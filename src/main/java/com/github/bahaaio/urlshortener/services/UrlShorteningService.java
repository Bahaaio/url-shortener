package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.dtos.ShortenRequest;
import com.github.bahaaio.urlshortener.dtos.ShortenResponse;
import com.github.bahaaio.urlshortener.dtos.UpdateUrlRequest;
import com.github.bahaaio.urlshortener.exception.UrlNotFoundException;
import com.github.bahaaio.urlshortener.mapper.UrlMapper;
import com.github.bahaaio.urlshortener.model.UrlMapping;
import com.github.bahaaio.urlshortener.repository.UrlMappingRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlShorteningService {
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlMappingRepository urlMappingRepository;
    private final UrlMapper urlMapper;

    public String getUrlByShortCode(String shortCode) {
        var urlMapping = urlMappingRepository.getUrlMappingByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        return urlMapping.getOriginalUrl();
    }

    public ShortenResponse shortenUrl(ShortenRequest request) {
        var shortCode = shortCodeGenerator.generateShortCode();

        var urlMapping = UrlMapping.builder()
                .shortCode(shortCode)
                .originalUrl(request.url())
                .build();

        return urlMapper.toUrlMappingResponse(urlMappingRepository.save(urlMapping));
    }

    public ShortenResponse updateShortenedUrl(String shortCode, UpdateUrlRequest request) {
        var urlMapping = urlMappingRepository.getUrlMappingByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        urlMapping.setOriginalUrl(request.url());
        return urlMapper.toUrlMappingResponse(urlMappingRepository.save(urlMapping));
    }

    public void deleteUrlByShortCode(String shortCode) {
        if (!urlMappingRepository.existsByShortCode(shortCode)) {
            throw new UrlNotFoundException(shortCode);
        }

        urlMappingRepository.deleteByShortCode(shortCode);
    }
}
