package com.github.bahaaio.urlshortener.repository;

import com.github.bahaaio.urlshortener.model.UrlMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import jakarta.transaction.Transactional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    @Transactional
    void deleteByCode(String code);

    boolean existsByCode(String code);

    Optional<UrlMapping> findByCode(String code);

    @Transactional
    @Modifying
    @Query("update UrlMapping m SET m.accessCount = m.accessCount + 1 WHERE m.code = :code")
    void incrementAccessCount(@Param("code") String code);
}
