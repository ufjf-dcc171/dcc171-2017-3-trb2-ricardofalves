package pkg20173;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PedidoDAO {

    private static Scanner input;
    private File arquivo = new File("item.txt");

    public PedidoDAO() {
    }

    public void cria(ArrayList<Pedido> item) throws IOException {
        try {
            FileWriter fw = new FileWriter("item.txt", false);
            BufferedWriter conexao = new BufferedWriter(fw);
            for (Pedido p : item) {
                conexao.write(p.getProdutos() + "//" + p.getValor() + "//" + p.getQuantidade() + "//" + p.getTotal() + "//");
                conexao.newLine();
            }
            conexao.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pedido> busca() throws IOException {
        ArrayList<Pedido> item = new ArrayList<>();
        input = new Scanner(new FileReader("item.txt")).useDelimiter("//");
        input.useLocale(Locale.ENGLISH);
        try {
            while (input.hasNext()) {
                Pedido a = new Pedido();
                a.setProdutos(input.next());
                
                a.setTotal(input.nextDouble());
                item.add(a);
            }
        } catch (NoSuchElementException elementException) {
            System.out.println("Todas as leituras de item foram feitas.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }
        input.close();
        return item;
    }

    public Boolean vazio() throws IOException {
        try {
            Scanner input;
            input = new Scanner("item.txt");
            if (arquivo.exists() && arquivo.length() != 0) {
                input.close();
                return false;
            } else {
                input.close();
                return true;
            }
        } catch (SecurityException securityException) {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1);
        }
        return false;
    }
}
