package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.dtos.UrlStatsResponse;
import com.github.bahaaio.urlshortener.exception.UrlNotFoundException;
import com.github.bahaaio.urlshortener.mapper.UrlStatsMapper;
import com.github.bahaaio.urlshortener.model.UrlStats;
import com.github.bahaaio.urlshortener.repository.UrlStatsRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final UrlStatsRepository statsRepository;
    private final UrlStatsMapper urlStatsMapper;

    public void createStats(String shortCode) {
        var stats = UrlStats.builder()
                .shortCode(shortCode)
                .accessCount(0L)
                .build();

        statsRepository.save(stats);
    }

    public UrlStatsResponse getStats(String shortCode) {
        var urlStats = statsRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        return urlStatsMapper.toUrlStatsResponse(urlStats);
    }

    public void IncrementAccessCount(String shortCode) {
        statsRepository.incrementAccessCount(shortCode);
    }
}
