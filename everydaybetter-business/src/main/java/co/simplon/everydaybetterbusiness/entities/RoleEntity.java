package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_default")
    private Boolean roleDefault;

    public RoleEntity() {
    }

    public RoleEntity(Boolean roleDefault, String roleName) {
        this.roleDefault = roleDefault;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getRoleDefault() {
        return roleDefault;
    }

    public void setIsDefault(Boolean roleDefault) {
        this.roleDefault = roleDefault;
    }
}
