package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.dtos.UrlStatsResponse;
import com.github.bahaaio.urlshortener.exception.UrlNotFoundException;
import com.github.bahaaio.urlshortener.mapper.UrlStatsMapper;
import com.github.bahaaio.urlshortener.repository.UrlMappingRepository;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final UrlMappingRepository mappingRepository;
    private final UrlStatsMapper urlStatsMapper;

    public UrlStatsResponse getStats(String code) {
        var urlStats = mappingRepository.findByCode(code)
                .orElseThrow(UrlNotFoundException::new);

        return urlStatsMapper.toUrlStatsResponse(urlStats);
    }

    @Transactional
    public void incrementAccessCount(String code) {
        mappingRepository.incrementAccessCount(code);
    }
}
