package com.ruslanshakirov.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruslanshakirov.crm.entity.profile.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractNamedEntity {
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean enabled = true;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Profile currentProfile;
    @OneToMany(mappedBy = "user")
    private List<Profile> profiles;
    @ElementCollection
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public User(String name, String email, String password, boolean enabled, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addProfile(Profile profile) {
        profile.setUser(this);
        profiles.add(profile);
    }

    public void removeProfile(Profile profile) {
        profile.setUser(null);
        profiles.remove(profile);
    }
}
