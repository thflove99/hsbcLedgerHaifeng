package org.example.module.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @JsonProperty("identityId")
    private String identityId;

    @JsonProperty("accountName")
    private String accountName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String status;

}
