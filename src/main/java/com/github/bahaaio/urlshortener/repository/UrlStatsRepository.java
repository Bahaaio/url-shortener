package com.github.bahaaio.urlshortener.repository;

import com.github.bahaaio.urlshortener.model.UrlStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import jakarta.transaction.Transactional;

public interface UrlStatsRepository extends JpaRepository<UrlStats, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE UrlStats s SET s.accessCount = s.accessCount + 1 WHERE s.shortCode = :shortCode")
    void incrementAccessCount(@Param("shortCode") String shortCode);

    Optional<UrlStats> findByShortCode(String shortCode);
}
