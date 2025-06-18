import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = null;

        // Verifica path salvo
        Path pathSalvo = Persistencia.lerCaminhoBiblioteca();
        if (pathSalvo != null && Files.exists(pathSalvo)) {
            System.out.println("Biblioteca encontrada: " + pathSalvo);
            biblioteca = new Biblioteca("Biblioteca Padrao", pathSalvo);
        } else {
            System.out.println("Nenhuma biblioteca encontrada. Digite um path para criar uma nova:");
            String novoPath = scanner.nextLine();
            Path pathNovo = Paths.get(novoPath);
            try {
                Files.createDirectories(pathNovo);
                Persistencia.salvarCaminhoBiblioteca(pathNovo);
                biblioteca = new Biblioteca("Biblioteca Padrao", pathNovo);
            } catch (Exception e) {
                System.out.println("Erro ao criar biblioteca: " + e.getMessage());
                return;
            }
        }

        int opcao;
        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1 - Adicionar entrada");
            System.out.println("2 - Listar entradas");
            System.out.println("3 - Remover entrada");
            System.out.println("4 - Editar entrada");
            System.out.println("5 - Criar nova biblioteca");
            System.out.println("6 - Alternar biblioteca");
            System.out.println("7 - Criar coleção");
            System.out.println("8 - Adicionar entrada à coleção");
            System.out.println("9 - Remover entrada da coleção");
            System.out.println("10 - Exportar coleção .bib");
            System.out.println("11 - Exportar coleção .zip");
            System.out.println("12 - Listar coleções");
            System.out.println("0 - Sair");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("Escolha o tipo: 1-Livro, 2-Nota, 3-Slide");
                    int tipo = Integer.parseInt(scanner.nextLine());
                    PdfEntry novaEntrada = EntradaFactory.criarEntrada(tipo, scanner);
                    biblioteca.adicionarEntrada(novaEntrada);
                    break;
                case 2:
                    biblioteca.listarEntradas();
                    break;
                case 3:
                    System.out.println("Informe o titulo da entrada a remover:");
                    String tituloRem = scanner.nextLine();
                    if (biblioteca.removerEntrada(tituloRem))
                        System.out.println("Removido com sucesso.");
                    else
                        System.out.println("Nao encontrado.");
                    break;
                case 4:
                    System.out.println("Informe o titulo da entrada a editar:");
                    String tituloAntigo = scanner.nextLine();
                    System.out.println("Insira a nova entrada:");
                    System.out.println("Escolha o tipo: 1-Livro, 2-Nota, 3-Slide");
                    int tipoNovo = Integer.parseInt(scanner.nextLine());
                    PdfEntry entradaNova = EntradaFactory.criarEntrada(tipoNovo, scanner);
                    if (biblioteca.editarEntrada(tituloAntigo, entradaNova))
                        System.out.println("Editado com sucesso.");
                    else
                        System.out.println("Entrada nao encontrada.");
                    break;
                case 5:
                    System.out.println("Digite o path para nova biblioteca:");
                    String novo = scanner.nextLine();
                    Path novoDir = Paths.get(novo);
                    try {
                        Files.createDirectories(novoDir);
                        Persistencia.salvarCaminhoBiblioteca(novoDir);
                        biblioteca = new Biblioteca("Nova Biblioteca", novoDir);
                        System.out.println("Nova biblioteca criada e ativada.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Digite o path de uma biblioteca existente:");
                    String existente = scanner.nextLine();
                    Path existePath = Paths.get(existente);
                    if (Files.exists(existePath)) {
                        Persistencia.salvarCaminhoBiblioteca(existePath);
                        biblioteca = new Biblioteca("Alternada", existePath);
                        System.out.println("Biblioteca alternada com sucesso.");
                    } else {
                        System.out.println("Path nao encontrado.");
                    }
                    break;
                 
            }

        } while (opcao != 0);
    }
}