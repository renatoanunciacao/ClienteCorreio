/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.cliente.CResultado;
import br.edu.ifsul.cliente.CServico;
import br.edu.ifsul.cliente.CalcPrecoPrazo;
import br.edu.ifsul.cliente.CalcPrecoPrazoWS;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.clientes.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Renato
 */
@Named(value = "controleCliente")
@SessionScoped
public class ControleCliente implements Serializable {

    private Cliente objeto;

    @EJB
    private ClienteDAO dao;
    String valorFrete = "";
    String prazo = "";
    String cepDestino = "";
    String cepOrigem = "";
    private CalcPrecoPrazoWS servicoCorreio;
    private CalcPrecoPrazo salvando;

    public ControleCliente() {
        servicoCorreio = new CalcPrecoPrazoWS();
        salvando = new CalcPrecoPrazo();
    }

    public String novo() {
        objeto = new Cliente();
        setSalvando(new CalcPrecoPrazo());
        objeto.setId(0);
        return "form?faces-redirect=true";
    }

    public String alterar(Cliente obj) {
        objeto = obj;
        return "form?faces-redirect=true";
    }

    public String salvar() {
        if (objeto.getId() == 0) {
            dao.inserir(objeto);
        } else {
            dao.alterar(objeto);
        }
        return "index?faces-redirect=true";
    }

    public void enviarDados() {
        CResultado resultado = getServicoCorreio().getCalcPrecoPrazoWSSoap().calcPrecoPrazo(
                salvando.getNCdEmpresa(), salvando.getSDsSenha(), salvando.getNCdServico(),
                salvando.getSCepOrigem(), salvando.getSCepDestino(), salvando.getNVlPeso(),
                salvando.getNCdFormato(), salvando.getNVlComprimento(), salvando.getNVlAltura(),
                salvando.getNVlLargura(), salvando.getNVlDiametro(), salvando.getSCdMaoPropria(),
                salvando.getNVlValorDeclarado(), salvando.getNVlPeso());
        List<CServico> lista = resultado.getServicos().getCServico();
        for(CServico serv: lista){
            valorFrete = serv.getValor();
            valorFrete = valorFrete.replace(",", ".");
            Double valor = Double.parseDouble(valorFrete);
            objeto.setValorFrete(valor);
            
            prazo = serv.getPrazoEntrega();
            objeto.setPrazo(prazo + " dia(s)");
            
            cepOrigem = salvando.getSCepOrigem();
            objeto.setCepOrigem(cepOrigem);
            
            cepDestino = salvando.getSCepDestino();
            objeto.setCepDestino(cepDestino);
        }
    }

    public void remover(Integer id) {
        dao.remover(id);
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public ClienteDAO getDao() {
        return dao;
    }

    public void setDao(ClienteDAO dao) {
        this.dao = dao;
    }

    public CalcPrecoPrazoWS getServicoCorreio() {
        return servicoCorreio;
    }

    public void setServicoCorreio(CalcPrecoPrazoWS servicoCorreio) {
        this.servicoCorreio = servicoCorreio;
    }

    public CalcPrecoPrazo getSalvando() {
        return salvando;
    }

    public void setSalvando(CalcPrecoPrazo salvando) {
        this.salvando = salvando;
    }
}
