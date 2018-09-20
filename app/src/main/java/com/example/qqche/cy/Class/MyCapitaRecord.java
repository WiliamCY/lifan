package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyCapitaRecord {
    private String crAmountofcollection,crAllTotal,crbalance,crTotal,crblockedBalances,crMessage;

    public MyCapitaRecord(String crAmountofcollection,String crAllTotal,String crbalance,String crTotal,String crblockedBalances,String crMessage){
       this.crAmountofcollection = crAmountofcollection;
       this.crAllTotal = crAllTotal;
       this.crbalance = crbalance;
       this.crTotal = crTotal;
       this.crblockedBalances = crblockedBalances;
       this.crMessage = crMessage;

    }

    public String getCrAllTotal() {
        return crAllTotal;
    }

    public String getCrAmountofcollection() {
        return crAmountofcollection;
    }

    public String getCrbalance() {
        return crbalance;
    }

    public String getCrblockedBalances() {
        return crblockedBalances;
    }

    public String getCrMessage() {
        return crMessage;
    }

    public String getCrTotal() {
        return crTotal;
    }

    public void setCrAllTotal(String crAllTotal) {
        this.crAllTotal = crAllTotal;
    }

    public void setCrAmountofcollection(String crAmountofcollection) {
        this.crAmountofcollection = crAmountofcollection;
    }

    public void setCrbalance(String crbalance) {
        this.crbalance = crbalance;
    }

    public void setCrblockedBalances(String crblockedBalances) {
        this.crblockedBalances = crblockedBalances;
    }

    public void setCrMessage(String crMessage) {
        this.crMessage = crMessage;
    }

    public void setCrTotal(String crTotal) {
        this.crTotal = crTotal;
    }
}
