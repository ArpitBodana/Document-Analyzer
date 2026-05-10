package com.absys.Document.Anaylzer.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResumeAnalysisDTO {

    private String name;
    private String title;
    private String summary;

    private List<String> skills;

    private List<ExperienceDTO> experience;

    private List<String> achievements;
    private List<String> certifications;
    private List<String> education;

    private String seniorityLevel;
    private List<String> domainExpertise;

    private RatingDTO overallRating;

    @Data
    public static class ExperienceDTO {
        private String company;
        private String role;
        private List<String> highlights;
    }

    @Data
    public static class RatingDTO {
        private int score;
        private String reason;
    }
}