package com.github.bahaaio.urlshortener.dtos;

public record UrlStatsResponse(
        String code,
        Long accessCount
) {
}
