
package pkg20173;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Program {

   
    public static void main(String[] args) {
        JanelaHotDog janela = new JanelaHotDog(dadosIniciais());
        janela.setSize(700,350);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
     private static List<Mesa> dadosIniciais(){
        Mesa comanda1 = new Mesa(1, "Mesa 1");
        Mesa comanda2 = new Mesa(2, "Mesa 2");
        Mesa comanda3 = new Mesa(3, "Mesa 3");
        Mesa comanda4 = new Mesa(4, "Mesa 4");
        
        List<Mesa> comandas = new ArrayList<>();
        comandas.add(comanda1);
        comandas.add(comanda2);
        comandas.add(comanda3);
        comandas.add(comanda4);
        
        return comandas;
     }
    
    
}
