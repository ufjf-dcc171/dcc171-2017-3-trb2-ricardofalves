package pkg20173;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JanelaHotDog extends JFrame {

    private final JButton criarMesa = new JButton("Criar Mesa");
    private final JButton excluirMesa = new JButton("Excluir Mesa");
    private final JButton criarPedido = new JButton("Adicionar Pedido");
    private final JButton excluirPedido = new JButton("Remover Pedido");
    private final JButton fecharMesa = new JButton("Fechar Mesa");
    private final JPanel painelBotoes = new JPanel();

    private final List<Mesa> comandas;
    private final JList<Mesa> listaComandas = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> listaPedidos = new JList<>(new DefaultListModel<>());

    private final JanelaPedidos janelaPedidos = new JanelaPedidos();

    public JanelaHotDog(List<Mesa> dados) throws HeadlessException {
        super("Cachorro Quente do ICE");

        this.comandas = dados;
        listaComandas.setModel(new MesasListModel(comandas));
        add(new JScrollPane(listaComandas), BorderLayout.WEST);
        add(new JScrollPane(listaPedidos), BorderLayout.CENTER);
        listaComandas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        painelBotoes.setLayout(new GridLayout(1, 5));
        painelBotoes.add(criarMesa);
        painelBotoes.add(excluirMesa);
        painelBotoes.add(criarPedido);
        painelBotoes.add(excluirPedido);
        painelBotoes.add(fecharMesa);
        add(painelBotoes, BorderLayout.SOUTH);

        listaComandas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (listaComandas.getSelectedValue() != null) {
                    listaPedidos.setModel(new PedidosListModel(listaComandas.getSelectedValue().getPedidos()));
                } else {
                    listaPedidos.setModel(new DefaultListModel<>());
                }
            }
        });
        janelaPedidos.setJanelaComandas(this);

        criarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (listaComandas.getSelectedValue() != null) {
                    janelaPedidos.setSize(350, 200);
                    janelaPedidos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    janelaPedidos.setLocationRelativeTo(null);
                    janelaPedidos.setVisible(true);
                    janelaPedidos.fazPedido();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma comanda", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        criarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual o número da comanda?"));
                String nomeComanda = "Comanda " + id;
                Mesa comanda = new Mesa(id, nomeComanda);
                comandas.add(comanda);
                listaComandas.updateUI();

            }
        });

        excluirMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (listaComandas.getSelectedValue() != null) {
                    if (!listaComandas.getSelectedValue().getPedidos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Comanda possui pedidos", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        comandas.remove(listaComandas.getSelectedValue());
                        listaComandas.clearSelection();
                        listaComandas.updateUI();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma comanda", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        excluirPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pedido pedidoSelecionado = listaPedidos.getSelectedValue();
                if (pedidoSelecionado != null) {
                    listaComandas.getSelectedValue().getPedidos().remove(pedidoSelecionado);
                    listaComandas.updateUI();
                    listaPedidos.updateUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        fecharMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Mesa comandaSelecionada = listaComandas.getSelectedValue();
                if (comandaSelecionada != null) {
                    if (comandaSelecionada.getPedidos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Comanda Vazia", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int mesaFechada = comandaSelecionada.getNumero();
                        DecimalFormat d = new DecimalFormat();
                        d.setMaximumFractionDigits(2);
                        d.setMinimumFractionDigits(2);
                        int confirma = JOptionPane.showConfirmDialog(null, "Itens da Comanda: " + comandaSelecionada.getPedidos().toString(), "Confirmação", JOptionPane.OK_OPTION);
                        if (confirma == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Conta fechada em: " + new Date() + "\nValor a pagar: R$" + d.format(comandaSelecionada.getTotal()), "Conta Fechada", JOptionPane.INFORMATION_MESSAGE);
                            comandas.remove(comandaSelecionada);
                            listaComandas.clearSelection();
                            listaComandas.updateUI();
                            listaPedidos.updateUI();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma comanda", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    void adicionaPedido(Pedido pedido) {
        double valor = pedido.getQuantidade() * pedido.getValor();
        listaComandas.getSelectedValue().setTotal(valor);
        listaComandas.getSelectedValue().getPedidos().add(pedido);
        listaComandas.updateUI();
        listaPedidos.updateUI();

        janelaPedidos.setVisible(false);
    }

}
