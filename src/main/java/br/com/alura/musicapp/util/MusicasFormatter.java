package br.com.alura.musicapp.util;

import br.com.alura.musicapp.model.Musica;

public class MusicasFormatter {

    public static void printMusicas(Musica musica) {
        System.out.println("""
                ------------------------------------------------------------
                🎧 Título da Música: %s
                🎤 Artista: %s
                ------------------------------------------------------------
                """.formatted(
                musica.getTitulo(),
                musica.getArtista() != null ? musica.getArtista().getNome() : "Desconhecido"
        ));
    }
}

