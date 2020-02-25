package com.pc1crt.application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private Boolean active;

    private String roles = "";

    private String permissions = "";

    public User(String username, String password, String roles, String permissions){
        this.userName = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = true;
    }

    protected User(){}

    public long getId() {
        return id;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + ", permissions=" + permissions + "]";
	}
}