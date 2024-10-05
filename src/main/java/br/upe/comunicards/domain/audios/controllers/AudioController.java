package br.upe.comunicards.domain.audios.controllers;

import br.upe.comunicards.domain.audios.models.AudioResponse;
import br.upe.comunicards.domain.audios.services.AudioService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/audios")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AudioController {
    AudioService audioService;

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getAudio(@PathVariable UUID id) {
        AudioResponse audioResponse = audioService.getAudio(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=audio.mp3")
                .contentLength(audioResponse.getContentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(audioResponse.getResource());
    }

}
