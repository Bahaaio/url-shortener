package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.repository.UrlRepository;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeGeneratorService {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ALPHABET_LENGTH = ALPHABET.length();
    private static final int CODE_LENGTH = 6;
    private static final int MAX_TRIES = 3;
    private final SecureRandom secureRandom = new SecureRandom();

    private final UrlRepository urlRepository;

    public String shortenUrl(String url) {
        for (int tries = 0; tries < MAX_TRIES; tries++) {
            var generated = generateRandomShortCode();

            if (!urlRepository.existsByShortCode(generated)) {
                return generated;
            }
        }

        throw new RuntimeException("Failed to generate unique short code");
    }

    private String generateRandomShortCode() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            var randomIndex = Math.abs(secureRandom.nextInt()) % ALPHABET_LENGTH;
            builder.append(ALPHABET.charAt(randomIndex));
        }

        return builder.toString();
    }
}
