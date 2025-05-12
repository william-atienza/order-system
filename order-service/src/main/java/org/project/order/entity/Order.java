package org.project.order.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @Tsid()
    private String id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdOn;
    @UpdateTimestamp
    @Column(insertable = false)
    private Instant lastUpdatedOn;
    @Column(columnDefinition = "json")
    private String shipping;

    @ManyToOne
    private Account account;

    public Order(){}
    public Order(String shipping, Account account) {
        //this.id = TSID.fast().toString();
        this.shipping = shipping;
        this.account = account;
    }

    public String getId() {
        return id;
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

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
