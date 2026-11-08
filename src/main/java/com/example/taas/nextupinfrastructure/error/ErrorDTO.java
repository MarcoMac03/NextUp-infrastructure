package com.example.taas.nextupinfrastructure.error;

public record ErrorDTO(int status, String error, String message, String path) {
}
