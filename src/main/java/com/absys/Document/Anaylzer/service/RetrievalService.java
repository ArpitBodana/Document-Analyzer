package com.absys.Document.Anaylzer.service;

import com.absys.Document.Anaylzer.dto.ChunkResultDTO;
import com.absys.Document.Anaylzer.repository.DocumentChunkRepository;
import com.absys.Document.Anaylzer.utils.DocumentType;
import com.absys.Document.Anaylzer.utils.VectorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrievalService {

    private final EmbeddingService embeddingService;

    private final DocumentChunkRepository repository;

    private final VectorUtils vectorUtils;

    public List<ChunkResultDTO> retrieve(String question, String filename) {

        float[] embedding = embeddingService.createEmbedding(question);
        String vectorString = vectorUtils.toVectorString(embedding);

        return repository.searchSimilar(filename, vectorString);
    }

    public DocumentType retrieveDocumentType(String filename) {
        return repository.findTypeByDocumentName(filename);
    }
}