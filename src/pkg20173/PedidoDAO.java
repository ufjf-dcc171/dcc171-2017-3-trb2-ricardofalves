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
    private File arquivo = new File("pedido.txt");

    public PedidoDAO() {
    }

    public void cria(ArrayList<Pedido> item) throws IOException {
        try {
            FileWriter fw = new FileWriter("pedido.txt", false);
            BufferedWriter conexao = new BufferedWriter(fw);
            for (Pedido p : item) {
                conexao.write(p.getQuantidade()+ "//" + p.getProdutos()+ "//" + p.getValor()+ "//" + p.getTotal() + "//" + p.isStatusPedido());
                conexao.newLine();
            }
            conexao.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pedido> busca() throws IOException {
        ArrayList<Pedido> item = new ArrayList<>();
        input = new Scanner(new FileReader("pedido.txt")).useDelimiter("//");
        input.useLocale(Locale.ENGLISH);
        try {
            while (input.hasNext()) {
                Pedido p = new Pedido();
                p.setQuantidade(input.nextInt());
                p.setProdutos(input.next());
                p.setTotal(input.nextDouble());
                p.setStatusPedido(input.nextBoolean());
                item.add(p);
            }
        } catch (NoSuchElementException elementException) {
            System.out.println("Todas as leituras de item foram feitas.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }
        input.close();
        return item;
    }

        public Boolean ehVazio() throws IOException {
        try {
            Scanner input;
            input = new Scanner("pedido.txt");
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
