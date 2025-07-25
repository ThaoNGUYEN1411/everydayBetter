package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_categories")
public class Category extends AbstractEntity {

    @Column(name = "category_name")
    private String name;

    public Category() {
        //Default for ORM
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Category other && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
