package pkg20173;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private Integer numero;
    private String nomeMesa;
    private List<Pedido> pedidos;
    private Double total = 0.0;

    public Mesa() {
        this(null, null);
    }

    public Mesa(Integer numero, String nomeComanda) {
        this.numero = numero;
        this.nomeMesa = nomeComanda;
        this.pedidos = new ArrayList<>();
    }

    public Integer getNumero() {
        return numero;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Double getTotal() {
        return total;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setTotal(Double total) {
        this.total += total;
    }

    @Override
    public String toString() {
        return nomeMesa + " | Valor: " + total;
    }

}
