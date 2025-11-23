package com.github.bahaaio.urlshortener.services;

import com.github.bahaaio.urlshortener.repository.UrlMappingRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortCodeGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final SecureRandom secureRandom = new SecureRandom();
    private final UrlMappingRepository urlMappingRepository;

    @Value("${shortener.code-length:6}")
    private int CODE_LENGTH;

    @Value("${shortener.max-tries:3}")
    private int MAX_TRIES;

    public String generateUniqueCode() {
        String code;
        int attempts = 0;

        do {
            code = generateCode();
            attempts++;
        } while (urlMappingRepository.existsByCode(code) && attempts < MAX_TRIES);

        if (attempts >= MAX_TRIES) {
            throw new RuntimeException("Unable to generate unique code");
        }

        return code;
    }

    private String generateCode() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            var randomIndex = secureRandom.nextInt(ALPHABET.length());
            builder.append(ALPHABET.charAt(randomIndex));
        }

        return builder.toString();
    }
}
