package com.absys.Document.Anaylzer.repository;

import com.absys.Document.Anaylzer.dto.ChunkResultDTO;
import com.absys.Document.Anaylzer.entity.DocumentChunk;
import com.absys.Document.Anaylzer.utils.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, Long> {
    @Query(value = """
            SELECT
                id,
                document_name,
                chunk_index,
                content,
                document_type
            FROM document_chunks
            WHERE document_name = :docName
            ORDER BY embedding <=> CAST(:embedding AS vector)
            LIMIT 5
            """, nativeQuery = true)
    List<ChunkResultDTO> searchSimilar(
            @Param("docName") String docName,
            @Param("embedding") String embedding
    );

    @Query(value = """
        SELECT document_type
        FROM document_chunks
        WHERE document_name = :docName
        LIMIT 1
        """, nativeQuery = true)
    DocumentType findTypeByDocumentName(@Param("docName") String docName);
}
