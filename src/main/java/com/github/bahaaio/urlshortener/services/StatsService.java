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

    public void createStats(String code) {
        var stats = UrlStats.builder()
                .code(code)
                .accessCount(0L)
                .build();

        statsRepository.save(stats);
    }

    public UrlStatsResponse getStats(String code) {
        var urlStats = statsRepository.findByCode(code)
                .orElseThrow(UrlNotFoundException::new);

        return urlStatsMapper.toUrlStatsResponse(urlStats);
    }

    public void IncrementAccessCount(String code) {
        statsRepository.incrementAccessCount(code);
    }
}
