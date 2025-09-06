import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaEventos implements Serializable {

    private List<Evento> eventos;
    private final String arquivo = "events.data";

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
        carregarEventos(); // Tenta carregar os eventos quando o objeto é criado
    }

    public void adicionarEvento(Evento evento) {
        this.eventos.add(evento);
        salvarEventos(); // Salva a lista toda vez que um evento é adicionado
    }

    public List<Evento> listarEventos() {
        return this.eventos;
    }

    public void salvarEventos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(eventos);
            System.out.println("Eventos salvos com sucesso em " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarEventos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            this.eventos = (List<Evento>) ois.readObject();
            System.out.println("Eventos carregados com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de eventos não encontrado. Criando novo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar eventos: " + e.getMessage());
        }
    }
}