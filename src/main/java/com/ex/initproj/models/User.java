package com.ex.initproj.models;

import com.ex.initproj.validations.ValidPasswordCharacter;
import com.ex.initproj.validations.ValidPasswordLength;
import com.ex.initproj.validations.ValidUsernameCharacter;
import com.ex.initproj.validations.ValidUsernameLength;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
//    @NotBlank(message = "USER - HUMAN ID CANT BE NULL")
    @Column(name = "humanid")
    private int humanid;
    @Column(name = "employee_code")
    private String employeeCode;
    @NotBlank(message = "USER - USERNAME CANT BE NULL")
    @Column(name = "username")
//    @ValidUsernameCharacter
//    @ValidUsernameLength
    private String username;
    @NotBlank(message = "USER - PASSWORD CANT BE NULL")
    @Column(name = "password")
//    @ValidPasswordCharacter
//    @ValidPasswordLength
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
//    @NotBlank(message = "Role cant be null")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
