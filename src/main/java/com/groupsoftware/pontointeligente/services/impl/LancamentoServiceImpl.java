package com.groupsoftware.pontointeligente.services.impl;

import com.groupsoftware.pontointeligente.entities.Funcionario;
import com.groupsoftware.pontointeligente.repositories.FuncionarioRepository;
import com.groupsoftware.pontointeligente.services.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario persistir(Funcionario funcionario) {
        log.info("Persistindo funcionário: {}", funcionario);
        return this.funcionarioRepository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> buscarPorCpf(String cpf) {
        log.info("Buscando funcionário pelo CPF {}", cpf);
        return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Funcionario> buscarPorEmail(String email) {
        log.info("Buscando funcionário pelo email {}", email);
        return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        log.info("Buscando funcionário pelo IDl {}", id);
        return Optional.ofNullable(this.funcionarioRepository.findOne(id));
    }
}
