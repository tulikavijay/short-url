package com.craft.shorturl.controller;

import com.craft.shorturl.dto.Request;
import com.craft.shorturl.dto.Response;
import com.craft.shorturl.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
public class ShortUrlController {

    private final ShortUrlService shortUrlServiceImp;

    @PostMapping(value = "/api/v1/short_url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createShortUrl(@RequestBody @Validated Request request) {
        return new Response(shortUrlServiceImp.create(request));
    }

    @GetMapping(value = "/api/v1/short_url", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getShortUrl(@RequestParam(name = "url") String url, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FOUND);
        return new Response(shortUrlServiceImp.get(url));
    }
}
