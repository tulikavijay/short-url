package com.craft.shorturl.service;

import com.craft.shorturl.dto.Request;

public interface ShortUrlService {
    String create(Request request);
    String get(String url);
}
