package com.clientes.clientes.repository;

import com.clientes.clientes.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByEndereco(String endereco);

    List<Cliente> findByNome(String nome);

    Cliente findByCpf(String cpf);

}
