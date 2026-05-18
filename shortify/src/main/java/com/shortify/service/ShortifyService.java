package com.shortify.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortify.dao.UrlDao;
import com.shortify.model.Url;

import jakarta.transaction.Transactional;

@Service
public class ShortifyService {
    @Autowired
    private UrlDao urlDao;

    private String generateRandomCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        char[] code = new char[7];
        for (int i = 0; i < code.length; i++) {
            code[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(code);
    }
    
    @Transactional
    public void generateShortUrlCode(Url url){
        url.setShortCode(generateRandomCode());
        url.setCreatedAt(LocalDateTime.now());
        url.setExpiryDateTime(LocalDateTime.now().plusMonths(1));
        
        urlDao.saveUrl(url);
    }

    @Transactional
    public Url getUrlByShortCode(String shortCode) {
        Url url = urlDao.fetchUrlByShortCode(shortCode);
        if(url != null) {
            urlDao.incrementClickCount(url.getUrlId());
        }
        return url;
    }
}
