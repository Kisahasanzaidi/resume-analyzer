package com.kisazaidi.resumeanalyzer.resume_analyzer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ElementCollection
    private List<String> extractedSkills;

    @ElementCollection
    private List<String> projectDescriptions;

    @ElementCollection
    private List<String> jobTitles;

    @ElementCollection
    private List<String> experienceDurations;

    private String linkedinUrl;
    private String githubUrl;

    private Integer matchScore;
    private Integer totalExperienceYears;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getExtractedSkills() {
        return extractedSkills;
    }

    public void setExtractedSkills(List<String> extractedSkills) {
        this.extractedSkills = extractedSkills;
    }

    public List<String> getProjectDescriptions() {
        return projectDescriptions;
    }

    public void setProjectDescriptions(List<String> projectDescriptions) {
        this.projectDescriptions = projectDescriptions;
    }

    public List<String> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(List<String> jobTitles) {
        this.jobTitles = jobTitles;
    }

    public List<String> getExperienceDurations() {
        return experienceDurations;
    }

    public void setExperienceDurations(List<String> experienceDurations) {
        this.experienceDurations = experienceDurations;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Integer getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Integer matchScore) {
        this.matchScore = matchScore;
    }

    public Integer getTotalExperienceYears() {
        return totalExperienceYears;
    }

    public void setTotalExperienceYears(Integer totalExperienceYears) {
        this.totalExperienceYears = totalExperienceYears;
    }
}


