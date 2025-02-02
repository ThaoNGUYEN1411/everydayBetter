package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "t_users")
public class UserEntity extends AbstractEntity {

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

//     fetch = FetchType.LAZY: default
    @ManyToMany
    @JoinTable(name = "t_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public UserEntity() {
        //default for ORM
    }

    public UserEntity( String nickname, String email, String password, Set<RoleEntity> roles) {
        //this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>(roles);
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "AccountEntity{"+
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='PROTECTED" + '\'' +
                ", roles=LAZY8LOADING" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof UserEntity other && email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }
}
// note: private SetId handle by db
// recover toString for not sprint password
// constructer empty for create object default for ORM
//@Column add nullable = false, updatable = false
//learn: why need to use new HashSet<>(roles);?
