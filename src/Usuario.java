import java.util.ArrayList;

public class Usuario {
    String nome;
    int id;
    ArrayList<Livro> livrosEmprestados;

    public Usuario(String nome, ArrayList<Livro> livrosEmprestados) {
        this.nome = nome;
        this.livrosEmprestados = livrosEmprestados;
    }

    public String getNome() {
        System.out.println(this.nome);
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        livrosEmprestados.forEach(livro -> {
            livro.getTitulo();
        });
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

    public void exibirDetalhes() {
        System.out.printf("Nome: %s, Id: %d, livrosEmprestados: %s",
                this.nome, this.id, this.livrosEmprestados);
    }

    public void adicionarLivro(Livro livro) {
        this.livrosEmprestados.add(livro);
    }

    public void removerLivro(Livro livro) {
        this.livrosEmprestados.remove(livro);
    }
}

