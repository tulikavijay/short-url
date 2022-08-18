package com.craft.shorturl.service;

import com.craft.shorturl.dto.Request;
import com.craft.shorturl.exception.EmptyURLException;
import com.craft.shorturl.exception.ErrorWhileSavingToDB;
import com.craft.shorturl.exception.URLNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.craft.shorturl.service.EncodingAndHashingService.generateShortUrl;

@Service
@RequiredArgsConstructor
public class ShortUrlServiceWithHash implements ShortUrlService {

    private static final int OFFSET_DAYS = 365;
    private final ShortUrlEntityService shortUrlEntityService;
    private static final int SHORT_URL_LENGTH = 6;
    private static final String MDS = "MD5";
    private static final String HTTPS_SHORT_URL = "https://shortUrl/";

    @Override
    public String create(Request request) {
        if (!StringUtils.hasLength(request.getOriginalUrl()))
            throw new EmptyURLException();
        String shortenedUrl = getShortenedUrl(request);
        if (!shortUrlEntityService.save(request.getOriginalUrl(), shortenedUrl, LocalDateTime.now().plusDays(OFFSET_DAYS)))
            throw new ErrorWhileSavingToDB("Could not save in the database.");
        return  shortenedUrl;
    }

    @Override
    public String get(String url) {
        Optional<String> originalUrl = shortUrlEntityService.retrieveOriginalUrl(url);
        if (originalUrl.isPresent())
            return originalUrl.get();
        throw new URLNotFound("Could not find url");
    }

    private String getShortenedUrl(Request request) {
        if (!ObjectUtils.isEmpty(request.getCustomUrl()))
            return request.getCustomUrl();
        else
            return generateShortUrl(request.getOriginalUrl(), MDS, SHORT_URL_LENGTH);
    }
}
