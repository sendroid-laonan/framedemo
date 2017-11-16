package com.sendroids.framedemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import groovy.util.logging.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *Create By Edison Lao on 2017/11/10
 * users bean
 * */
@Entity
@Table(name="users")
@Slf4j
public class Users extends BaseEntity implements UserDetails{

    @Id
    @GeneratedValue
    private  Long id;

    @Column(name = "name",nullable = false)
    @NotEmpty
    @Length(min = 2,message = "*姓名长度最少为2")
    private String name;

    @Column(name = "username",nullable = false)
    @NotEmpty
    @Length(min = 6,max=14, message = "*用户名长度6-14")
    private String username;

    @Column(name = "password",nullable = false)
    @NotEmpty
    @Length(min = 6,max=14, message = "*密码长度6-14")
    private String password;

    @Column(name = "phone",nullable = false)
    @NotEmpty
    @Length(min = 11,max=11, message = "*11位手机号码")
    private String phone;

    @Column(name = "state",nullable = false)
    private int state;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id") }, inverseJoinColumns = {
        @JoinColumn(name = "role_id") })
    private List<Role> roles;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "town_id")
    private Town town;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
