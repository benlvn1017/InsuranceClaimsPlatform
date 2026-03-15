package com.example.claims.service;

import org.springframework.stereotype.Service;
import com.example.claims.model.Claim;
import java.util.List;

@Service
public class FraudDetectionService {

    public boolean isDuplicate(List<Claim> existingClaims) {
        return !existingClaims.isEmpty();
    }

}
