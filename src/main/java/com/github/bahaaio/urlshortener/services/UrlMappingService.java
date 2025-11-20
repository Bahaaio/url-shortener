package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.dtos.UrlRequest;
import com.github.bahaaio.urlshortener.exception.UrlNotFoundException;
import com.github.bahaaio.urlshortener.model.UrlMapping;
import com.github.bahaaio.urlshortener.repository.UrlRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlMappingService {
    private final CodeGeneratorService codeGeneratorService;
    private final UrlRepository urlRepository;

    public UrlMapping getUrlByShortCode(String shortCode) {
        return urlRepository.getUrlMappingByShortCode(shortCode).
                orElseThrow(() -> new UrlNotFoundException(shortCode));
    }

    public UrlMapping createUrlMapping(UrlRequest request) {
        var shortCode = codeGeneratorService.shortenUrl(request.url());

        var urlMapping = UrlMapping.builder()
                .url(request.url())
                .shortCode(shortCode)
                .build();

        return urlRepository.save(urlMapping);
    }

    public UrlMapping updateUrl(String shortCode, UrlRequest request) {
        var urlMapping = urlRepository.getUrlMappingByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        urlMapping.setUrl(request.url());
        return urlRepository.save(urlMapping);
    }

    public void deleteUrlByShortCode(String shortCode) {
        if (!urlRepository.existsByShortCode(shortCode)) {
            throw new UrlNotFoundException(shortCode);
        }

        urlRepository.deleteByShortCode(shortCode);
    }
}
