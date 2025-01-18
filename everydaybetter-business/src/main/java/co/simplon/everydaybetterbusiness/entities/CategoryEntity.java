package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_categories")
public class CategoryEntity extends AbstractEntity {

    @Column(name = "category_name")
    private String name;

//    @ManyToMany
//    @JoinTable(name = "t_activities_categories",
//            joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "activity_id")
//        )
//    private ActivityEntity activities;

    public CategoryEntity() {
        //Default for ORM
    }

    public String getName() {
        return name;
    }

//    public ActivityEntity getActivities() {
//        return activities;
//    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setActivities(ActivityEntity activities) {
//        this.activities = activities;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof CategoryEntity other && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
//note: use @Column(name = "label_id") to map id with lable_id in db => correct db in DBeaver not use label_id, use id
// - private setId because handle by db, need to add suppressWarning
//if necessary to @manytomany