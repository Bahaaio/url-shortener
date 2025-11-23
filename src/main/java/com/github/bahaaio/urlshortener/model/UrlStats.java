package com.github.bahaaio.urlshortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlStats {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    String code;

    private Long accessCount;
}
