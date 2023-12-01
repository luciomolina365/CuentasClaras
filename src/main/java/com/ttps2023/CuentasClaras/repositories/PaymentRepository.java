package com.ttps2023.CuentasClaras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
