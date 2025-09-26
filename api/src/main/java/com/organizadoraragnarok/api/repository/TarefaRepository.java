package com.organizadoraragnarok.api.repository;

import com.organizadoraragnarok.api.domain.Setor;
import com.organizadoraragnarok.api.domain.TarefaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<TarefaEntity, UUID> {
    Page<TarefaEntity> findByGincanaIdAndDataHoraPublicacaoLessThanEqual(
            UUID gincanaId, OffsetDateTime dataHoraPublicacao, Pageable pageable
    );

    Page<TarefaEntity> findByGincanaIdAndSetorAndDataHoraPublicacaoLessThanEqual(
            UUID gincanaId, Setor setor, OffsetDateTime dataHoraPublicacao, Pageable pageable
    );

    Page<TarefaEntity> findByGincanaId(UUID gincanaId, Pageable pageable);
    Page<TarefaEntity> findByGincanaIdAndSetor(UUID gincanaId, Setor setor, Pageable pageable);
}
