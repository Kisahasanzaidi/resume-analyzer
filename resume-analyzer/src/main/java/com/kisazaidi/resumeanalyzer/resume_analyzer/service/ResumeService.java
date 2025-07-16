package com.kisazaidi.resumeanalyzer.resume_analyzer.service;

import com.kisazaidi.resumeanalyzer.resume_analyzer.entity.Resume;
import com.kisazaidi.resumeanalyzer.resume_analyzer.parser.ResumeParser;
import com.kisazaidi.resumeanalyzer.resume_analyzer.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume processResume(MultipartFile file) {
        ResumeParser parser = new ResumeParser();
        Map<String, Object> data = parser.parse(file);

        List<String> resumeSkills = (List<String>) data.get("skills");
        List<String> knownSkills = Arrays.asList("Java", "Spring Boot", "MySQL", "Hibernate");
        int matchCount = 0;

        for (String skill : resumeSkills) {
            if (knownSkills.contains(skill)) matchCount++;
        }

        int score = (int) ((matchCount / (double) knownSkills.size()) * 100);

        Resume resume = new Resume();
        resume.setName((String) data.get("name"));
        resume.setEmail((String) data.get("email"));
        resume.setExtractedSkills(resumeSkills);
        resume.setProjectDescriptions((List<String>) data.get("projects"));
        resume.setGithubUrl((String) data.get("github"));
        resume.setLinkedinUrl((String) data.get("linkedin"));
        resume.setMatchScore(score);

        resume.setJobTitles((List<String>) data.get("jobTitles"));
        resume.setExperienceDurations((List<String>) data.get("experienceDurations"));
        resume.setTotalExperienceYears(estimateExperienceYears((List<String>) data.get("experienceDurations")));

        return resumeRepository.save(resume);
    }

    private int estimateExperienceYears(List<String> durations) {
        return durations.size(); // Simplified: 1 duration = 1 year (can be improved later)
    }
}