package com.github.bahaaio.urlshortener.dtos;

import java.time.LocalDateTime;

public record UrlResponse(
        String code,
        String shortUrl,
        String originalUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
