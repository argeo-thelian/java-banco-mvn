package com.arthe.banco.mvn.repository;

import com.arthe.banco.mvn.repository.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}

