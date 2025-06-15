package br.com.alura.musicapp.util;

public class PesquisaFormatter {

    public static void printInformacaoArtista(String nomeArtista, String resposta) {
        System.out.println("""
                🔍 Informações sobre: %s
                ==================================
                %s
                ==================================
                """.formatted(nomeArtista, resposta));
    }
}

