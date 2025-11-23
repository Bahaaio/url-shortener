package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.services.StatsService;
import com.github.bahaaio.urlshortener.services.UrlShorteningService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final StatsService statsService;
    private final UrlShorteningService urlShorteningService;

    @GetMapping("/{code}")
    public ResponseEntity<Void> getByCode(@PathVariable String code) {
        statsService.IncrementAccessCount(code);

        var url = urlShorteningService.getUrlByCode(code);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", url)
                .build();
    }
}
