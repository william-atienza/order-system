package org.project.order.entity;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
public class Account {

    @Id
    private String id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdOn;
    @UpdateTimestamp
    @Column(insertable = false)
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
    public String getId() {
        return id;
    }

    public Account(){
        this.id = TSID.fast().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Instant lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }
}
