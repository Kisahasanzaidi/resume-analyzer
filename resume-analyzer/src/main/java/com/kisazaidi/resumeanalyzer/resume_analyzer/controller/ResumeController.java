package com.kisazaidi.resumeanalyzer.resume_analyzer.controller;

import com.kisazaidi.resumeanalyzer.resume_analyzer.entity.Resume;
import com.kisazaidi.resumeanalyzer.resume_analyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<Resume> uploadResume(@RequestParam("file") MultipartFile file) {
        Resume resume = resumeService.processResume(file);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }
}
