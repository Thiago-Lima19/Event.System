import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Objeto para gerenciar eventos (agora ele cuida da lista e dos arquivos)
        SistemaEventos sistema = new SistemaEventos();

        // Objeto Usuario para representar a pessoa usando o sistema
        Usuario usuarioDoSistema = new Usuario("Nome do Usuario", "email@exemplo.com", "123.456.789-00", LocalDate.of(1990, 1, 1), "senha123");

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem-vindo ao Sistema de Eventos!");

        // Loop principal do menu
        while (opcao != 6) {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1. Cadastrar novo evento");
            System.out.println("2. Listar todos os eventos");
            System.out.println("3. Confirmar participacao em evento");
            System.out.println("4. Cancelar participacao");
            System.out.println("5. Ver meus eventos confirmados");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas numeros.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n---- Cadastro de Evento ----");
                    System.out.print("Nome do evento: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.println("Categorias disponíveis: CONFERENCIAS, WORKSHOPS_TREINAMENTOS, SEMINARIOS_PALESTRAS, FEIRAS_EXPOSICOES, REUNIOES_ASSEMBLEIAS, OUTROS");
                    System.out.print("Categoria: ");
                    String categoriaStr = scanner.nextLine().toUpperCase();
                    try {
                        CategoriaEvento categoria = CategoriaEvento.valueOf(categoriaStr);
                        LocalDateTime horario = null;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        while (horario == null) {
                            System.out.print("Horário (dd/MM/yyyy HH:mm): ");
                            String horarioStr = scanner.nextLine();
                            try {
                                horario = LocalDateTime.parse(horarioStr, formatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de horário inválido. Use dd/MM/yyyy HH:mm.");
                            }
                        }
                        System.out.print("Descrição: ");
                        String descricao = scanner.nextLine();
                        Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);
                        sistema.adicionarEvento(novoEvento);
                        System.out.println("\nEvento '" + novoEvento.getNome() + "' cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Categoria inválida. Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println("\n---- Lista de Eventos Cadastrados ----");
                    List<Evento> eventosCadastrados = sistema.listarEventos();
                    if (eventosCadastrados.isEmpty()) {
                        System.out.println("Nenhum evento cadastrado ainda.");
                    } else {
                        for (Evento evento : eventosCadastrados) {
                            System.out.println("------------------------------------");
                            System.out.println("Nome: " + evento.getNome());
                            System.out.println("Endereço: " + evento.getEndereco());
                            System.out.println("Categoria: " + evento.getCategoria());
                            System.out.println("Horário: " + evento.getHorario());
                            System.out.println("Descrição: " + evento.getDescricao());
                            System.out.println("------------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n---- Confirmar Participacao ----");
                    List<Evento> eventosDisponiveis = sistema.listarEventos();
                    if (eventosDisponiveis.isEmpty()) {
                        System.out.println("Nenhum evento disponivel para confirmacao.");
                    } else {
                        System.out.println("Escolha um evento para confirmar participacao:");
                        for (int i = 0; i < eventosDisponiveis.size(); i++) {
                            Evento eventoAtual = eventosDisponiveis.get(i);
                            System.out.println((i + 1) + ". " + eventoAtual.getNome());
                        }
                        System.out.print("Digite o numero do evento: ");
                        int numeroEvento = scanner.nextInt();
                        scanner.nextLine();
                        if (numeroEvento > 0 && numeroEvento <= eventosDisponiveis.size()) {
                            Evento eventoEscolhido = eventosDisponiveis.get(numeroEvento - 1);
                            usuarioDoSistema.confirmarParticipacao(eventoEscolhido);
                            System.out.println("Participacao em '" + eventoEscolhido.getNome() + "' confirmada com sucesso!");
                        } else {
                            System.out.println("Numero de evento invalido.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n---- Cancelar Participacao ----");
                    List<Evento> eventosConfirmados = usuarioDoSistema.getEventosConfirmados();
                    if (eventosConfirmados.isEmpty()) {
                        System.out.println("Voce nao tem eventos confirmados para cancelar.");
                    } else {
                        System.out.println("Escolha um evento para cancelar a participacao:");
                        for (int i = 0; i < eventosConfirmados.size(); i++) {
                            Evento eventoAtual = eventosConfirmados.get(i);
                            System.out.println((i + 1) + ". " + eventoAtual.getNome());
                        }
                        System.out.print("Digite o numero do evento: ");
                        int numeroEvento = scanner.nextInt();
                        scanner.nextLine();
                        if (numeroEvento > 0 && numeroEvento <= eventosConfirmados.size()) {
                            Evento eventoEscolhido = eventosConfirmados.get(numeroEvento - 1);
                            usuarioDoSistema.cancelarParticipacao(eventoEscolhido);
                            System.out.println("Participacao em '" + eventoEscolhido.getNome() + "' cancelada com sucesso!");
                        } else {
                            System.out.println("Numero de evento invalido.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n---- Meus Eventos Confirmados ----");
                    eventosConfirmados = usuarioDoSistema.getEventosConfirmados();
                    if (eventosConfirmados.isEmpty()) {
                        System.out.println("Voce ainda nao confirmou participacao em nenhum evento.");
                    } else {
                        for (Evento evento : eventosConfirmados) {
                            System.out.println("------------------------------------");
                            System.out.println("Nome: " + evento.getNome());
                            System.out.println("Endereco: " + evento.getEndereco());
                            System.out.println("------------------------------------");
                        }
                    }
                    break;
                case 6:
                    sistema.salvarEventos(); // Chama o metodo para salvar antes de sair
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 6.");
                    break;
            }
        }

        scanner.close();
    }
}