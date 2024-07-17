package com.alura.forumhub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

   boolean existsByMensagem(String mensagem);
}
