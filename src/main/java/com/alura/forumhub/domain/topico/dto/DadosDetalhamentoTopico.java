package com.alura.forumhub.domain.topico.dto;

import com.alura.forumhub.domain.topico.Topico;

import java.util.List;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String curso,
        String mensagem,
        String autor,
        List<String> respostas) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getCurso(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getRespostas());
    }
}
