package com.github.bahaaio.urlshortener.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ShortenRequest(
        @NotBlank
        @URL
        @Size(max = 2048)
        String url
) {
}
