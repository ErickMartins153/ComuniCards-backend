package br.upe.comunicards.domain.audios.services;

import br.upe.comunicards.domain.audios.models.AudioResponse;
import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.services.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AudioServiceImpl implements AudioService {
    private final RestTemplate restTemplate;
    private final CartaoService cartaoService;

    @Override
    public AudioResponse getAudio(UUID id) {
        Cartao cartao = cartaoService.getById(id);
        if (cartao == null) {
            throw new RuntimeException("Não foi possível achar um cartão com id: " + id);
        }
        String fraseCodificada;
        try {
            fraseCodificada = URLEncoder.encode(cartao.getFrase(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        if (fraseCodificada == null) {
            throw new RuntimeException("Algo deu errado");
        }
        String url = "https://translate.google.com/translate_tts?ie=UTF-8&q=" + fraseCodificada + "&tl=pt&client=tw-ob";
        byte[] audioData = restTemplate.getForObject(url, byte[].class);

        ByteArrayResource resource = new ByteArrayResource(audioData);
        return new AudioResponse(resource, audioData.length);
    }

}
