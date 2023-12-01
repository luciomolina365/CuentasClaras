package com.ttps2023.CuentasClaras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}