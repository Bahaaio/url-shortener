package com.github.bahaaio.urlshortener.controller;

import com.github.bahaaio.urlshortener.services.StatsService;
import com.github.bahaaio.urlshortener.services.UrlShorteningService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final StatsService statsService;
    private final UrlShorteningService urlShorteningService;

    @GetMapping("/{code}")
    public void getByCode(@PathVariable String code, HttpServletResponse response) throws IOException {
        statsService.IncrementAccessCount(code);
        response.sendRedirect(urlShorteningService.getUrlByCode(code));
    }
}
