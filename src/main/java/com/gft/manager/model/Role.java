package com.gft.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The type Role. Defines the role and the list of users who are associated with that role
 */
@Document()
public class Role {

    @Id
    private String id;

    private RoleName role;

    @DBRef
    @JsonIgnore
    private Set<User> userList = new HashSet<>();
    private Map<HttpMethod,String> permittedUrl = new HashMap<>();
    public Role(RoleName role) {
        this.role = role;
    }

    public Role() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isAdminRole() {
        return null != this && this.role.equals(RoleName.ROLE_ADMIN);
    }

    public boolean isInfluencerRole() {
        return null != this && this.role.equals(RoleName.ROLE_INFLUENCER);
    }

    public boolean isReviewerRole() {
        return null != this && this.role.equals(RoleName.ROLE_REVIEWER);
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public Set<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return String.valueOf(role);
    }
}
