package com.github.bahaaio.urlshortener.repository;

import com.github.bahaaio.urlshortener.model.UrlMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import jakarta.transaction.Transactional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    @Transactional
    void deleteByCode(String code);

    boolean existsByCode(String code);

    Optional<UrlMapping> getUrlMappingByCode(String code);
}
