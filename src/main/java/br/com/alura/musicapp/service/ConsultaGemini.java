package br.com.alura.musicapp.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {
    public static GenerateContentResponse obterInformacao (String texto) {
        String modeloGemini = "gemini-2.0-flash-lite";
        String prompt = "Me fale sobre o artista: " + texto;

        Client client = new Client.Builder().apiKey(System.getenv("GEMINI_APIKEY")).build();

        GenerateContentResponse response;
        try{
            response = client.models.generateContent(
                    modeloGemini,
                    prompt,
                    null); // parâmetro de configurações adicionais
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
