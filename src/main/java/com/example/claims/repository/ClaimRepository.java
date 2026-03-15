package com.example.claims.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.claims.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, UUID> {

    List<Claim> findByCustomerId(String customerId);

    List<Claim> findByDuplicateFlagTrue();

    List<Claim> findByCustomerIdAndPolicyId(String customerId, String policyId);

}
