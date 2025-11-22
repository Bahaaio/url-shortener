package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.dtos.UrlStatsResponse;
import com.github.bahaaio.urlshortener.services.StatsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stats")
public class StatisticsController {
    private final StatsService statsService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlStatsResponse> getStats(@PathVariable String shortCode) {
        return ResponseEntity.ok(statsService.getStats(shortCode));
    }
}
