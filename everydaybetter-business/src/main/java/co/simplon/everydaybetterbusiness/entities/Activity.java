package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;


@Entity
@Table(name = "t_activities")
public class Activity extends AbstractEntity {

    @Column(name = "activity_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_positive", nullable = false, columnDefinition = "boolean default true")
    private Boolean positive;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
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

    public Category getCategory() {
        return category;
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


    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", positive=" + positive +
                ", category=" + category +
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
