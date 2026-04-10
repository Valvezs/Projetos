import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Streaming de Música - CP1
 */
public class StreamingMusica {

    static Playlist playlist = new Playlist("Minha Playlist");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n=== STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar título");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarPorTitulo();
                break;
            case 0:
                break;
            default:
                System.out.println("Tente novamente.");
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        int duracao = 0;
        try {
            System.out.print("Duração (segundos): ");
            duracao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, duração definida como 0.");
        }

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        Musica musica = new Musica(titulo, artista, duracao, genero);
        playlist.adicionarMusica(musica);

        System.out.println("Música cadastrada!");
    }

    public static void listarMusicas() {
        System.out.println("\n--- PLAYLIST MÚSICAS ---");

        if (playlist.getMusicas().isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        int i = 1;
        for (Musica m : playlist.getMusicas()) {
            System.out.printf("%d. %s - %s (%s) [%s]\n",
                    i++,
                    m.getTitulo(),
                    m.getArtista(),
                    formatarDuracao(m.getDuracao()),
                    m.getGenero());
        }
    }

    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");
        System.out.print("Título: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrado = false;
        int i = 1;
        for (Musica m : playlist.getMusicas()) {
            if (m.getTitulo().toLowerCase().contains(busca)) {
                System.out.printf("%d. %s - %s (%s) [%s]\n",
                        i,
                        m.getTitulo(),
                        m.getArtista(),
                        formatarDuracao(m.getDuracao()),
                        m.getGenero());
                encontrado = true;
            }
            i++;
        }

        if (!encontrado) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    // Classe Musica
    static class Musica {
        private String titulo;
        private String artista;
        private int duracao;
        private String genero;

        public Musica() {
            this("Sem Título", "Sem Artista", 0, "Sem Gênero");
        }

        public Musica(String titulo, String artista, int duracao, String genero) {
            setTitulo(titulo);
            setArtista(artista);
            setDuracao(duracao);
            setGenero(genero);
        }

        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) {
            this.titulo = (titulo == null || titulo.trim().isEmpty()) ? "Sem Título" : titulo;
        }

        public String getArtista() { return artista; }
        public void setArtista(String artista) {
            this.artista = (artista == null || artista.trim().isEmpty()) ? "Sem Artista" : artista;
        }

        public int getDuracao() { return duracao; }
        public void setDuracao(int duracao) {
            this.duracao = (duracao < 0) ? 0 : duracao;
        }

        public String getGenero() { return genero; }
        public void setGenero(String genero) {
            this.genero = (genero == null || genero.trim().isEmpty()) ? "Sem Gênero" : genero;
        }
    }

    // Classe Playlist
    static class Playlist {
        private String nome;
        private ArrayList<Musica> musicas;

        public Playlist() {
            this("Sem nome");
        }

        public Playlist(String nome) {
            this.nome = (nome == null || nome.trim().isEmpty()) ? "Playlist sem nome" : nome;
            this.musicas = new ArrayList<>();
        }

        public String getNome() { return nome; }
        public void setNome(String nome) {
            this.nome = (nome == null || nome.trim().isEmpty()) ? "Playlist sem nome" : nome;
        }

        public ArrayList<Musica> getMusicas() {
            return new ArrayList<>(musicas);
        }

        public void adicionarMusica(Musica musica) {
            if (musica != null) {
                this.musicas.add(musica);
            }
        }
    }

    public static void adicionarMusicasTeste() {
        playlist.adicionarMusica(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        playlist.adicionarMusica(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }
}
