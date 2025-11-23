package com.github.bahaaio.urlshortener.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record ShortenRequest(
        @NotBlank @URL String url
) {
}
