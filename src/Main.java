import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Livro novoLivro1 = new Livro("Pequeno Príncipe", " Antoine","abc1");
//        Livro novoLivro2 = new Livro("Harry Potter e a Pedra Filosofal", "J.K.Rowling","abc2");
//        Livro novoLivro3 = new Livro("Sapiens", "Yuval Harari","abc3");
//        ArrayList<Livro> todosLivros = new ArrayList<>();
//        todosLivros.add(novoLivro1);
//        todosLivros.add(novoLivro2);
//        todosLivros.add(novoLivro3);
//
//        ArrayList<Livro> livrosFlavia = new ArrayList<>();
//        livrosFlavia.add(novoLivro1);
//        livrosFlavia.add(novoLivro2);
//
//        ArrayList<Livro> livrosP = new ArrayList<>();
//        livrosP.add(novoLivro3);
//
//        Usuario novoUsuario1 = new Usuario("Flávia", 1, livrosFlavia);
//        Usuario novoUsuario2 = new Usuario("Pedro", 2, livrosP);
//
//        ArrayList<Usuario> todosUsuarios = new ArrayList<>();
//        todosUsuarios.add(novoUsuario1);
//        todosUsuarios.add(novoUsuario2);

        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        biblioteca.menu(scanner);

    }

}
