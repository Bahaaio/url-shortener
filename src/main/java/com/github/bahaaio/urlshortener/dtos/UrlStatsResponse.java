package com.github.bahaaio.urlshortener.dtos;

public record UrlStatsResponse(
        String shortCode,
        long accessCount
) {
}
