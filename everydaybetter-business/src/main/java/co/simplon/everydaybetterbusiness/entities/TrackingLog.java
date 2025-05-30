package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "t_tracking_logs")
public class TrackingLog extends AbstractEntity {

    @Column(name = "tracked_date")
    private LocalDate trackedDate;

    @Column(name = "done")
    private Boolean done;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false) //name column in this table
    private Activity activity;

    public TrackingLog(){
        //ORM
    }

    public LocalDate getTrackedDate() {
        return trackedDate;
    }

    public Boolean getDone() {
        return done;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setTrackedDate(LocalDate trackedDate) {
        this.trackedDate = trackedDate;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "TrackingLog{" +
                "trackedDate=" + trackedDate +
                ", done=" + done +
                ", activity=" + activity +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return object instanceof TrackingLog other && activity.equals(other.activity) && trackedDate.equals(other.trackedDate);
    }

    //todo: review
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trackedDate, done, activity);
    }
}
