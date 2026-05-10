package com.absys.Document.Anaylzer.service;


import com.absys.Document.Anaylzer.dto.ChunkResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegalSummaryService {

    private final ChatClient.Builder chatClientBuilder;

    public String summarize(List<ChunkResultDTO> chunks) {

        String context = chunks.stream().map(ChunkResultDTO::getContent).reduce("", (a, b) -> a + "\n" + b);

        String prompt = """
                You are an enterprise legal analyst.
                
                Analyze the document carefully.
                
                Rules:
                - Return ONLY bullet points
                - Maximum 10 bullets
                - Each bullet under 15 words
                - Ignore generic legal boilerplate
                - Focus on:
                  - obligations
                  - penalties
                  - risks
                  - deadlines
                  - payment terms
                  - termination clauses
                
                Document:
                %s
                """.formatted(context);

        ChatClient chatClient = chatClientBuilder.build();

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}