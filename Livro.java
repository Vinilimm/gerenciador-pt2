import java.util.List;

public class Livro extends PdfEntry {
    private String areaConhecimento;
    private int anoPublicacao;
    private String editora;
    private int numeroPaginas;

    public Livro(List<String> autores, String titulo, String subtitulo, String caminhoPDF,
                 String areaConhecimento, int anoPublicacao, String editora, int numeroPaginas) {
        super(autores, titulo, subtitulo, caminhoPDF);
        this.areaConhecimento = areaConhecimento;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "[Livro] " + super.toString() +
               ", Area: " + areaConhecimento +
               ", Ano: " + anoPublicacao +
               ", Editora: " + (editora.isEmpty() ? "-" : editora) +
               ", Paginas: " + (numeroPaginas > 0 ? numeroPaginas : "-");
    }
}
