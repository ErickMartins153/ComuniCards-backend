package br.upe.comunicards.domain.audios.models;

import org.springframework.core.io.ByteArrayResource;

public class AudioResponse {
    private final ByteArrayResource resource;
    private final long contentLength;

    public AudioResponse(ByteArrayResource resource, long contentLength) {
        this.resource = resource;
        this.contentLength = contentLength;
    }

    public ByteArrayResource getResource() {
        return resource;
    }

    public long getContentLength() {
        return contentLength;
    }
}
