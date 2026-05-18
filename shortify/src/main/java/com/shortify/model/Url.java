package com.shortify.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@DynamicInsert
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urlId;

    @Column(nullable = false)
    @NotBlank(message = "URL cannot be empty")
    @Pattern(regexp = "^(http|https)://.*$", message = "Enter valid URL")
    private String originalUrl;
    
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiryDateTime;

/* It is a better approach to use Wrapper class instead od Primitive types coz
   using hibernate we can check for nullable values from the database tables. */
    @ColumnDefault("0")
    private Integer clickCount; 

    public Long getUrlId() {
        return urlId;
    }
    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public String getShortCode() {
        return shortCode;
    }
    public void setShortCode(String shortUrl) {
        this.shortCode = shortUrl;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }
    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
    public int getClickCount() {
        return clickCount;
    }
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}
