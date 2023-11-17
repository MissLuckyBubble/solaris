package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends MainModel {
    @Column(nullable = false, length = 256)
    private String name;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false, length = 256, unique = true)
    private String email;
    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;
    @Column(name = "LAST_LOGGED_AT")
    private LocalDateTime lastLoggedAt;
    @Column(length = 30)
    private String phone;
    @Column(length = 256)
    private String address;
    @OneToMany(mappedBy = "owner")
    private Set<Article> articles;
}
