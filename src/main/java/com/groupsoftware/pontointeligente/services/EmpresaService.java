package com.groupsoftware.pontointeligente.services;

import com.groupsoftware.pontointeligente.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {

    /**
     * Busca uma empresa através do CNPJ
     *
     * @param cnpj
     * @return
     */
    Optional<Empresa> buscarPorCnpj(String cnpj);

    /**
     * Salva uma empresa no banco de dados
     * @param empresa
     * @return
     */
    Empresa persistir(Empresa empresa);
}
