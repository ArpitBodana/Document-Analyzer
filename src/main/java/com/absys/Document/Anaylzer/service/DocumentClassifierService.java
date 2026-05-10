package com.absys.Document.Anaylzer.service;

import com.absys.Document.Anaylzer.utils.DocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentClassifierService {

    private final ChatClient.Builder chatClientBuilder;

    public DocumentType classify(String text) {

        String prompt = """
                You are a document classifier.
                
                Classify the document into ONLY ONE category:
                - LEGAL
                - RESUME
                - GENERAL
                
                Rules:
                - If it contains job experience, skills, education → RESUME
                - If it contains contracts, agreements, legal terms → LEGAL
                - Otherwise → GENERAL
                
                Return ONLY the category name, nothing else.
                
                Document:
                %s
                """.formatted(trim(text));

        ChatClient chatClient = chatClientBuilder.build();

        String response = chatClient
                .prompt(prompt)
                .call()
                .content()
                .trim()
                .toUpperCase();

        return mapToEnum(response);
    }

    private DocumentType mapToEnum(String value) {
        return switch (value) {
            case "LEGAL" -> DocumentType.LEGAL;
            case "RESUME" -> DocumentType.RESUME;
            default -> DocumentType.GENERAL;
        };
    }

    private String trim(String text) {
        return text.length() > 3000 ? text.substring(0, 3000) : text;
    }
}