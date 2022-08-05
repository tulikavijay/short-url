package com.craft.shorturl.service;

import com.craft.shorturl.repository.ShortUrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShortUrlEntityServiceTest {

    @InjectMocks
    ShortUrlEntityService shortUrlEntityService;

    @Mock
    ShortUrlRepository repository;

    @Test
    void shouldSaveDetails() {
        when(repository.save(any())).thenReturn(any());
        assertTrue(shortUrlEntityService.save("https://url/", "https://shortUrl/abcde", LocalDateTime.now()));
    }

    @Test
    void shouldNotSaveInDBForIntegrityViolatiob() {
        when(repository.save(any())).thenThrow(DataIntegrityViolationException.class);
        assertFalse(shortUrlEntityService.save("https://url/", "https://shortUrl/abcde", LocalDateTime.now()));
    }

    @Test
    void shouldNotSaveInDBForIllegalState() {
        when(repository.save(any())).thenThrow(IllegalStateException.class);
        assertFalse(shortUrlEntityService.save("https://url/", "https://shortUrl/abcde", LocalDateTime.now()));
    }
}