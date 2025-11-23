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

    public String getUrlByCode(String code) {
        var urlMapping = urlMappingRepository.getUrlMappingByCode(code)
                .orElseThrow(UrlNotFoundException::new);

        return urlMapping.getOriginalUrl();
    }

    public ShortenResponse shortenUrl(ShortenRequest request) {
        var code = shortCodeGenerator.generateShortCode();

        var urlMapping = UrlMapping.builder()
                .code(code)
                .originalUrl(request.url())
                .build();

        return urlMapper.toUrlMappingResponse(urlMappingRepository.save(urlMapping));
    }

    public ShortenResponse updateShortenedUrl(String code, UpdateUrlRequest request) {
        var urlMapping = urlMappingRepository.getUrlMappingByCode(code)
                .orElseThrow(UrlNotFoundException::new);

        urlMapping.setOriginalUrl(request.url());
        return urlMapper.toUrlMappingResponse(urlMappingRepository.save(urlMapping));
    }

    public void deleteUrlByCode(String code) {
        if (!urlMappingRepository.existsByCode(code)) {
            throw new UrlNotFoundException();
        }

        urlMappingRepository.deleteByCode(code);
    }
}
