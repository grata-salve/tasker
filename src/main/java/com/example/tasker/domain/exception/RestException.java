package com.example.tasker.domain.exception;

import lombok.Builder;

@Builder
public record RestException(String errorMessage) {

}
