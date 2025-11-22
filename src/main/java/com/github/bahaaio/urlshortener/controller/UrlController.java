package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.dtos.UrlMappingRequest;
import com.github.bahaaio.urlshortener.dtos.UrlMappingResponse;
import com.github.bahaaio.urlshortener.services.StatsService;
import com.github.bahaaio.urlshortener.services.UrlMappingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/urls")
public class UrlController {
    private final UrlMappingService urlMappingService;
    private final StatsService statsService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlMappingResponse> getByCode(@PathVariable String shortCode) {
        statsService.IncrementAccessCount(shortCode);
        return ResponseEntity.ok(urlMappingService.getUrlByShortCode(shortCode));
    }

    @PostMapping
    public ResponseEntity<UrlMappingResponse> createUrl(@RequestBody UrlMappingRequest request) {
        var created = urlMappingService.createUrlMapping(request);
        statsService.createStats(created.shortCode());

        return ResponseEntity.created(URI.create("/shorten/" + created.shortCode()))
                .body(created);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlMappingResponse> updatedUrl(@PathVariable String shortCode, @RequestBody UrlMappingRequest request) {
        return ResponseEntity.ok(urlMappingService.updateUrl(shortCode, request));
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlMappingService.deleteUrlByShortCode(shortCode);
        return ResponseEntity.noContent().build();
    }
}
