package com.absys.Document.Anaylzer.dto;

public interface ChunkResultDTO {
    Long getId();
    String getDocumentName();
    Integer getChunkIndex();
    String getContent();
    String getDocumentType();
}
