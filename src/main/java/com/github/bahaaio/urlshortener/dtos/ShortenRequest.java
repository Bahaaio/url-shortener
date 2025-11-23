package com.github.bahaaio.urlshortener.dtos;

import jakarta.validation.constraints.NotBlank;

public record ShortenRequest(
        @NotBlank String url
) {
}
