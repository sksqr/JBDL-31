package io.bootify.l16_visitor_app.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    private String password;

    transient private List<SimpleGrantedAuthority> authorities;

    @PostLoad
    public void initAuthorities(){
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRole()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
