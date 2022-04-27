package module;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Table(name ="seller")
@Entity
public class seller implements UserDetails {
    @Id
    private Long sellerID;

    private String fName;

    private  String lName;

    private  String dob;

    private  int phoneNumber;

    private  String email;

    private String passward;

    private String address;

    private String photoPath ;
    @OneToMany(mappedBy = "seller")
    @JoinColumn(name = "itemID")
    private List<item> items;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
