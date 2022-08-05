package com.craft.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @NonNull
    private String originalUrl;

    private String customUrl;
}
