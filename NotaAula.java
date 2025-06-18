import java.util.List;

public class NotaAula extends PdfEntry {
    private String disciplina;
    private String instituicao;
    private int numeroPaginas;

    public NotaAula(List<String> autores, String titulo, String subtitulo, String caminhoPDF,
                    String disciplina, String instituicao, int numeroPaginas) {
        super(autores, titulo, subtitulo, caminhoPDF);
        this.disciplina = disciplina;
        this.instituicao = instituicao;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "[Nota de Aula] " + super.toString() +
               ", Disciplina: " + disciplina +
               ", Instituicao: " + (instituicao.isEmpty() ? "-" : instituicao) +
               ", Paginas: " + (numeroPaginas > 0 ? numeroPaginas : "-");
    }
}
