/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coins.model;

/**
 *
 * @author Will
 */
public class Cotacao {
    private String nome;
    private double valor;
    private double corretagem;
    private double imposto;

    public Cotacao(String nome, double valor, double corretagem, double imposto) {
        this.nome = nome;
        this.valor = valor;
        this.corretagem = corretagem;
        this.imposto = imposto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getCorretagem() {
        return corretagem;
    }

    public void setCorretagem(double corretagem) {
        this.corretagem = corretagem;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }
    
    
}
