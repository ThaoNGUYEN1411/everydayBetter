package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_roles")
public class RoleEntity extends AbstractEntity {

    @Column(name = "role_name")
    private String name;

    @Column(name = "is_default")
    private Boolean roleDefault;

    public RoleEntity() {
        //ORM
    }

    public String getName() {
        return name;
    }

    public Boolean getRoleDefault() {
        return roleDefault;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoleDefault(Boolean roleDefault) {
        this.roleDefault = roleDefault;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                ", roleDefault=" + roleDefault +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof RoleEntity other && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
