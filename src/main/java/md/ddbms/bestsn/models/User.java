package md.ddbms.bestsn.models;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private int id;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    // friendList - List of people that this user consider as his friends
    @JoinTable(name = "Friends", joinColumns = {
            @JoinColumn(name = "Requested_UserId", referencedColumnName = "UserId")}, inverseJoinColumns = {
            @JoinColumn(name = "Addressed_UserId", referencedColumnName = "UserId")})
    @ManyToMany
    private List<User> friendList = new ArrayList<>();

    //requestedFriendList - List of people that requested friendship with this user
    @ManyToMany(mappedBy = "friendList")
    private List<User> requestedFriendList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
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
