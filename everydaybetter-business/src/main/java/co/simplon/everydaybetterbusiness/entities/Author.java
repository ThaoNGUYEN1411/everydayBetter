package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_authors")
public class Author extends AbstractEntity {
    @Column(name = "author_name", nullable = false)
    private String name;
}
