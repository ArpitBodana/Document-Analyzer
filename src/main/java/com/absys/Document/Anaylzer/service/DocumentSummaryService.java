package com.absys.Document.Anaylzer.service;

import com.absys.Document.Anaylzer.dto.ChunkResultDTO;
import com.absys.Document.Anaylzer.utils.DocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentSummaryService {

    private final LegalSummaryService legalSummaryService;
    private final ResumeSummaryService resumeSummaryService;

    public String summarize(List<ChunkResultDTO> chunks, DocumentType type) {
        return switch (type) {
            case LEGAL -> legalSummaryService.summarize(chunks);
            case RESUME -> resumeSummaryService.summarize(chunks);
            default -> "Unsupported document type for summarization";
        };
    }
}