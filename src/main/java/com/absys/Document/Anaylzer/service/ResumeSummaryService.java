package com.absys.Document.Anaylzer.service;

import com.absys.Document.Anaylzer.dto.ChunkResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeSummaryService {

    private final ChatClient.Builder chatClientBuilder;

    public String summarize(List<ChunkResultDTO> chunks) {

        String context = chunks.stream()
                .map(ChunkResultDTO::getContent)
                .reduce("", (a, b) -> a + "\n" + b);

        String prompt = """
                You are an expert HR resume analyzer.

                TASK:
                Extract structured resume information.

                STRICT RULES:
                - Output ONLY valid JSON
                - Do NOT include explanations, markdown, or text
                - Do NOT use bullet points
                - Do NOT hallucinate information
                - If missing data, use null or empty arrays

                JSON SCHEMA:
                {
                  "name": "",
                  "title": "",
                  "summary": "",
                  "skills": [],
                  "experience": [
                    {
                      "company": "",
                      "role": "",
                      "highlights": []
                    }
                  ],
                  "achievements": [],
                  "certifications": [],
                  "education": [],
                  "seniority_level": "",
                  "domain_expertise": [],
                  "overall_rating": {
                    "score": 0,
                    "reason": ""
                  }
                }

                RATING RULE:
                - Score must be 1 to 10
                - Based on skills, experience depth, and achievements

                Resume:
                %s
                """.formatted(context);

        ChatClient chatClient = chatClientBuilder.build();

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}