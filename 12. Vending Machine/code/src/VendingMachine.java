package src;

import java.util.ArrayList;
import java.util.List;

import Interfaces.State;
import enums.Coin;
import stateImpl.Idle;

// (Context: VendingMachine) has (state: vendingMachineState)
public class VendingMachine {
    private State vendingMachineState;
    private Inventory inventory;
    private List<Coin> coinList; // stores inserted coins since VM takes money before product selection; helps compare total coin value to product price

    public VendingMachine(){
        vendingMachineState = new Idle();
        inventory = new Inventory(10);
        coinList = new ArrayList<>();
    }

    public State getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(State vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }
}

