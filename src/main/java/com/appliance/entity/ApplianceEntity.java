package com.appliance.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class ApplianceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String state;

    }


