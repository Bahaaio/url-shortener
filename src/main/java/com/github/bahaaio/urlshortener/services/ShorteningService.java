package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.repository.UrlRepository;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShorteningService {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LETTERS_LENGTH = 62;
    private static final int SHORT_CODE_LENGTH = 6;
    private static final int MAX_TRIES = 3;
    private final SecureRandom secureRandom = new SecureRandom();

    private final UrlRepository urlRepository;

    public String shortenUrl(String url) {
        String generatedCode;
        int tries = 1;

        do {
            generatedCode = GenerateRandomShortCode();
        } while (urlRepository.existsByShortCode(generatedCode) && tries++ < MAX_TRIES);

        return generatedCode;
    }

    private String GenerateRandomShortCode() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            var randomIndex = Math.abs(secureRandom.nextInt()) % LETTERS_LENGTH;
            builder.append(LETTERS.charAt(randomIndex));
        }

        return builder.toString();
    }
}
