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

public class JanelaItens extends JFrame {

    private final JLabel quantidadeItens = new JLabel("Selecione a quantidade: ");

    private String[] paes = {"Pão de Sal", "Pão de Forma", "Pão de Hot Dog"};
    private String[] recheios = {"Salsicha", "Linguiça", "Frango", "Carne Moída"};
    private String[] queijos = {"Cheddar", "Prato", "Minas"};
    private final JComboBox<String> pao = new JComboBox<>(paes);
    private final JComboBox<String> recheio = new JComboBox<>(recheios);
    private final JComboBox<String> queijo = new JComboBox<>(queijos);
    private final JTextField txtItem = new JTextField(25);
    private final JTextField txtQuantidade = new JTextField("texto", 10);
    private final JButton finalizaItem = new JButton("Finaliza Item");

    private JanelaHotDog janela;

    public JanelaItens() throws HeadlessException {
        super("Itens");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(pao);
        add(recheio);
        add(queijo);

        txtItem.setEditable(false);
        add(txtItem);
        add(quantidadeItens);
        add(txtQuantidade);
        add(finalizaItem);

        HotDogHandler handler = new HotDogHandler();
        pao.addItemListener(handler);
        recheio.addItemListener(handler);
        queijo.addItemListener(handler);
        pao.setSelectedIndex(1);

        finalizaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtItem.getText() == null || txtQuantidade.getText() == null) {
                    JOptionPane.showMessageDialog(null, "Item está vazio", "Item Vazio", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    DecimalFormat d = new DecimalFormat();
                    d.setMaximumFractionDigits(2);
                    d.setMinimumFractionDigits(2);
                    Integer quantidade = Integer.parseInt(txtQuantidade.getText());
                    int confirma = JOptionPane.showConfirmDialog(null, "Item pedido: " + txtItem.getText()
                            + "\nValor total: " + d.format(quantidade.doubleValue() * 5.0), "Pedido realizado", JOptionPane.OK_OPTION);
                    if (confirma == JOptionPane.OK_OPTION) {
                        Pedido item = new Pedido(null, null, null, null, true);
                        item.setAbreComanda(new Date());
                        item.setProdutos(txtItem.getText());
                        item.setQuantidade(quantidade);
                        item.setStatusPedido(true);

                        janela.adicionaPedido(item);
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

            txtItem.setText(sb.toString());
        }
    }

}
