package com.user_mangement.User_Mangement.service;


import com.user_mangement.User_Mangement.dto.JobDto;
import com.user_mangement.User_Mangement.model.JobList;
import com.user_mangement.User_Mangement.model.User;
import com.user_mangement.User_Mangement.repository.JobRepository;
import com.user_mangement.User_Mangement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobDto addJob(JobDto jobDto,Long userId) {
        JobList jobList = new JobList();
        try{
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            jobList.setCompleted(jobDto.getIsDone());
            jobList.setJobDescription(jobDto.getJob_description());
            jobList.setJobName(jobDto.getJob_name());
            jobList.setUser(user);
            jobRepository.save(jobList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return jobDto;
    }
    public JobDto updateJob(Long id, JobDto jobDto) {
        JobList jobList = jobRepository.findById(id).orElseThrow();
        jobList.setJobDescription(jobDto.getJob_description());
        jobList.setJobName(jobDto.getJob_name());
        jobList.setCompleted(jobDto.getIsDone());

        jobRepository.save(jobList);
        return jobDto;

    }
    public JobDto getJob(Long id) {
        JobList jobList = jobRepository.findById(id).orElseThrow();

        JobDto jobDto = new JobDto();
        jobDto.setJob_name(jobList.getJobName());
        jobDto.setJob_description(jobList.getJobDescription());
        jobDto.setIsDone(jobList.isCompleted());
        return jobDto;
    }
    public List<JobList> getAllJob() {
        List<JobList> jobList = jobRepository.findAll();
        return jobList;

    }
    public void deleteJob(Long id) {
        JobList job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        // Xoá job
        jobRepository.delete(job);
    }
    public List<JobDto> getJobByUserId(Long userId) {
        List<JobList> jobLists = jobRepository.findByUserId(userId);
        return jobLists.stream()
                .map(job -> new JobDto(
                        job.getJobName(),
                        job.getJobDescription(),
                        job.isCompleted()))
                .collect(Collectors.toList());
    }

}
