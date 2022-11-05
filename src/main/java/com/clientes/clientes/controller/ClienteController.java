package com.clientes.clientes.controller;

import com.clientes.clientes.modelo.Cliente;
import com.clientes.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes(
            @RequestParam(name = "cidade", required = false) String cidade) {
        return clienteService.buscarClientes(cidade);
    }

    @GetMapping("/{cpf}")
    public Cliente getCliente(@PathVariable String cpf) {
        return clienteService.buscaClienteCpf(cpf);
    }


    @PostMapping
    public String salvarClientes(@RequestBody Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().equals("")) {
            return "O nome do usuário é obrigatório";
        }

        if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
            return "O CPF é obrigatório";
        }

        Cliente clienteDB = clienteService.buscaClienteCpf(cliente.getCpf());
        if (clienteDB != null) {
            return "CPF já cadastrado.";
        }

        clienteService.salvarCliente(cliente);
        return "Cadastro efetuado com sucesso!";
    }

    @DeleteMapping("/{cpf}")
    public String deletarClientes(@PathVariable String cpf) {
        boolean deletou = clienteService.excluirCliente(cpf);
        if (deletou) {
            return "Cliente excluído com sucesso!";
        }
        return "Cliente não encontrado.";
    }

    @PutMapping("/{cpf}")
    public String atualizarCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
        boolean atualizou = clienteService.atualizarCliente(cpf, cliente);
        if (atualizou) {
            return "Cliente atualizado com sucesso";
        }
        return "Cliente não encontrado";
    }
}
