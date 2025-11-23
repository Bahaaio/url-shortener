package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.dtos.ShortenRequest;
import com.github.bahaaio.urlshortener.dtos.ShortenResponse;
import com.github.bahaaio.urlshortener.exception.UrlNotFoundException;
import com.github.bahaaio.urlshortener.mapper.UrlMapper;
import com.github.bahaaio.urlshortener.model.UrlMapping;
import com.github.bahaaio.urlshortener.repository.UrlMappingRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlShorteningService {
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlMappingRepository urlMappingRepository;
    private final UrlMapper urlMapper;

    @Value("${shortener.base-url}")
    private String URL_BASE;

    public UrlMapping getUrlMappingByCode(String code) {
        return urlMappingRepository.findByCode(code).orElseThrow(UrlNotFoundException::new);
    }

    public String getUrlByCode(String code) {
        var urlMapping = urlMappingRepository.findByCode(code)
                .orElseThrow(UrlNotFoundException::new);

        return urlMapping.getOriginalUrl();
    }

    public ShortenResponse shortenUrl(ShortenRequest request) {
        var code = shortCodeGenerator.generateCode();

        var urlMapping = UrlMapping.builder()
                .code(code)
                .shortUrl(URL_BASE + "/" + code)
                .originalUrl(request.url())
                .build();

        return urlMapper.toShortenResponse(urlMappingRepository.save(urlMapping));
    }

    public ShortenResponse updateShortenedUrl(String code, ShortenRequest request) {
        var urlMapping = urlMappingRepository.findByCode(code)
                .orElseThrow(UrlNotFoundException::new);

        urlMapping.setOriginalUrl(request.url());
        return urlMapper.toShortenResponse(urlMappingRepository.save(urlMapping));
    }

    public void deleteUrlByCode(String code) {
        if (!urlMappingRepository.existsByCode(code)) {
            throw new UrlNotFoundException();
        }

        urlMappingRepository.deleteByCode(code);
    }
}
