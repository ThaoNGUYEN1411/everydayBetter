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
public class Activity extends AbstractEntity {

    @Column(name = "activity_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_positive", nullable = false, columnDefinition = "boolean default true")
    private Boolean positive;

    @ManyToMany
    @JoinTable(name = "t_activities_categories",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Activity() {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public User getUser() {
        return user;
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

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", positive=" + positive +
                ", categoryIds=" + categories +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Activity other && name.equals(other.name) && user.equals(other.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, user);
    }
}