package com.craft.shorturl.service;

import com.craft.shorturl.model.ShortUrlEntity;
import com.craft.shorturl.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShortUrlEntityService {
    private final ShortUrlRepository repository;

    public boolean save(String original, String shortenedUrl, LocalDateTime expiryDate) {
        try {
            repository.save(new ShortUrlEntity(shortenedUrl, original, expiryDate));
        } catch (IllegalStateException e) {
            System.out.println("Some unexpected exception has occurred");
            return false;
        } catch (DataIntegrityViolationException e) {
            System.out.println("Constraint Violation occurred");
            return false;
        }
        return true;
    }

    public Optional<String> retrieveOriginalUrl(String shortUrl) {
        ShortUrlEntity entity = repository.findByShortUrl(shortUrl);
        if (ObjectUtils.isEmpty(entity))
            return Optional.empty();
        if (isUrlExpired(entity))
            return Optional.empty();
        return Optional.ofNullable(entity.getOriginalUrl());
    }

    private boolean isUrlExpired(ShortUrlEntity entity) {
        return entity.getExpiryDate().isBefore(LocalDateTime.now());
    }
}
