/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.clientes.Cliente;
import br.edu.ifsul.clientes.ServicoClienteService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Renato
 */
@Stateless
public class ClienteDAO implements Serializable {

    private ServicoClienteService cliente;

    public ClienteDAO() {
        cliente = new ServicoClienteService();
    }

    public List<Cliente> getListaCliente() {
        return getCliente().getServicoClientePort().listaClientes();
    }

    public void inserir(Cliente obj) {
        cliente.getServicoClientePort().inserir(obj);
    }

    public void alterar(Cliente obj) {
        cliente.getServicoClientePort().alterar(obj);
    }

    public void remover(Integer id) {
        cliente.getServicoClientePort().remover(id);
    }

    public ServicoClienteService getCliente() {
        return cliente;
    }

    public void setCliente(ServicoClienteService cliente) {
        this.cliente = cliente;
    }
}
