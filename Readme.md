# AI Document Analyzer (Spring Boot + LLM + Vector Search)
An AI-powered document processing system built with Spring Boot that supports document ingestion, chunking, embedding, semantic retrieval, classification, and intelligent summarization for legal and resume documents.

---

## 🚀 Features

- 📂 Upload and ingest documents (PDF)
- ✂️ Smart text chunking
- 🧠 AI-based document classification (Resume / Legal / General)
- 🔍 Vector embedding generation (semantic search)
- 📊 PostgreSQL + pgvector similarity search
- 🤖 AI-powered summarization using LLM (ChatClient)
- 📑 Structured JSON resume extraction
- ⚖️ Legal document bullet-point analysis
- ⚡ REST APIs for ingestion and querying

---

## 🏗️ Architecture Overview

    Client -->|Upload /api/documents/upload| Ingestion[DocumentIngestionService]
    Ingestion --> PDF[PDF Text Extraction]
    Ingestion --> Chunk[ChunkingService]
    Ingestion --> Classify[DocumentClassifierService - LLM]
    Ingestion --> Embed[EmbeddingService]
    Ingestion --> DB[(PostgreSQL + pgvector)]

    Client -->|Query /api/ai/query| Retrieval[RetrievalService - Vector Search]
    Retrieval --> Summary[DocumentSummaryService]
    Summary --> Legal[LegalSummaryService]
    Summary --> Resume[ResumeSummaryService]


---

## 📡 REST APIs

### 📤 Upload Document

POST /api/documents/upload

**Request:**

- `file` (MultipartFile)

**Response:**

```json
{
  "message": "Document uploaded",
  "chunks": 12
}
🔎 Query Document
GET /api/ai/query

Parameters:

question → User query
filename → Document name

Response:

AI-generated summary based on document type
🧠 Core Modules
1. 📥 Document Ingestion
Extracts text from uploaded files
Splits text into chunks (max 1200 chars)
Generates embeddings
Stores in PostgreSQL using pgvector
2. 🧩 Chunking Service
Splits large documents into semantic chunks
Preserves paragraph structure
3. 🧠 Document Classification (LLM)

Classifies document into:

RESUME
LEGAL
GENERAL
4. 🔍 Vector Search
Uses PostgreSQL embedding <=> vector
Returns top 5 similar chunks
5. 🤖 AI Summarization
📑 Legal Documents
Bullet-point extraction
Focus on:
obligations
risks
penalties
deadlines
👤 Resume Documents
Structured JSON output:
{
  "name": "",
  "skills": [],
  "experience": [],
  "education": [],
  "overall_rating": {}
}

🗄️ Database Schema
document_chunks
Column	Type
id	Long
document_name	String
chunk_index	Integer
content	Text
embedding	vector(768)
document_type	Enum

🧠 Tech Stack
Java 21
Spring Boot
Spring AI (ChatClient + Embeddings)
PostgreSQL + pgvector
Hibernate / JDBC
Lombok
Local LLM (Ollama):
    Inference: qwen2.5:3b
    Embeddings: nomic-embed-text:latest

⚙️ Key Services
DocumentIngestionService → Upload & processing pipeline
ChunkingService → Text segmentation
EmbeddingService → Vector generation
RetrievalService → Semantic search
DocumentClassifierService → LLM classification
DocumentSummaryService → Response routing
LegalSummaryService → Legal analysis
ResumeSummaryService → JSON resume extraction


📌 Example Flow
1. Upload Resume
POST /api/documents/upload
2. Query Resume
GET /api/ai/query?question=skills and experience&filename=resume.pdf
3. Output

Structured JSON with:

Skills
Experience
Education
Rating


👨‍💻 Author
Arpit Bodana
Java Developer 
Role: Architecture Design & Backend Implementation
GitHub: @ArpitBodana
LinkedIn: https://www.linkedin.com/in/arpitbodana99/

