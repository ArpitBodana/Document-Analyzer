package com.absys.Document.Anaylzer.controller;

import com.absys.Document.Anaylzer.dto.ChunkResultDTO;
import com.absys.Document.Anaylzer.service.DocumentSummaryService;
import com.absys.Document.Anaylzer.service.RetrievalService;
import com.absys.Document.Anaylzer.utils.DocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final RetrievalService retrievalService;

    private final DocumentSummaryService documentSummaryService;

    @GetMapping("/query")
    public String query(@RequestParam String question,@RequestParam String filename) {

        List<ChunkResultDTO> chunks = retrievalService.retrieve(question,filename);
        DocumentType docType = retrievalService.retrieveDocumentType(filename);

        return documentSummaryService.summarize(chunks,docType);
    }
}