package com.github.bahaaio.urlshortener.dtos;

import java.time.LocalDateTime;

public record UrlMappingResponse(
        String url,
        String shortCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
