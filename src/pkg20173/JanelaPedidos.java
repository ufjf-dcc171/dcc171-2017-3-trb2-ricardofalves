package pkg20173;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.Date;
import javafx.scene.control.ComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaPedidos extends JFrame {

    private final JLabel quantidadePedidos = new JLabel("Selecione a quantidade: ");

    private String[] paes = {"Pão de Sal", "Pão de Forma", "Pão de Hot Dog"};
    private String[] recheios = {"Salsicha", "Linguiça", "Frango", "Carne Moída"};
    private String[] queijos = {"Cheddar", "Prato", "Minas"};
    private final JComboBox<String> pao = new JComboBox<>(paes);
    private final JComboBox<String> recheio = new JComboBox<>(recheios);
    private final JComboBox<String> queijo = new JComboBox<>(queijos);
    private final JTextField txtPedido = new JTextField(25);
    private final JTextField txtQuantidade = new JTextField("texto", 10);
    private final JButton finalizaPedido = new JButton("Finaliza Pedido");

    private JanelaHotDog janela;

    public JanelaPedidos() throws HeadlessException {
        super("Pedidos");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(pao);
        add(recheio);
        add(queijo);

        txtPedido.setEditable(false);
        add(txtPedido);
        add(quantidadePedidos);
        add(txtQuantidade);
        add(finalizaPedido);

        HotDogHandler handler = new HotDogHandler();
        pao.addItemListener(handler);
        recheio.addItemListener(handler);
        queijo.addItemListener(handler);
        pao.setSelectedIndex(1);

        finalizaPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtPedido.getText() == null || txtQuantidade.getText() == null) {
                    JOptionPane.showMessageDialog(null, "Seu pedido está vazio", "Pedido Vazio", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    DecimalFormat d = new DecimalFormat();
                    d.setMaximumFractionDigits(2);
                    d.setMinimumFractionDigits(2);
                    Integer quantidade = Integer.parseInt(txtQuantidade.getText());
                    int confirma = JOptionPane.showConfirmDialog(null, "Pedido feito: " + txtPedido.getText()
                            + "\nValor total: " + d.format(quantidade.doubleValue() * 5.0), "Pedido realizado", JOptionPane.OK_OPTION);
                    if (confirma == JOptionPane.OK_OPTION) {
                        Pedido pedido = new Pedido(null, null, null, null);
                        pedido.setAbreComanda(new Date());
                        pedido.setProdutos(txtPedido.getText());
                        pedido.setQuantidade(quantidade);

                        janela.adicionaPedido(pedido);
                    }
                }
            }

        });

    }

    public void setJanelaComandas(JanelaHotDog janela) {
        this.janela = janela;
    }

    public void fazPedido() {
        setLocationRelativeTo(null);
        setVisible(true);
        txtQuantidade.setText("");
        txtQuantidade.requestFocus();
    }

    private class HotDogHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            StringBuilder sb = new StringBuilder();

            sb.append((String) pao.getSelectedItem());
            sb.append(" + ");
            sb.append((String) recheio.getSelectedItem());
            sb.append(" + ");
            sb.append((String) queijo.getSelectedItem());

            txtPedido.setText(sb.toString());
        }
    }

}
