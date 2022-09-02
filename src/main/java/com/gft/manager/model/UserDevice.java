package com.gft.manager.model;

import com.gft.manager.model.audit.DateAudit;
import com.gft.manager.model.token.RefreshToken;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class UserDevice extends DateAudit {

    @Id
    private String id;

   @DBRef
    private User user;


    private DeviceType deviceType;


    private String deviceId;


    @DBRef
    private RefreshToken refreshToken;


    private Boolean isRefreshActive;

    public UserDevice() {
    }

    public UserDevice(String id, User user, DeviceType deviceType, String deviceId,
                      RefreshToken refreshToken, Boolean isRefreshActive) {
        this.id = id;
        this.user = user;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.refreshToken = refreshToken;
        this.isRefreshActive = isRefreshActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Boolean getRefreshActive() {
        return isRefreshActive;
    }

    public void setRefreshActive(Boolean refreshActive) {
        isRefreshActive = refreshActive;
    }
}
