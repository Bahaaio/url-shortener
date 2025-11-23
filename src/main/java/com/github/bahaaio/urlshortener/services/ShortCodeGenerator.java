package com.github.bahaaio.urlshortener.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortCodeGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${shortener.code-length:6}")
    private int CODE_LENGTH;

    public String generateShortCode() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            var randomIndex = secureRandom.nextInt(ALPHABET.length());
            builder.append(ALPHABET.charAt(randomIndex));
        }

        return builder.toString();
    }
}
