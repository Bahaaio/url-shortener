package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.dtos.UrlMappingRequest;
import com.github.bahaaio.urlshortener.dtos.UrlMappingResponse;
import com.github.bahaaio.urlshortener.exception.UrlNotFoundException;
import com.github.bahaaio.urlshortener.mapper.UrlMapper;
import com.github.bahaaio.urlshortener.model.UrlMapping;
import com.github.bahaaio.urlshortener.repository.UrlMappingRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlMappingService {
    private final CodeGeneratorService codeGeneratorService;
    private final UrlMappingRepository urlMappingRepository;
    private final UrlMapper urlMapper;

    public UrlMappingResponse getUrlByShortCode(String shortCode) {
        var urlMapping = urlMappingRepository.getUrlMappingByShortCode(shortCode).
                orElseThrow(() -> new UrlNotFoundException(shortCode));

        return urlMapper.toUrlMappingResponse(urlMapping);
    }

    public UrlMappingResponse createUrlMapping(UrlMappingRequest request) {
        var shortCode = codeGeneratorService.shortenUrl(request.url());

        var urlMapping = UrlMapping.builder()
                .url(request.url())
                .shortCode(shortCode)
                .build();

        return urlMapper.toUrlMappingResponse(urlMappingRepository.save(urlMapping));
    }

    public UrlMappingResponse updateUrl(String shortCode, UrlMappingRequest request) {
        var urlMapping = urlMappingRepository.getUrlMappingByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        urlMapping.setUrl(request.url());
        return urlMapper.toUrlMappingResponse(urlMappingRepository.save(urlMapping));
    }

    public void deleteUrlByShortCode(String shortCode) {
        if (!urlMappingRepository.existsByShortCode(shortCode)) {
            throw new UrlNotFoundException(shortCode);
        }

        urlMappingRepository.deleteByShortCode(shortCode);
    }
}
