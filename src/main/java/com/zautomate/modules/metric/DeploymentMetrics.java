package com.zautomate.modules.metric;

import com.zautomate.modules.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private Long deploymentId;

    private Long expiry;
    private Double quantity;
    private Double catalogBasePrice;

    @OneToOne
    private Organization customer;
    @OneToOne
    private Organization deployer;
    @OneToOne
    private Organization distributor;
    @OneToOne
    private Organization manufacturer;

    private String customerName;
    private String deployerName;
    private String distributorName;
    private String manufacturerName;

    private String parentCustomerName;
    private String parentDeployerName;
    private String parentDistributorName;
    private String parentManufacturerName;

    private Long quoteManufacturer;
    private Long quoteDistributor;
    private Long quoteVar;

    private Long pendingPODistributor;
    private Long pendingPOVar;
    private Long pendingPOEndCustomer;

    private Integer quotedDaysInAdvance;
    private Integer quotedDaysInAdvanceVar;
    private Integer quotedDaysInAdvanceDistributor;
    private Integer quotedDaysInAdvanceManufacturer;

    private String renewalStatus;
    private String status;
}
