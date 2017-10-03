
package pkg20173;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class PedidosListModel implements ListModel<Pedido> {
    private List<Pedido> pedidos;
    private List<ListDataListener> dataListeners;
    
    public PedidosListModel(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        this.dataListeners.add(ll);
    }

    @Override
    public int getSize() {
        return pedidos.size();
    }

    @Override
    public Pedido getElementAt(int i) {
        return pedidos.get(i);
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        this.dataListeners.remove(ll);
    }
    

    
}
