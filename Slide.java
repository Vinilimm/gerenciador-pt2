import java.util.List;

public class Slide extends PdfEntry {
    private String disciplina;
    private String instituicao;

    public Slide(List<String> autores, String titulo, String caminhoPDF,
                 String disciplina, String instituicao) {
        super(autores, titulo, "", caminhoPDF);
        this.disciplina = disciplina;
        this.instituicao = instituicao;
    }

    @Override
    public String toString() {
        return "[Slide] " + super.toString() +
               ", Disciplina: " + disciplina +
               ", Instituicao: " + (instituicao.isEmpty() ? "-" : instituicao);
    }
}
