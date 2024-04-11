package org.example.module.dto;


import lombok.Data;

@Data
public class AccountUpdateRequest {
    private Long accountId;
    private String status;
}
   
