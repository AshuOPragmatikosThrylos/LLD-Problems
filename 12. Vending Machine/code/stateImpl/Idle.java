package stateImpl;

import java.util.ArrayList;
import java.util.List;

import Interfaces.State;
import src.VendingMachine;
import src.Item;
import enums.Coin;

public class Idle implements State {
    public Idle(){
        System.out.println("Currently Vending machine is in Idle state");
    }

    public Idle(VendingMachine machine){
        System.out.println("Currently Vending machine is in Idle state");
        machine.setCoinList(new ArrayList<>());
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception{
        machine.setVendingMachineState(new HasMoney());
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("Click on insert coin button first");

    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception{
        throw new Exception("Cannot insert coin in idle state");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception{
        throw new Exception("Cannot choose product in idle state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception{
        throw new Exception("Cannot get change in idle state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception{
        throw new Exception("Cannot get refunded in idle state");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception{
        throw new Exception("Product can not be dispensed idle state");
    }

    @Override
    public void updateInventory(VendingMachine machine, Item item, int codeNumber) throws Exception {
        machine.getInventory().addItem(item, codeNumber);
    }
}

