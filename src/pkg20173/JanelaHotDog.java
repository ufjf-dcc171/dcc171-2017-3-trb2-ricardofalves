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
    private final JButton fecharPedido = new JButton("Fechar Pedido");
    private final JButton exibirDados = new JButton("Exibir Dados");
    private final JPanel painelBotoes = new JPanel();

    private final List<Mesa> mesas;
    private final JList<Mesa> listaMesas = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> listaPedidos = new JList<>(new DefaultListModel<>());

    private final JanelaItens janelaPedidos = new JanelaItens();

    public JanelaHotDog(List<Mesa> dados) throws HeadlessException {
        super("Cachorro Quente do ICE");

        this.mesas = dados;
        listaMesas.setModel(new MesasListModel(mesas));
        add(new JScrollPane(listaMesas), BorderLayout.WEST);
        add(new JScrollPane(listaPedidos), BorderLayout.CENTER);
        listaMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        painelBotoes.setLayout(new GridLayout(1, 6));
        painelBotoes.add(criarMesa);
        painelBotoes.add(excluirMesa);
        painelBotoes.add(criarPedido);
        painelBotoes.add(excluirPedido);
        painelBotoes.add(fecharPedido);
        painelBotoes.add(exibirDados);
        add(painelBotoes, BorderLayout.SOUTH);

        listaMesas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (listaMesas.getSelectedValue() != null) {
                    listaPedidos.setModel(new PedidosListModel(listaMesas.getSelectedValue().getPedidos()));
                } else {
                    listaPedidos.setModel(new DefaultListModel<>());
                }
            }
        });
        janelaPedidos.setJanelaComandas(this);

        criarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (listaMesas.getSelectedValue() != null) {
                    janelaPedidos.setSize(350, 200);
                    janelaPedidos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    janelaPedidos.setLocationRelativeTo(null);
                    janelaPedidos.setVisible(true);
                    janelaPedidos.fazPedido();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        criarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual o número da mesa?"));
                String nomeMesa = "Mesa " + id;
                Mesa mesa = new Mesa(id, nomeMesa);
                mesas.add(mesa);
                listaMesas.updateUI();

            }
        });

        excluirMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (listaMesas.getSelectedValue() != null) {
                    mesas.remove(listaMesas.getSelectedValue());
                    listaMesas.clearSelection();
                    listaMesas.updateUI();

                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        excluirPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pedido pedidoSelecionado = listaPedidos.getSelectedValue();
                if (pedidoSelecionado != null) {
                    listaMesas.getSelectedValue().getPedidos().remove(pedidoSelecionado);
                    listaMesas.updateUI();
                    listaPedidos.updateUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        exibirDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JanelaDados jd = new  JanelaDados();
                jd.setLocationRelativeTo(null);
                jd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jd.setVisible(true);
                
            }
        });
        fecharPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pedido pedidoSelecionado = listaPedidos.getSelectedValue();
                Mesa mesaSelecionada = listaMesas.getSelectedValue();
                if (pedidoSelecionado != null) {
                    if (pedidoSelecionado.getProdutos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Pedido Vazio", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        //int mesaFechada = pedidoSelecionado.getTotal();
                        DecimalFormat d = new DecimalFormat();
                        d.setMaximumFractionDigits(2);
                        d.setMinimumFractionDigits(2);
                        int confirma = JOptionPane.showConfirmDialog(null, "Itens: " + pedidoSelecionado.getProdutos().toString(), "Confirmação", JOptionPane.OK_OPTION);
                        if (confirma == JOptionPane.YES_OPTION) {
                            double total = pedidoSelecionado.getValor() * pedidoSelecionado.getQuantidade();
                            JOptionPane.showMessageDialog(null, "Conta fechada em: " + new Date() + "\nValor a pagar: R$" + d.format(total), "Conta Fechada", JOptionPane.INFORMATION_MESSAGE);
                            listaPedidos.getSelectedValue().setStatusPedido(false);
                            listaMesas.getSelectedValue().setTotal(-total);
                            listaMesas.updateUI();
                            listaPedidos.updateUI();
                           Dados dados = new Dados(mesaSelecionada.getNumero(), mesaSelecionada.getTotal(), mesaSelecionada.getPedidos());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
              
            }
        });
    }

    void adicionaPedido(Pedido pedido) {
        double valor = pedido.getQuantidade() * pedido.getValor();
        listaMesas.getSelectedValue().setTotal(valor);
        listaMesas.getSelectedValue().getPedidos().add(pedido);
        listaMesas.updateUI();
        listaPedidos.updateUI();

        janelaPedidos.setVisible(false);
    }

}
