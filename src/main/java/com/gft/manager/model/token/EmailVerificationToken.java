package com.gft.manager.model.token;

import com.gft.manager.model.User;
import com.gft.manager.model.audit.DateAudit;
import com.gft.manager.model.TokenStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document()
public class EmailVerificationToken extends DateAudit {

    @Id
    private Long id;

    private String token;
    @DBRef
    private User user;

    private TokenStatus tokenStatus;

    private Instant expiryDate;

    public EmailVerificationToken() {
    }

    public EmailVerificationToken(Long id, String token, User user, TokenStatus tokenStatus, Instant expiryDate) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.tokenStatus = tokenStatus;
        this.expiryDate = expiryDate;
    }

    public void setConfirmedStatus() {
        setTokenStatus(TokenStatus.STATUS_CONFIRMED);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public TokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(TokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }
}
