import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.io.IOException;

public class Biblioteca {
    private String nome;
    private Path diretorioBase;
    private List<PdfEntry> entradas;

    public Biblioteca(String nome, Path diretorioBase) {
        this.nome = nome;
        this.diretorioBase = diretorioBase;
        this.entradas = new ArrayList<>();
    }

    public void adicionarEntrada(PdfEntry entrada) {
        entradas.add(entrada);
        copiarParaAutor(entrada);
    }

    public boolean removerEntrada(String titulo) {
        for (PdfEntry entrada : entradas) {
            if (entrada.getTitulo().equalsIgnoreCase(titulo)) {
                entradas.remove(entrada);
                return true;
            }
        }
        return false;
    }

    public boolean editarEntrada(String titulo, PdfEntry novaEntrada) {
        for (int i = 0; i < entradas.size(); i++) {
            if (entradas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                entradas.set(i, novaEntrada);
                copiarParaAutor(novaEntrada);
                return true;
            }
        }
        return false;
    }

    public void listarEntradas() {
        for (PdfEntry entrada : entradas) {
            System.out.println(entrada);
        }
    }

    private void copiarParaAutor(PdfEntry entrada) {
        if (entrada.getAutores().isEmpty()) return;

        String autor = entrada.getAutores().get(0).replaceAll(" ", "_");
        Path pastaAutor = diretorioBase.resolve(autor);

        try {
            Files.createDirectories(pastaAutor);
            Path origem = Paths.get(entrada.getCaminhoPDF());
            Path destino = pastaAutor.resolve(origem.getFileName());
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("PDF copiado para " + destino);
        } catch (IOException e) {
            System.err.println("Erro ao copiar o arquivo: " + e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public Path getDiretorioBase() {
        return diretorioBase;
    }
}