package com.example.claims.service;

import com.example.claims.repository.ClaimRepository;
import com.example.claims.service.FraudDetectionService;

import org.springframework.stereotype.Service;

import com.example.claims.dto.CreateClaimRequest;
import com.example.claims.model.Claim;
import com.example.claims.model.ClaimStatus;

import java.util.List;
import java.util.UUID;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final FraudDetectionService fraudDetectionService;

    public ClaimService(ClaimRepository claimRepository,
                        FraudDetectionService fraudDetectionService) {
        this.claimRepository = claimRepository;
        this.fraudDetectionService = fraudDetectionService;
    }

    public Claim createClaim(CreateClaimRequest request) {

        List<Claim> existingClaims =
            claimRepository.findByCustomerIdAndPolicyId(
                request.getCustomerId(),
                request.getPolicyId()
            );

        boolean duplicate =
            fraudDetectionService.isDuplicate(existingClaims);

        Claim claim = new Claim();
        claim.setCustomerId(request.getCustomerId());
        claim.setPolicyId(request.getPolicyId());
        claim.setClaimType(request.getClaimType());
        claim.setDescription(request.getDescription());
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setDuplicateFlag(duplicate);

        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsByCustomer(String customerId) {
        return claimRepository.findByCustomerId(customerId);
    }

    public Claim updateStatus(UUID claimId, ClaimStatus status) {
        Claim claim = claimRepository.findById(claimId)
            .orElseThrow();

        claim.setStatus(status);

        return claimRepository.save(claim);
    }

    public List<Claim> getDuplicateClaims() {
        return claimRepository.findByDuplicateFlagTrue();
    }

}
