package com.alura.forumhub.domain.topico.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        String titulo,
        @NotNull
        String mensagem) {
}
