package com.craft.shorturl.service;

import com.craft.shorturl.exception.IncorrectAlgorithmException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EncodingAndHashingService {


    // original url - > hashed MD5
    // byte[] -> encoded
    // encoded -> 20-30 characters
    // Say 6 random characters
    public static String generateShortUrl(String url, String algorithm, int length)  {
        byte[] input = generateHash(url, algorithm);
        Base64.Encoder encoder = Base64.getEncoder();
        return randomizeCharacters(encoder.encodeToString(input), length);
    }

    private static byte[] generateHash(String url, String algorithm){
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IncorrectAlgorithmException("Incorrect algorithm added for hashing technique");
        }

        byte[] input = url.getBytes(StandardCharsets.UTF_8);
        return messageDigest.digest(input);
    }

    private static String randomizeCharacters(String encodedUrl, int x) {
        Random random = ThreadLocalRandom.current();
        char[] charArray = encodedUrl.toCharArray();
        for (int i = 20; i >= 0; i--) {
            int nextIndex = random.nextInt(i + 1);
            swap(charArray, nextIndex, i);
        }

        return new String(charArray, 0, x);
    }

    private static void swap(char[] encodedChars, int randomIndex, int i) {
        char temp = encodedChars[i];
        encodedChars[i] = encodedChars[randomIndex];
        encodedChars[randomIndex] = temp;
    }
}
