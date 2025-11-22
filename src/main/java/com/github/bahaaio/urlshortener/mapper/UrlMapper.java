package com.github.bahaaio.urlshortener.mapper;

import com.github.bahaaio.urlshortener.dtos.UrlMappingResponse;
import com.github.bahaaio.urlshortener.model.UrlMapping;

import org.mapstruct.Mapper;

@Mapper
public interface UrlMapper {
    UrlMappingResponse toUrlMappingResponse(UrlMapping urlMapping);
}
