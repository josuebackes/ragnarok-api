package com.organizadoraragnarok.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "gincana")
@Table(schema = "public", name = "gincana")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class GincanaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "regulamento_oficial")
    private String regulamentoOficial;

    @OneToMany(mappedBy = "gincana", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarefaEntity> tarefas;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(name = "data_mes_ano")
    private String dataMesAno;
}
