package com.gft.manager.model.token;

import com.gft.manager.model.audit.DateAudit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class RefreshToken extends DateAudit {

    @Id
    private String id;

    private String token;


   private String  userId;

    private Long refreshCount;


    private Instant expiryDate;

    public RefreshToken() {
    }

    public RefreshToken(String id, String token, String userId, Long refreshCount, Instant expiryDate) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.refreshCount = refreshCount;
        this.expiryDate = expiryDate;
    }

    public void incrementRefreshCount() {
        refreshCount = refreshCount + 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(Long refreshCount) {
        this.refreshCount = refreshCount;
    }
}
