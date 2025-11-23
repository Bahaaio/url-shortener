package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.dtos.ShortenResponse;
import com.github.bahaaio.urlshortener.dtos.UpdateUrlRequest;
import com.github.bahaaio.urlshortener.dtos.UrlResponse;
import com.github.bahaaio.urlshortener.mapper.UrlMapper;
import com.github.bahaaio.urlshortener.services.UrlShorteningService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/urls")
public class UrlController {
    private final UrlShorteningService urlShorteningService;
    private final UrlMapper urlMapper;

    @GetMapping("/{code}")
    public ResponseEntity<UrlResponse> getMetaData(@PathVariable String code) {
        var urlMapping = urlShorteningService.getUrlMappingByCode(code);
        var response = urlMapper.toUrlResponse(urlMapping);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ShortenResponse> updatedUrl(@PathVariable String code, @Valid @RequestBody UpdateUrlRequest request) {
        return ResponseEntity.ok(urlShorteningService.updateShortenedUrl(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String code) {
        urlShorteningService.deleteUrlByCode(code);
        return ResponseEntity.noContent().build();
    }
}
