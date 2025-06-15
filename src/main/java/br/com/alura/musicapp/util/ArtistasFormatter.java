package br.com.alura.musicapp.util;

import br.com.alura.musicapp.model.Artista;

public class ArtistasFormatter {

    public static void printArtistas(Artista artista) {
        System.out.println("""
                ============================================================
                ✨ Nome do Artista: %s ✨
                🎤 Tipo: %s
                🎧 Número de músicas cadastradas: %d
                ============================================================
                """.formatted(
                artista.getNome(),
                artista.getTipoArtista(),
                artista.getMusicas().size()
        ));
    }
}
