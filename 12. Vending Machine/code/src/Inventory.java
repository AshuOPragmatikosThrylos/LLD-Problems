package src;

public class Inventory {
    ItemSlot[] inventory = null;

    Inventory(int itemCount) {
        inventory = new ItemSlot[itemCount];
        initialEmptyInventory();
    }

    public ItemSlot[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemSlot[] inventory) {
        this.inventory = inventory;
    }

    public void initialEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemSlot space = new ItemSlot();
            space.setCode(startCode);
            space.setSoldOut(true);
            inventory[i]= space;
            startCode++;
        }
    }

    public void addItem(Item item, int codeNumber) throws Exception {
        for (ItemSlot itemSlot : inventory) {
            if (itemSlot.code == codeNumber) {
                if (itemSlot.isSoldOut()) {
                    itemSlot.item = item;
                    itemSlot.setSoldOut(false);
                } else {
                    throw new Exception("Item already present, cannot add item here");
                }
            }
        }
    }

    public Item getItem(int codeNumber) throws Exception {
        for (ItemSlot itemSlot : inventory) {
            if (itemSlot.code == codeNumber) {
                if (itemSlot.isSoldOut()) {
                    throw new Exception("Item already sold out");
                } else {
                    return itemSlot.item;
                }
            }
        }
        throw new Exception("Invalid Code");
    }

    public void updateSoldOutItem(int codeNumber){
        for (ItemSlot itemSlot : inventory) {
            if (itemSlot.code == codeNumber) {
                itemSlot.setSoldOut(true);
            }
        }
    }
}


