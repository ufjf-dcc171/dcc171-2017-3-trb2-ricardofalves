
package pkg20173;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class Diretorio {
    private File file = new File("historico.txt");
    private FileWriter file1;
    private FileWriter fileItem;

    public Diretorio() {
        if (!file.exists()) {
            try {
                file1 = new FileWriter("historico.txt");
                fileItem = new FileWriter("item.txt");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar o diretorio");
            }
        }
    }

    
    public FileWriter getFile1() {
        return file1;
    }

    public void setFile1(FileWriter file1) {
        this.file1 = file1;
    }

    public FileWriter getFileItem() {
        return fileItem;
    }

    public void setFileItem(FileWriter fileItem) {
        this.fileItem = fileItem;
    }
    
    
    
    
    
}
