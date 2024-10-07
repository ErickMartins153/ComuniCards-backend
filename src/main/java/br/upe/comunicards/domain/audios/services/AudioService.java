package br.upe.comunicards.domain.audios.services;

import br.upe.comunicards.domain.audios.models.AudioResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface AudioService {

    public AudioResponse getAudio(UUID id);
}
