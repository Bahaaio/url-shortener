package com.github.bahaaio.urlshortener.dtos;

import jakarta.validation.constraints.NotBlank;

public record UrlMappingRequest(
        @NotBlank String url
) {
}
