package com.example.claims.dto;

import com.example.claims.model.ClaimStatus;

public class UpdateClaimStatusRequest {

    private ClaimStatus status;

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

}

