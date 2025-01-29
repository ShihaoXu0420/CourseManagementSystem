package com.example.course_management_system.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private String status;
    private String message;
    private Object data;

    public Result(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
