package com.example.claims.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.claims.service.ClaimService;
import java.util.List;
import java.util.UUID;
import com.example.claims.dto.CreateClaimRequest;
import com.example.claims.dto.UpdateClaimStatusRequest;
import com.example.claims.model.Claim;
import com.example.claims.dto.UpdateClaimStatusRequest;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public Claim submitClaim(@RequestBody CreateClaimRequest request) {
        return claimService.createClaim(request);
    }

    @GetMapping("/customer/{customerId}")
    public List<Claim> getClaimsByCustomer(
        @PathVariable String customerId) {
        return claimService.getClaimsByCustomer(customerId);
    }

    @PatchMapping("/{claimId}")
    public Claim updateStatus(
        @PathVariable UUID claimId,
        @RequestBody UpdateClaimStatusRequest request) {

        return claimService.updateStatus(
            claimId,
            request.getStatus());
    }

    @GetMapping("/duplicates")
    public List<Claim> getDuplicateClaims() {
        return claimService.getDuplicateClaims();
    }
}
