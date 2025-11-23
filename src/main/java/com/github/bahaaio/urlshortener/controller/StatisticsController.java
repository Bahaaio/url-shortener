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
@RequestMapping("/api/v1/urls")
public class StatisticsController {
    private final StatsService statsService;

    @GetMapping("/{code}/stats")
    public ResponseEntity<UrlStatsResponse> getStats(@PathVariable String code) {
        return ResponseEntity.ok(statsService.getStats(code));
    }
}
