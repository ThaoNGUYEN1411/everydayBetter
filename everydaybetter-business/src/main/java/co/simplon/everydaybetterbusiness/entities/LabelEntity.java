package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_labels")
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label_name")
    private String labelName;

    public LabelEntity() {
        //Default for ORM
    }

    public Long getId() {
        return id;
    }

    public String getLabelName() {
        return labelName;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        //handled by db
        this.id = id;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
//note: use @Column(name = "label_id") to map id with lable_id in db => correct db in DBeaver not use label_id, use id
// - private setId because handle by db, need to add suppressWarning