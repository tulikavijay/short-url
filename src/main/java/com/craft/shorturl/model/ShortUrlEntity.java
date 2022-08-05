package com.craft.shorturl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "URL")
@NoArgsConstructor
@Getter
public class ShortUrlEntity {
    @Id
    private String shortUrl;
    @Column(unique = true)
    private String originalUrl;
    private LocalDateTime expiryDate;

    public ShortUrlEntity(String shortenedUrl, String original, LocalDateTime expiryDate) {
        this.originalUrl = original;
        this.shortUrl = shortenedUrl;
        this.expiryDate = expiryDate;
    }
}
