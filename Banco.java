import java.util.List;

public class Banco {
    static List<UsuarioBanco> usuarios;

    public static void main(String[] args) {
        DadosUsuario infoUsuarios = new DadosUsuario();
        usuarios = infoUsuarios.carregarUsuarios(
            "usuarios.txt");

        BancoInterface banco = new BancoInterface();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                banco.criarInterface();
            }
        });
    }
}
