package br.upe.comunicards.domain.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/audios")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AudioController {
    private final RestTemplate restTemplate;


    @GetMapping("/{texto}")
    public ResponseEntity<ByteArrayResource> getAudio(@PathVariable String texto) {
        String url = "https://translate.google.com/translate_tts?ie=UTF-8&q=" + texto + "&tl=pt&client=tw-ob";
        byte[] audioData = restTemplate.getForObject(url, byte[].class);
        ByteArrayResource resource = new ByteArrayResource(audioData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=audio.mp3")
                .contentLength(audioData.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
