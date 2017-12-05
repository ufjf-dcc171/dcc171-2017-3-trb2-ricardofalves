package pkg20173;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Dados {

    private File file = new File("historico.txt");
    private FileWriter historico;

    public Dados() {
    }

    Dados(Integer numero, Double total, List<Pedido> pedidos) {
        if (!file.exists()) {
            try {
                historico = new FileWriter("historico.txt");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao tentar criar o arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        FileWriter fw;
        try {
            fw = new FileWriter("historico.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder escritorArquivo = new StringBuilder();
            DecimalFormat d = new DecimalFormat();
            d.setMinimumFractionDigits(2);
            d.setMaximumFractionDigits(2);

            escritorArquivo.append("Mesa " + numero + " // ");
            escritorArquivo.append("Valor a pagar: R$" + d.format(total) + " // ");

            escritorArquivo.append("Itens Pedidos " + pedidos);
            escritorArquivo.append("\n");

            escritorArquivo.append("\n");
            bw.write(escritorArquivo.toString());
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(JanelaHotDog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
