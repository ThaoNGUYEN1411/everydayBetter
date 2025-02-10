package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "t_activities")
public class ActivityEntity extends AbstractEntity {

    @Column(name = "activity_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_positive", nullable = false, columnDefinition = "boolean default true")
    private Boolean positive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToMany
    @JoinTable(name = "t_activities_categories",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories;

    public ActivityEntity() {
        //ORM
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getPositive() {
        return positive;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPositive(Boolean positive) {
        this.positive = positive;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", positive=" + positive +
                ", categoryIds=" + categories +
                ", userEntity=" + userEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof ActivityEntity other && name.equals(other.name) && userEntity.equals(other.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userEntity);
    }
}