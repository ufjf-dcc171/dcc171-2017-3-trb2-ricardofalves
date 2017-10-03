
package pkg20173;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class tbl01 {

   
    public static void main(String[] args) {
        JanelaHotDog janela = new JanelaHotDog(dadosIniciais());
        janela.setSize(800,300);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
     private static List<Comanda> dadosIniciais(){
        Comanda comanda1 = new Comanda(1, "Comanda 1");
        Comanda comanda2 = new Comanda(2, "Comanda 2");
        Comanda comanda3 = new Comanda(3, "Comanda 3");
        Comanda comanda4 = new Comanda(4, "Comanda 4");
        
        List<Comanda> comandas = new ArrayList<>();
        comandas.add(comanda1);
        comandas.add(comanda2);
        comandas.add(comanda3);
        comandas.add(comanda4);
        
        return comandas;
     }
    
    
}
