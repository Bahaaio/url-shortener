package com.github.bahaaio.urlshortener.mapper;

import com.github.bahaaio.urlshortener.dtos.UrlStatsResponse;
import com.github.bahaaio.urlshortener.model.UrlStats;

import org.mapstruct.Mapper;

@Mapper
public interface UrlStatsMapper {
    UrlStatsResponse toUrlStatsResponse(UrlStats urlStats);
}
