import java.time.LocalDate;
import java.util.ArrayList; // Importa a classe ArrayList
import java.util.List;      // Importa a interface List

public class Usuario {

    // Atributos
    private String nomeCompleto;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private String senha;
    private List<Evento> eventosConfirmados; // Novo atributo para a lista

    // Construtor
    public Usuario(String nomeCompleto, String email, String cpf, LocalDate dataNascimento, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.eventosConfirmados = new ArrayList<>(); // Inicializa a lista
    }

    // Getters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public List<Evento> getEventosConfirmados() { // Getter para a nova lista
        return eventosConfirmados;
    }

    // Setters
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Novo método para adicionar evento à lista
    public void confirmarParticipacao(Evento evento) {
        this.eventosConfirmados.add(evento);
    }

    // Novo método para remover evento da lista
    public void cancelarParticipacao(Evento evento) {
        this.eventosConfirmados.remove(evento);
    }
}