/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.clientes.Cliente;
import br.edu.ifsul.clientes.ServicoClienteService;

/**
 *
 * @author Renato
 */
public class TesteServicoCorreio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicoClienteService cliente = new ServicoClienteService();
        Cliente c = new Cliente();
        c.setId(0);
        c.setNome("lol");
        c.setEndereco("8gh");
        c.setCepDestino("uybiu");
        c.setCepOrigem("nigo8y");
        c.setValorFrete(22.50);
        c.setPrazo("5 dias");
        cliente.getServicoClientePort().inserir(c);
    }

}
