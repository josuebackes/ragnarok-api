package com.organizadoraragnarok.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity(name = "tarefa")
@Table(schema = "public", name = "tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String url;

    @Column(name = "data_hora_publicacao", nullable = false)
    private OffsetDateTime dataHoraPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "gincana_id", nullable = false)
    private GincanaEntity gincana;
}
