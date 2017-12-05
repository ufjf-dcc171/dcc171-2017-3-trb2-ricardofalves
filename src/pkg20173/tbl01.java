
package pkg20173;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class tbl01 {

   
    public static void main(String[] args) {
        JanelaHotDog janela = new JanelaHotDog(dadosIniciais());
        janela.setSize(700,350);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
     private static List<Mesa> dadosIniciais(){
        Mesa comanda1 = new Mesa(1, "Comanda 1");
        Mesa comanda2 = new Mesa(2, "Comanda 2");
        Mesa comanda3 = new Mesa(3, "Comanda 3");
        Mesa comanda4 = new Mesa(4, "Comanda 4");
        
        List<Mesa> comandas = new ArrayList<>();
        comandas.add(comanda1);
        comandas.add(comanda2);
        comandas.add(comanda3);
        comandas.add(comanda4);
        
        return comandas;
     }
    
    
}
