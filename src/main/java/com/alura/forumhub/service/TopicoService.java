package com.alura.forumhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alura.forumhub.domain.topico.Topico;
import com.alura.forumhub.domain.topico.TopicoRepository;
import com.alura.forumhub.domain.topico.dto.DadosAtualizacaoTopico;
import com.alura.forumhub.domain.topico.dto.DadosCadastroTopico;
import com.alura.forumhub.domain.topico.dto.DadosDetalhamentoCadastroTopico;
import com.alura.forumhub.domain.topico.dto.DadosDetalhamentoTopico;
import com.alura.forumhub.domain.topico.dto.DadosListagemTopico;
import com.alura.forumhub.service.exceptions.DuplicidadeTopicoException;
import com.alura.forumhub.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    public DadosDetalhamentoCadastroTopico cadastrar(DadosCadastroTopico dados) {
        try {
            validarDuplicidade(dados.mensagem());
            Topico topico = repository.save(new Topico(dados));
            return new DadosDetalhamentoCadastroTopico(topico);
        } catch (EntityNotFoundException e) {
            throw new DuplicidadeTopicoException("Já existe um tópico com o mesmo título ou mensagem. ");
        }
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemTopico> listar(Pageable pageable) {
        return repository.findAll(pageable).map(DadosListagemTopico::new);
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoTopico detalhar(Long id) {
        try {
            Topico topico = repository.getReferenceById(id);
            return new DadosDetalhamentoTopico(topico);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID do tópico inválido. Forneça um ID válido. " + id);
        }
    }

    @Transactional
    public DadosDetalhamentoTopico atualizar(Long id, DadosAtualizacaoTopico dados) {
        try {
            Topico topico = repository.getReferenceById(id);
            topico.setTitulo(dados.titulo());
            topico.setMensagem(dados.mensagem());
            topico = repository.save(topico);
            return new DadosDetalhamentoTopico(topico);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID do tópico inválido. Forneça um ID válido. " + id);
        }
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID do tópico inválido. Forneça um ID válido. " + id);
        }
        repository.deleteById(id);
    }

    public void validarDuplicidade(String mensagem) {
        if (repository.existsByMensagem(mensagem)) {
            throw new DuplicidadeTopicoException("Já existe um tópico com o mesmo título e mensagem. ");
        }
    }
}
