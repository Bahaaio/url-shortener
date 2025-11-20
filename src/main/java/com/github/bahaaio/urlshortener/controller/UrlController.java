package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.dtos.UrlRequest;
import com.github.bahaaio.urlshortener.model.UrlMapping;
import com.github.bahaaio.urlshortener.services.UrlMappingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shorten")
public class UrlController {
    private final UrlMappingService urlMappingService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlMapping> getByCode(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlMappingService.getUrlByShortCode(shortCode));
    }

    @PostMapping
    public ResponseEntity<UrlMapping> createUrl(@RequestBody UrlRequest request) {
        var created = urlMappingService.createUrlMapping(request);

        return ResponseEntity.created(URI.create("/shorten/" + created.getShortCode()))
                .body(created);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlMapping> updatedUrl(@PathVariable String shortCode, @RequestBody UrlRequest request) {
        return ResponseEntity.ok(urlMappingService.updateUrl(shortCode, request));
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlMappingService.deleteUrlByShortCode(shortCode);
        return ResponseEntity.noContent().build();
    }
}
