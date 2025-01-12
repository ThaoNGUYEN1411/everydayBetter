package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "t_accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // fetch = FetchType.LAZY: default
//    @ManyToMany
//    @JoinTable(name = "t_account_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<RoleEntity> roles;

    public AccountEntity() {
        //default for ORM
    }

//    public AccountEntity(String username, String email, String password, Set<RoleEntity> roles) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.roles = new HashSet<>(roles);
//    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<RoleEntity> getRoles() {
//        return Collections.unmodifiableSet(roles);
//    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='PROTECTED" + '\'' +
                ", roles=LAZY8LOADING" +
                '}';
    }
}
// note: private SetId handle by db
// recover toString for not sprint password
// constructer empty for create object default for ORM
//@Column add nullable = false, updatable = false
//learn: why need to use new HashSet<>(roles);?
