package com.github.bahaaio.urlshortener.dtos;

public record ShortenResponse(
        String code,
        String shortUrl
) {
}
