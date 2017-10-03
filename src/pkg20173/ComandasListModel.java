
package pkg20173;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ComandasListModel implements ListModel<Comanda> {
    private final List<Comanda> comandas;
    private final List<ListDataListener> dataListener;

  

    public ComandasListModel(List<Comanda> comandas) {
        this.comandas = comandas;
        this.dataListener = new ArrayList<>();
    }
   
    @Override
    public int getSize() {
        return comandas.size();
    }

    @Override
    public Comanda getElementAt(int i) {
        return comandas.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        this.dataListener.add(ll);
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        this.dataListener.remove(ll);
    }
    
}
