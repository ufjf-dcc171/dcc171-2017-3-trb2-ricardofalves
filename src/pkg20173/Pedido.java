package pkg20173;

import java.util.Date;

public class Pedido {

    private Integer quantidade;
    private Double valor = 5.0; //todos os itens tem o mesmo preço
    private Double total = 0.0;
    private String produtos;
    private Date abreComanda;
    private Date fechaComanda;
    private boolean statusPedido;

    public boolean isStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(boolean statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Pedido() {
        this(null, null, null, null, true);
    }

    public Pedido(Integer quantidade, String produtos, Date abreComanda, Date fechaComanda, boolean b) {
        this.quantidade = quantidade;
        this.produtos = produtos;
        this.abreComanda = abreComanda;
        this.fechaComanda = fechaComanda;
        this.statusPedido = b;
    }

    public Date getAbreComanda() {
        return abreComanda;
    }

    public Date getFechaComanda() {
        return fechaComanda;
    }

    public String getProdutos() {
        return produtos;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setAbreComanda(Date abreComanda) {
        this.abreComanda = abreComanda;
    }

    public void setFechaComanda(Date fechaComanda) {
        this.fechaComanda = fechaComanda;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total += total;
    }

    @Override
    public String toString() {
        if (statusPedido) {
            return quantidade + "x " + produtos + " ---Pedido Aberto";
        }
        return quantidade + "x " + produtos + " ---Pedido Fechado";
    }

}
