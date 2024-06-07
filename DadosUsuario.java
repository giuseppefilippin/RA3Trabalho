import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DadosUsuario {
    public List<UsuarioBanco> carregarUsuarios(String arquivo) {
        List<UsuarioBanco> usuarios = new ArrayList<>();

        // carrega um arquivo txt e armazena os dados de usuario
        try (BufferedReader buffer = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = buffer.readLine()) != null) {
                String[] val = linha.split(","); // separa por virgule
                String nome = val[0].trim(); //corta os espacoes
                double saldo = Double.parseDouble(val[1].trim());
                UsuarioBanco usuario = new UsuarioBanco(nome, saldo);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }

        return usuarios;
    }
}
