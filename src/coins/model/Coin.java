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
public class Coin {
    private int id;
    private String nome;
    private double cotacao;
    private double unidades;
    private int wallet;

    public Coin(int id, String nome, double cotacao, double unidades, int wallet) {
        this.id = id;
        this.nome = nome;
        this.cotacao = cotacao;
        this.unidades = unidades;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCotacao() {
        return this.cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public double getUnidades() {
        return unidades;
    }

    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }

    public int getWallet() {
        return this.wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
    
}
