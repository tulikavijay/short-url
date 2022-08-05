package com.craft.shorturl.service;

import com.craft.shorturl.dto.Request;
import com.craft.shorturl.exception.EmptyURLException;
import com.craft.shorturl.exception.URLNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceWithHashTest {

    @InjectMocks
    ShortUrlServiceWithHash service;

    @Mock
    ShortUrlEntityService entityService;

    @Test
    void shouldCreateWhenNonEmptyRequestIsPassed(){
        when(entityService.save(anyString(),anyString(),any())).thenReturn(true);
        String url = service.create(new Request("url", ""));
        assertFalse(url.isEmpty());
    }

    @Test
    void shouldReturnExceptionForEmptyString(){
        assertThrows(EmptyURLException.class, () -> service.create(new Request("","")));
    }

    @Test
    void shouldCreateCustomUrl(){
        when(entityService.save(anyString(),anyString(),any())).thenReturn(true);
        String url = service.create(new Request("https://www.url.com/", "abcde"));
        assertEquals("https://shortUrl/abcde", url);
    }

    @Test
    void shouldGetUrlWhenItsPresentAndNotExpired(){
        when(entityService.retrieveOriginalUrl("https://shortUrl/abcde")).thenReturn(Optional.of("https://www.url.com/"));
        assertEquals("https://www.url.com/", service.get("https://shortUrl/abcde"));
    }

    @Test
    void shouldThrowExceptionWhenNotFound(){
        when(entityService.retrieveOriginalUrl("https://shortUrl/abcde")).thenReturn(Optional.empty());
        assertThrows(URLNotFound.class, ()-> service.get("https://shortUrl/abcde"));
    }

}