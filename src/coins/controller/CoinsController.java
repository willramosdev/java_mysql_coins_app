/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coins.controller;

import auth.model.User;
import coins.dao.CoinDAO;
import coins.dao.CotacaoDao;
import coins.dao.WalletDAO;
import coins.model.Coin;
import coins.model.Cotacao;
import coins.model.Wallet;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class CoinsController {
    User user;
    Wallet wallet;
    Coin coin;
    WalletDAO wdao = new WalletDAO();
    CoinDAO cdao = new CoinDAO();
    
    
    public CoinsController(User user) throws SQLException {
        this.user = user;
        this.wallet = wdao.findWallet(user.getId());
    }

    public Wallet getWallet() throws SQLException {
        return wdao.findWallet(user.getId());
    }
    
    
    public boolean testeLimite(double valor) {
        double dinheiro = this.wallet.getDinheiro();
        return dinheiro >= valor;
    }
    
    public void compraMoeda(int idWallet, int idMoeda, double valor, double unidades) throws SQLException {
        double retornoMoedas = cdao.alteraUnidadesMoeda(idMoeda, unidades);
        System.out.println(retornoMoedas);
        valor -= valor * getEncargosCompra();
        double retornoDinheiro = wdao.alteraDinheiro(idWallet, valor);
        System.out.println(retornoDinheiro);
    }
    
    public double adicionarDinheiro (int id, double valor) throws SQLException {
        return  wdao.alteraDinheiro(id, valor);
    }
    
    public List getCoins() throws SQLException {
       List coins = cdao.findCoins(); 
       return coins;
    }
    public Coin getCoin(int i) throws SQLException {
       Coin rcoin = cdao.findCoin(i); 
       return rcoin;
    }
    
    public ArrayList<ArrayList<String>> getTableData() throws SQLException{
        
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        List coinsList = getCoins();
        
        for(int i = 0; i < coinsList.size(); i++ ) {
            coin = (Coin) coinsList.get(i);
            ArrayList<String> linha = new ArrayList();
            linha.add(coin.getNome()); 
            linha.add(String.valueOf(coin.getUnidades())); 
            linha.add(String.valueOf(coin.getCotacao())); 
            linha.add(String.valueOf(coin.getCotacao()*coin.getUnidades())); 
            data.add(linha);
        }
        return data;
    }
    
    public void vendeMoeda(int id, double quantidade) {
        try {
            Coin moeda = this.cdao.findCoin(id);
            
            //se tiver moeda suficiente
            if (verificaSaldoMoeda(moeda, quantidade)) {
                //pega cotacao da moeda
                double cotacao = moeda.getCotacao();
                
                //calcula dinheiro a ser acrescido sem imposto
                double valor = cotacao * quantidade;
                
                //aplica o imposto
                valor -= valor * getEncargosVenda();
                System.out.println("Valor com imposto" + valor);
                
                //pega o id da carteira
                int carteiraID = moeda.getWallet();
                
                //calcula a quantidade que sobra
                double quantidadeRestante = moeda.getUnidades() - quantidade;
                
                //altera a quantidade de moedas
                cdao.alteraUnidadesMoeda(id, quantidadeRestante);
                
                //acresce o saldo novo em R$ 
                Wallet carteira = wdao.findWallet(carteiraID);
                double saldoAtual = carteira.getDinheiro();
                valor = saldoAtual + valor;
                wdao.alteraDinheiro(carteiraID, valor);
            }else {
                JOptionPane.showMessageDialog(
                    null, 
                    "Você não pode retirar mais do que possui."
                );
            }
        }catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    public double getEncargosVenda() {
        CotacaoDao ctdao = new CotacaoDao(); 
        Cotacao ct = ctdao.findCotacao("geral venda");
        Double retorno = ct.getImposto() + ct.getCorretagem();
        return retorno;
    }
    
    public double getEncargosCompra() {
        CotacaoDao ctdao = new CotacaoDao(); 
        Cotacao ct = ctdao.findCotacao("geral compra");
        Double retorno = ct.getImposto() + ct.getCorretagem();
        return retorno;
    }
    
    public boolean verificaSaldoMoeda(Coin coin, double quantidade){
        System.out.println("Unidades da moeda " + coin.getUnidades());
        System.out.println("Quantidade a ser removida " + quantidade);
        return coin.getUnidades() >= quantidade;
    }
    
}
