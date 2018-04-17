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
public class Wallet {
    private int id;
    private int user;
    private double dinheiro;
    private Coin coins[];

    public Wallet(int id, int user, double dinheiro) {
        this.id = id;
        this.user = user;
        this.dinheiro = dinheiro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(float dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Coin[] getCoins() {
        return coins;
    }

    public void setCoins(Coin[] coins) {
        this.coins = coins;
    }
    
    
}
