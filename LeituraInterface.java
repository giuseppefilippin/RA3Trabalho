import javax.swing.*;

public abstract class LeituraInterface {
    protected double lerValor(String mensagem) throws ValorInvalidoException {
        JTextField textField = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensagem));
        panel.add(textField);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Digite o valor", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double valor = Double.parseDouble(textField.getText());
                if (valor <= 0) {
                    throw new ValorInvalidoException();
                }
                return valor;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Valor inválido", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        return -1; // cancel
    }

    protected String lerString(String mensagem) {
        JTextField textField = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensagem));
        panel.add(textField);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Digite o texto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String texto = textField.getText();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Campo vazio", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return "";
            }
            return texto;
        }
        return ""; // cancel
    }
}
