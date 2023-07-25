package com.fours.tolevelup.model;

import com.fours.tolevelup.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserVO implements UserDetails {
    private String id;
    private String password;
    private String name;
    private String email;
    private int level;
    private String intro;
    private UserRole role;
    private Date registeredAt;

    public static UserVO fromEntity(User entity){
        return new UserVO(
                entity.getId(),
                entity.getPassword(),
                entity.getName(),
                entity.getEmail(),
                entity.getLevel(),
                entity.getIntro(),
                entity.getRole(),
                entity.getRegisteredAt()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
