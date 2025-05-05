package org.project.repository;

import org.project.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
    @Query(value = "SELECT * FROM Shipping WHERE orderId = :orderId ORDER BY OALlastUpdatedOn DESC LIMIT 1", nativeQuery = true)
    Optional<Shipping> findLatestShippingByOrderId(@Param("orderId") UUID orderId);
}
