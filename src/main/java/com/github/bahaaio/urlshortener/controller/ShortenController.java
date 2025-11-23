package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.dtos.ShortenRequest;
import com.github.bahaaio.urlshortener.dtos.ShortenResponse;
import com.github.bahaaio.urlshortener.dtos.UpdateUrlRequest;
import com.github.bahaaio.urlshortener.services.StatsService;
import com.github.bahaaio.urlshortener.services.UrlShorteningService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shorten")
public class ShortenController {
    private final UrlShorteningService urlShorteningService;
    private final StatsService statsService;

    @Value("${shortener.base-url}")
    private String URL_BASE;

    @PostMapping
    public ResponseEntity<ShortenResponse> createUrl(@RequestBody ShortenRequest request) {
        var created = urlShorteningService.shortenUrl(request);
        statsService.createStats(created.code());

        var uri = URI.create(URL_BASE + "/" + created.code());

        return ResponseEntity.created(uri).body(created);
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ShortenResponse> updatedUrl(@PathVariable String code, @RequestBody UpdateUrlRequest request) {
        return ResponseEntity.ok(urlShorteningService.updateShortenedUrl(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String code) {
        urlShorteningService.deleteUrlByCode(code);
        return ResponseEntity.noContent().build();
    }
}
