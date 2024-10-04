package com.user_mangement.User_Mangement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job")
public class JobList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private String jobDescription;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    User user;
    private boolean isCompleted;
}
