package com.github.bahaaio.urlshortener.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record UpdateUrlRequest(
        @NotBlank @URL String url
) {
}
