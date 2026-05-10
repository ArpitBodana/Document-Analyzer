package com.absys.Document.Anaylzer.service;

import com.pgvector.PGvector;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public float[] createEmbedding(String text) {

        EmbeddingResponse response = embeddingModel.embedForResponse(List.of(text));

        return response.getResults()
                .get(0)
                .getOutput();
    }
}