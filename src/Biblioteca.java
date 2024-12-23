import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Biblioteca {
    ArrayList<Livro> livros;
    ArrayList<Usuario> usuarios;

    public Biblioteca(ArrayList<Livro> livros, ArrayList<Usuario> usuarios) {
        this.livros = livros;
        this.usuarios = usuarios;
    }

    public void cadastrarLivro(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Nome do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Autor do livro: ");
        String autor = scanner.nextLine();

        System.out.println("ISBN do livro: ");
        String isbn = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, isbn);
        livros.add(livro);
    }

    public void cadastrarUsuario(Scanner scanner) {
        System.out.println("Seu nome: ");
        String nome = scanner.nextLine();
        int verificador = new Random().nextInt();
        Usuario usuarioExistente =  idUsuario(verificador);
        while (usuarioExistente != null) {
            verificador = new Random().nextInt();
            usuarioExistente =  idUsuario(verificador);

        }
        Usuario usuario = new Usuario(nome, new ArrayList<Livro>());
        usuarios.add(usuario);
        boolean novoLivro = true;
        while (novoLivro) {
            System.out.println("Informe o isbn do livro que quer realizar empréstimo: ");
            String isbnLivro = scanner.nextLine();
            realizarEmprestimo(isbnLivro, usuario.id);
            System.out.println("Deseja fazer empréstimo de mais um livro?");
            System.out.println("Digite 1 para SIM\n ou 0 para NÃO");
            novoLivro = Boolean.parseBoolean(scanner.nextLine());

        }
    }

    public void realizarEmprestimo(String isbn, int idUsuario) {
        Livro livro = livroPorISBN(isbn);
        Usuario usuario = idUsuario(idUsuario);

        if (livro.disponivel && usuario.getLivrosEmprestados().size() < 3) {
            System.out.println("Empréstimo realizado com sucesso!");
            livro.disponivel = false;
            usuario.adicionarLivro(livro);
        } else {
            System.out.println("Algo deu errado! Ou " +
                    "o livro está indisponível ou você já tem mais de 3 livros emprestados");
        }

    }

    public void realizarDevolucao(String isbn, int idUsuario) {
        Livro livro = livroPorISBN(isbn);
        Usuario usuario = idUsuario(idUsuario);

        if (usuario.livrosEmprestados.contains(livro)) {
            livro.disponivel = true;
            usuario.removerLivro(livro);
            System.out.println("Livro devolvido com sucesso");
        } else {
            System.out.println("Algo deu errado! Digite os dados novamente");
        }
    }

    public void exibirLivrosDisponiveis() {
        livros.forEach(livro -> {
            if (livro.getDisponivel()) {
                livro.exibirDetalhes();
            }
        });
    }

    public Livro livroPorISBN (String isbn) {
        for (Livro livro: livros) {
            if (Objects.equals(isbn, livro.getIsbn())) {
                return livro;
            }
        }
        return null;
    }

    public Usuario idUsuario (int id) {
        for (Usuario usuario: usuarios) {
            if (id == usuario.getId()) {
                return usuario;
            }
        }
        return null;
    }

    public void idUsuarioPorNome (String nome) {
        for (Usuario usuario: usuarios) {
            if (Objects.equals(nome, usuario.getNome())) {
                System.out.println("ID: " + usuario.id + " - " + "Nome usuário: " + usuario.nome);
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
        switch (escolhaUsuario) {
            case 1: cadastrarLivro(scanner);
            break;
            case 2: cadastrarUsuario(scanner);
            break;
            case 3:
                System.out.println("Informe o isbn do livro que quer realizar o empréstimo");
                String isbnLivro = scanner.nextLine();
                System.out.println("Digite o nome do usuário");
                String nome = scanner.nextLine();
                idUsuarioPorNome(nome);
                System.out.println("Digite o ID do usuário");
                int idUsuario = scanner.nextInt();
                realizarEmprestimo(isbnLivro, idUsuario);
            break;
            case 4:
                System.out.println("Informe o isbn do livro que quer devolver");
                String isbnLivroDevolver = scanner.nextLine();
                System.out.println("Digite o nome do usuário");
                String nomeDevolucao = scanner.nextLine();
                idUsuarioPorNome(nomeDevolucao);
                System.out.println("Digite o ID do usuário");
                int idUsuarioDevolucao = scanner.nextInt();
                realizarEmprestimo(isbnLivroDevolver, idUsuarioDevolucao);
                break;
        }
    }

}
