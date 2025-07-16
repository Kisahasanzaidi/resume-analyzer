package com.kisazaidi.resumeanalyzer.resume_analyzer.parser;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.*;

public class ResumeParser {

    public Map<String, Object> parse(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            String name = lines.get(0);
            String email = lines.stream().filter(l -> l.contains("@")).findFirst().orElse("not_found");
            result.put("name", name);
            result.put("email", email);

            List<String> skills = new ArrayList<>();
            List<String> knownSkills = Arrays.asList("Java", "Spring Boot", "MySQL", "Hibernate", "Python", "REST");
            for (String l : lines) {
                for (String skill : knownSkills) {
                    if (l.toLowerCase().contains(skill.toLowerCase()) && !skills.contains(skill)) {
                        skills.add(skill);
                    }
                }
            }
            result.put("skills", skills);

            List<String> projects = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).toLowerCase().contains("project")) {
                    for (int j = i + 1; j < lines.size() && !lines.get(j).trim().isEmpty(); j++) {
                        projects.add(lines.get(j).trim());
                    }
                    break;
                }
            }
            result.put("projects", projects);

            Pattern githubPattern = Pattern.compile("https?://(www\\.)?github\\.com/\\S+");
            Pattern linkedinPattern = Pattern.compile("https?://(www\\.)?linkedin\\.com/in/\\S+");

            String githubUrl = null, linkedinUrl = null;
            for (String l : lines) {
                Matcher gMatcher = githubPattern.matcher(l);
                if (gMatcher.find()) githubUrl = gMatcher.group();

                Matcher lMatcher = linkedinPattern.matcher(l);
                if (lMatcher.find()) linkedinUrl = lMatcher.group();
            }
            result.put("github", githubUrl);
            result.put("linkedin", linkedinUrl);

            List<String> jobTitles = new ArrayList<>();
            List<String> durations = new ArrayList<>();

            Pattern durationPattern = Pattern.compile("\\b(\\w+\\s\\d{4})\\s?-\\s?(\\w+\\s\\d{4}|Present)\\b", Pattern.CASE_INSENSITIVE);
            for (String l : lines) {
                if (l.toLowerCase().contains("developer") || l.toLowerCase().contains("engineer") || l.toLowerCase().contains("intern")) {
                    jobTitles.add(l.trim());
                }

                Matcher m = durationPattern.matcher(l);
                if (m.find()) {
                    durations.add(m.group());
                }
            }
            result.put("jobTitles", jobTitles);
            result.put("experienceDurations", durations);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
