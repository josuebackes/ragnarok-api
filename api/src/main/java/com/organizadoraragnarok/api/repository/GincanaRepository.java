package com.organizadoraragnarok.api.repository;

import com.organizadoraragnarok.api.domain.GincanaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GincanaRepository extends JpaRepository<GincanaEntity, UUID> {

}
