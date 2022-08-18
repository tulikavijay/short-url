package com.craft.shorturl.controller;

import com.craft.shorturl.dto.Request;
import com.craft.shorturl.dto.Response;
import com.craft.shorturl.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
public class ShortUrlController {

    private final ShortUrlService shortUrlServiceImp;

    @PostMapping(value = "/api/v1/short_url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createShortUrl(@RequestBody @Validated Request request) {
        return new Response(shortUrlServiceImp.create(request));
    }

    @GetMapping(value = "/{url}")
    public void getShortUrl(@PathVariable(name = "url", required = false) String url, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", shortUrlServiceImp.get(url));
    }
}