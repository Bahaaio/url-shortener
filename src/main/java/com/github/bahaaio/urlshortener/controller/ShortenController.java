package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.dtos.ShortenRequest;
import com.github.bahaaio.urlshortener.dtos.ShortenResponse;
import com.github.bahaaio.urlshortener.services.UrlShorteningService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shorten")
public class ShortenController {
    private final UrlShorteningService urlShorteningService;

    @PostMapping
    public ResponseEntity<ShortenResponse> createUrl(@RequestBody ShortenRequest request) {
        var response = urlShorteningService.shortenUrl(request);

        return ResponseEntity
                .created(URI.create(response.shortUrl()))
                .body(response);
    }
}
