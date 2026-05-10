package com.absys.Document.Anaylzer.controller;

import com.absys.Document.Anaylzer.service.DocumentIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class UploadController {

    private final DocumentIngestionService service;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

        Integer chunks = service.ingest(file);

        return ResponseEntity.ok(
                Map.of("message", "Document uploaded",
                        "chunks", chunks));
    }
}