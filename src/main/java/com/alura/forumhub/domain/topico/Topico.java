package com.alura.forumhub.domain.topico;

import com.alura.forumhub.domain.topico.dto.DadosCadastroTopico;
import com.alura.forumhub.domain.topico.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    @ElementCollection
    private List<String> respostas;

    public Topico(DadosCadastroTopico dados) {
        titulo = dados.titulo();
        mensagem = dados.mensagem();
        dataCriacao = LocalDateTime.now();
        status = Status.NAO_RESPONDIDO;
        autor = dados.autor();
        curso = dados.curso();
    }
}
