import java.io.*;
import java.nio.file.*;

public class Persistencia {
    private static final String ARQUIVO_CAMINHO = "caminho_biblioteca.txt";

    public static void salvarCaminhoBiblioteca(Path caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CAMINHO))) {
            writer.write(caminho.toAbsolutePath().toString());
        } catch (IOException e) {
            System.err.println("Erro ao salvar caminho: " + e.getMessage());
        }
    }

    public static Path lerCaminhoBiblioteca() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CAMINHO))) {
            String linha = reader.readLine();
            if (linha != null && !linha.isEmpty()) {
                return Paths.get(linha);
            }
        } catch (IOException e) {
            // Ignora erro, será tratado no main
        }
        return null;
    }
}
