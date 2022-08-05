package com.craft.shorturl.repository;

import com.craft.shorturl.model.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Integer> {
    ShortUrlEntity findByShortUrl(String url);
}
