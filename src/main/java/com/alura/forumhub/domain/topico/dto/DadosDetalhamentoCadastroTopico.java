package com.alura.forumhub.domain.topico.dto;

import com.alura.forumhub.domain.topico.Topico;

public record DadosDetalhamentoCadastroTopico(
        Long id,
        String titulo,
        String curso,
        String mensagem,
        String autor) {

    public DadosDetalhamentoCadastroTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getCurso(),
                topico.getMensagem(),
                topico.getAutor());
    }
}
