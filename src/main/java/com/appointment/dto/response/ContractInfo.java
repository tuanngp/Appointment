package com.appointment.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractInfo {

    private String contractNumber;

    private BigDecimal totalFee;

    private String status;

    private String description;
}
