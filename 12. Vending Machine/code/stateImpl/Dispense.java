package stateImpl;

import java.util.List;

import Interfaces.State;
import src.VendingMachine;
import src.Item;
import enums.Coin;


public class Dispense implements State {
    Dispense(VendingMachine machine, int codeNumber) throws Exception{
        System.out.println("Currently Vending machine is in Dispense state");
        dispenseProduct(machine, codeNumber);
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception{
        throw new Exception("Insert coin button can not clicked on Dispense state");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("Product selection button can not be clicked in Dispense state");

    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception{
        throw new Exception("Coin can not be inserted in Dispense state");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception{
        throw new Exception("Product can not be choosen in Dispense state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception{
        throw new Exception("Change can not returned in Dispense state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception{
        throw new Exception("Refund can not be happen in Dispense state");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception{
        System.out.println("Product has been dispensed");
        Item item = machine.getInventory().getItem(codeNumber);
        machine.getInventory().updateSoldOutItem(codeNumber);
        machine.setVendingMachineState(new Idle(machine));
        return item;
    }

    @Override
    public void updateInventory(VendingMachine machine, Item item, int codeNumber) throws Exception {
        throw new Exception("Inventory can not be updated in Dispense state");
    }
}

