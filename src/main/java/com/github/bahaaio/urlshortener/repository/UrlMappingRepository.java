package com.github.bahaaio.urlshortener.repository;

import com.github.bahaaio.urlshortener.model.UrlMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import jakarta.transaction.Transactional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    @Transactional
    void deleteByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    Optional<UrlMapping> getUrlMappingByShortCode(String shortCode);
}
