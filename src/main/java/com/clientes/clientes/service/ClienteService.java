package com.clientes.clientes.service;

import com.clientes.clientes.modelo.Cliente;
import com.clientes.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> buscarClientes(String cidade) {
        if (cidade != null) {
            return clienteRepository.findByEndereco(cidade);
        } else {
            return clienteRepository.findAll();
        }
    }

    public Cliente buscaClienteCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public boolean excluirCliente(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente != null) {
            clienteRepository.delete(cliente);
            return true;
        }
        return false;
    }

    public boolean atualizarCliente(String cpf, Cliente cliente) {
        Cliente clienteDB = clienteRepository.findByCpf(cpf);
        if (cliente != null) {
            clienteDB.setNome(cliente.getNome());
            clienteDB.setEndereco(cliente.getEndereco());
            clienteRepository.save(clienteDB);
            return true;
        }
        return false;
    }
}
