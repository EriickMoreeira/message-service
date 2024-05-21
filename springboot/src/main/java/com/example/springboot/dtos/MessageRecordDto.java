package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record MessageRecordDto(@NotBlank String message, @NotBlank String author ) {

}
