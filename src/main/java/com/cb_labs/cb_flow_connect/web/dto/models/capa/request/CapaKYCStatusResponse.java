package com.cb_labs.cb_flow_connect.web.dto.models.capa.request;

import java.util.List;

public record CapaKYCStatusResponse(
    String status,
    KYCStatusResponse data
) {
    public record KYCStatusResponse(
        AipriseSummary aipriseSummary,
        String clientReferenceId,
        ClientReferenceData clientReferenceData,
        FaceLivenessInfo faceLivenessInfo,
        FaceMatchInfo faceMatchInfo,
        FraudInsights fraudInsights,
        IdInfo idInfo,
        AmlInfo amlInfo,
        String verificationSessionId,
        Media media
    ) {}

    public record AipriseSummary(
        String verificationResult
    ) {}

    public record FaceLivenessInfo(
        String result,
        String status
    ) {}

    public record FaceMatchInfo(
        double faceMatchScore,
        String result,
        String status
    ) {}

    public record FraudInsights(
        String result,
        String status
    ) {}

    public record IdInfo(
        String fullName,
        String birthDate,
        String gender,
        String nationality,
        String idNumber,
        String idType
    ) {}

    public record AmlInfo(
        int numHits,
        List entityHits
    ) {}

    public record Media(
        String frontIdImageUrl,
        String backIdImageUrl,
        String selfieImageUrl,
        String selfieVideoUrl
    ) {}

    public record ClientReferenceData(
        PersonalInfo personalInfo
    ) {
        public record PersonalInfo(
            String profession,
            String phoneNumber,
            String birthCountry,
            String taxId
        ) {}
    }
}
