package com.absys.Document.Anaylzer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingService {

    private static final int MAX_CHUNK_SIZE = 1200;

    public List<String> chunkText(String text) {
        String[] paragraphs = text.split("\\n\\n");

        List<String> chunks = new ArrayList<>();

        StringBuilder currentChunk = new StringBuilder();

        for (String paragraph : paragraphs) {
            if (currentChunk.length() + paragraph.length() > MAX_CHUNK_SIZE) {
                chunks.add(currentChunk.toString());
                currentChunk = new StringBuilder();
            }
            currentChunk.append(paragraph).append("\\n");
        }
        if (!currentChunk.isEmpty()) {
            chunks.add(currentChunk.toString());
        }
        return chunks;
    }
}