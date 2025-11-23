package com.github.bahaaio.urlshortener.mapper;

import com.github.bahaaio.urlshortener.dtos.ShortenResponse;
import com.github.bahaaio.urlshortener.dtos.UrlResponse;
import com.github.bahaaio.urlshortener.model.UrlMapping;

import org.mapstruct.Mapper;

@Mapper
public interface UrlMapper {

    ShortenResponse toShortenResponse(UrlMapping urlMapping);

    UrlResponse toUrlResponse(UrlMapping urlMapping);
}
