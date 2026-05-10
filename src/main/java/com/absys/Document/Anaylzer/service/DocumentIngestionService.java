package com.absys.Document.Anaylzer.service;

import com.absys.Document.Anaylzer.repository.DocumentChunkRepository;
import com.absys.Document.Anaylzer.utils.DocumentType;
import com.absys.Document.Anaylzer.utils.VectorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentIngestionService {

    private final PdfService pdfService;

    private final ChunkingService chunkingService;

    private final EmbeddingService embeddingService;

    private final DocumentChunkRepository repository;

    private final VectorUtils vectorUtils;

    private final JdbcTemplate jdbcTemplate;

    private final DocumentClassifierService classifierService;

    public Integer ingest(MultipartFile file) {

        String text = pdfService.extractText(file);

        DocumentType type = classifierService.classify(text);

        List<String> chunks = chunkingService.chunkText(text);

        int index = 0;

        for (String chunk : chunks) {

            float[] embedding = embeddingService.createEmbedding(chunk);
            String vectorString = vectorUtils.toVectorString(embedding);

//            DocumentChunk entity = DocumentChunk.builder()
//                    .documentName(file.getOriginalFilename())
//                    .chunkIndex(index++)
//                    .content(chunk)
//                    .embedding(embedding)
//                    .documentType(type)
//                    .build();
//
//
//            repository.save(entity);

            jdbcTemplate.update(
                    "INSERT INTO document_chunks " +
                            "(document_name, chunk_index, content, document_type, embedding) " +
                            "VALUES (?, ?, ?, ?, ?::vector)",
                    file.getOriginalFilename(),
                    index++,
                    chunk,
                    type.name(),
                    vectorString
            );

        }

        return chunks.size();
    }


}