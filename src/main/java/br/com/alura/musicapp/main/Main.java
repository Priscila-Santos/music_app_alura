package br.com.alura.musicapp.main;

import br.com.alura.musicapp.model.Artista;
import br.com.alura.musicapp.model.Musica;
import br.com.alura.musicapp.model.TipoArtista;
import br.com.alura.musicapp.repository.ArtistaRepository;
import br.com.alura.musicapp.service.ConsultaGemini;
import br.com.alura.musicapp.util.ArtistasFormatter;
import br.com.alura.musicapp.util.MusicasFormatter;
import br.com.alura.musicapp.util.PesquisaFormatter;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final ArtistaRepository artistaRepository;
    private Scanner leitura = new Scanner(System.in);

    public Main(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu =
                    """
                            ******** Welcome to Music App Alura! ********
                            1 -  Cadastrar Artista
                            2 -  Cadastrar Música
                            3 -  Pesquisar Música por Artistas
                            4 -  Listar Todos os Artistas
                            5 -  Pesquisar Artista
         
                            0 - Sair
                            """;

            System.out.println(menu);
            System.out.println("Escolha a opção desejada: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    buscarMusicaPorArtistas();
                    break;
                case 4:
                    listarTodosArtistas();
                    break;
                case 5:
                    pesquisarSobreArtista();
                    break;
                case 0:
                    System.out.println("Sair do Music App...");
                    break;
                default:
                    System.out.println("Opção Invalída");

            }
        }
    }

    private void cadastrarArtista() {
        var cadastraNovo = "S";
        while (cadastraNovo.equalsIgnoreCase("S")) {
            System.out.println("Didite o nome de um artista para cadastrar: ");
            var nomeArtista = leitura.nextLine();
            System.out.println("Informe o tipo do artista: (solo, dupla, banda) ");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nomeArtista, tipoArtista);
            artistaRepository.save(artista);
            System.out.println("Cadastrar novo artista? S/N");
            cadastraNovo = leitura.nextLine();
        }


    }

    private void cadastrarMusica() {
        System.out.println("Cadastrar Música de qual Artista: ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()) {
            System.out.println("Informe o título da Música: ");
            var tituloMusica = leitura.nextLine();
            Musica musica = new Musica(tituloMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado :(");
        }
    }

    private void buscarMusicaPorArtistas() {
        System.out.println("Digite o nome do Artista para encontrar suas músicas: ");
        var nome = leitura.nextLine();
        List<Musica> musicas = artistaRepository.buscarMusicaPorArtista(nome);

        for (Musica musica : musicas) {
            MusicasFormatter.printMusicas(musica);
        }
    }

    private void listarTodosArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(System.out::println);
        for (Artista artista : artistas) {
            ArtistasFormatter.printArtistas(artista);
        }
    }

    private void pesquisarSobreArtista() {
        System.out.println("Digite o nome de um artista: ");
        var nome = leitura.nextLine();
        var resposta = ConsultaGemini.obterInformacao(nome);
        PesquisaFormatter.printInformacaoArtista(nome, String.valueOf(resposta));
    }
}