package com.gft.manager.model;

import com.gft.manager.model.audit.DateAudit;
import com.gft.manager.validation.annotation.NullOrNotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Document
public class User extends DateAudit {

    @Id
    private String id;

    @NotBlank(message = "User email cannot be null")
    private String email;


    @NullOrNotBlank(message = "Username can not be blank")
    private String username;


    @NotNull(message = "Password cannot be null")
    private String password;


    @NullOrNotBlank(message = "Full name can not be blank")
    private String fullName;

    private Boolean active;


    @DBRef
    private Set<Role> roles ;


    private Boolean isEmailVerified;
    private int  totalUpload;
    private  int totalApproved;

    private  int totalAssigned;
    private int totalReviewed;

    public User() {
        super();
    }

    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        fullName = user.getFullName();
        email = user.getEmail();
        active = user.getActive();
        roles = user.getRoles();
        isEmailVerified = user.getEmailVerified();
    }

    public  void increaseUpload(){
        this.totalUpload = this.getTotalUpload()+1;
    }
    public  void increaseApproved(){
        this.totalApproved = this.getTotalApproved()+1;
    }
    public  void increaseAssigned(){
        this.totalAssigned = this.getTotalAssigned()+1;
    }
    public  void increaseReviewed(){
        this.totalReviewed = this.getTotalReviewed()+1;
    }
    public int getTotalUpload() {
        return totalUpload;
    }

    public int getTotalApproved() {
        return totalApproved;
    }

    public int getTotalAssigned() {
        return totalAssigned;
    }

    public int getTotalReviewed() {
        return totalReviewed;
    }

    public void addRole(Role role) {
        if (roles== null){
            this.roles = new HashSet<>();
        }
        roles.add(role);
        role.getUserList().add(this);
    }
    public  boolean isAdmin(){
        var optional = this.roles.stream().filter(Role::isAdminRole).findFirst();
        return optional.isPresent();
    } public  boolean isReviewer(){
        var optional = this.roles.stream().filter(role -> role.isReviewerRole()).findFirst();
        return optional.isPresent();
    }
    public  boolean isInfluencer(){
        var optional = this.roles.stream().filter(Role::isInfluencerRole).findFirst();
        return optional.isPresent();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    public void removeRole(Role role) {
        roles.remove(role);

    }

    public void markVerificationConfirmed() {
        setEmailVerified(true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> authorities) {
        if (roles== null) this.roles = new HashSet<>();
        roles = authorities;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", username='" + username + '\'' + ", password='"
                + password + '\'' + ", fullName='" + fullName + '\'' +  '\'' + ", active="
                + active + ", roles=" + roles + ", isEmailVerified=" + isEmailVerified + '}';
    }
}
