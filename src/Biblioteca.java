import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Biblioteca {
    ArrayList<Livro> livros;
    ArrayList<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void getLivros() {
        livros.forEach(Livro::exibirDetalhes);
    }

    public void getUsuarios() {
        usuarios.forEach(Usuario::exibirDetalhes);
    }

    public void cadastrarLivro(Scanner scanner) {
        System.out.println("Nome do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Autor do livro: ");
        String autor = scanner.nextLine();

        System.out.println("ISBN do livro: ");
        String isbn = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, isbn);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void cadastrarUsuario(Scanner scanner) {
        System.out.println("Digite seu nome: ");
        String nomeUsuario = scanner.nextLine();

        System.out.println("Ids já existentes:");
        exibirIdUsuarios();

        System.out.println("Digite o id do usuário:");
        int idNovoUsuario = scanner.nextInt();
        scanner.nextLine();

        Usuario novoUsuario = new Usuario(nomeUsuario, idNovoUsuario, new ArrayList<>());
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void realizarEmprestimo(String isbn, int idUsuario) {
        Livro livro = livroPorISBN(isbn);
        Usuario usuario = idUsuario(idUsuario);

        if (livro != null && usuario != null && livro.disponivel && usuario.getLivrosEmprestados().size() < 3) {
            System.out.println("Empréstimo realizado com sucesso!");
            livro.disponivel = false;
            usuario.adicionarLivro(livro);
        } else {
            System.out.println("Algo deu errado! Ou o livro está indisponível ou você já tem mais de 3 livros emprestados.");
        }
    }

    public void realizarDevolucao(String isbn, int idUsuario) {
        Livro livro = livroPorISBN(isbn);
        Usuario usuario = idUsuario(idUsuario);

        if (livro != null && usuario != null && usuario.livrosEmprestados.contains(livro)) {
            livro.disponivel = true;
            usuario.removerLivro(livro);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Algo deu errado! Digite os dados novamente.");
        }
    }

    public void exibirLivrosDisponiveis() {
        livros.forEach(livro -> {
            if (livro.getDisponivel()) {
                livro.exibirDetalhes();
            }
        });
    }

    public void exibirIdUsuarios() {
        usuarios.forEach(Usuario::exibirIds);
    }

    public Livro livroPorISBN(String isbn) {
        for (Livro livro : livros) {
            if (Objects.equals(isbn, livro.getIsbn())) {
                return livro;
            }
        }
        return null;
    }

    public Usuario idUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (id == usuario.getId()) {
                return usuario;
            }
        }
        return null;
    }

    public void idUsuarioPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (Objects.equals(nome, usuario.getNome())) {
                System.out.println("ID: " + usuario.id + " - Nome usuário: " + usuario.nome);
            }
        }
    }

    public void menu(Scanner scanner) {
        System.out.println("""
            Você deseja
            digite 1: Cadastrar um livro
            digite 2: Cadastrar um usuário
            digite 3: Realizar um empréstimo
            digite 4: Realizar uma devolução de livro""");
        int escolhaUsuario = scanner.nextInt();
        scanner.nextLine();

        switch (escolhaUsuario) {
            case 1:
                cadastrarLivro(scanner);
                break;
            case 2:
                cadastrarUsuario(scanner);
                break;
            case 3:
                System.out.println("Informe o ISBN do livro que quer realizar o empréstimo:");
                String isbnLivro = scanner.nextLine();
                System.out.println("Digite o nome do usuário:");
                String nome = scanner.nextLine();
                idUsuarioPorNome(nome);
                System.out.println("Digite o ID do usuário:");
                int idUsuario = scanner.nextInt();
                scanner.nextLine();
                realizarEmprestimo(isbnLivro, idUsuario);
                break;
            case 4:
                System.out.println("Informe o ISBN do livro que quer devolver: ");
                String isbnLivroDevolver = scanner.nextLine();
                System.out.println("Digite o nome do usuário: ");
                String nomeDevolucao = scanner.nextLine();
                idUsuarioPorNome(nomeDevolucao);
                System.out.println("Digite o ID do usuário: ");
                int idUsuarioDevolucao = scanner.nextInt();
                scanner.nextLine();
                realizarDevolucao(isbnLivroDevolver, idUsuarioDevolucao);
                break;
            default:
                System.out.println("Algo deu errado!");
        }
    }
}