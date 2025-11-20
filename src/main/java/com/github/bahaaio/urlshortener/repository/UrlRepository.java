package com.github.bahaaio.urlshortener.repository;

import com.github.bahaaio.urlshortener.model.UrlMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
    void deleteByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    Optional<UrlMapping> getUrlMappingByShortCode(String shortCode);
}
