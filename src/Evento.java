import java.io.Serializable;
import java.time.LocalDateTime;

// Enum para as categorias de eventos
enum CategoriaEvento {
    CONFERENCIAS,
    WORKSHOPS_TREINAMENTOS,
    SEMINARIOS_PALESTRAS,
    FEIRAS_EXPOSICOES,
    REUNIOES_ASSEMBLEIAS,
    OUTROS
}

// Classe que representa um Evento no nosso sistema
public class Evento implements Serializable {

    // Atributos privados para garantir o encapsulamento
    private String nome;
    private String endereco;
    private CategoriaEvento categoria;
    private LocalDateTime horario;
    private String descricao;

    // Construtor para criar um novo objeto Evento
    public Evento(String nome, String endereco, CategoriaEvento categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    // Métodos Getters para acessar os atributos
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getDescricao() {
        return descricao;
    }
}