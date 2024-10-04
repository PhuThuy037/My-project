package com.user_mangement.User_Mangement.controller;

import com.user_mangement.User_Mangement.dto.JobDto;
import com.user_mangement.User_Mangement.model.JobList;
import com.user_mangement.User_Mangement.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    @GetMapping("/all")
    public ResponseEntity<List<JobList>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJob());
    }
    @GetMapping("/get-job/{userId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long userId) {
        return ResponseEntity.ok(jobService.getJob(userId));
    }
    @PostMapping("/add-job/{userId}")
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto, @PathVariable Long userId) {
        return ResponseEntity.ok(jobService.addJob(jobDto,userId));
    }
    @PostMapping("/update-job/{userId}")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long userId, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.updateJob(userId, jobDto));
    }
    @DeleteMapping("/delete-job/{userId}")
    public void deleteJob(@PathVariable Long userId) {
        jobService.deleteJob(userId);
    }
    @GetMapping("/get-user-job/{userId}")
    public ResponseEntity<List<JobDto>> getUserJobById(@PathVariable Long userId) {
        return ResponseEntity.ok(jobService.getJobByUserId(userId));
    }

}
