package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_users")
public class User extends AbstractEntity {

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    //     fetch = FetchType.LAZY: default
    @ManyToMany
    @JoinTable(name = "t_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        //default for ORM
    }

    public User(String nickname, String email, String password, Set<Role> roles) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>(roles);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof User other && email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), email);
//    }

    @Override
    public String toString() {
        return "User{" +
               "nickname='" + nickname + '\'' +
               ", email='" + email + '\'' +
               ", password='" + "PROTECTED" + '\'' +
               ", roles=" + roles +
               '}';
    }
}
// recover toString for not sprint password
// constructor empty for create object default for ORM
//@Column add nullable = false, updatable = false
//learn: why need to use new HashSet<>(roles);?
