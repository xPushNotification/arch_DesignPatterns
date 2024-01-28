package gghype.x00_DesignAndAchitecture.Patterns;

//
// independent object with the data structure:
class Inventory
{
    int[] slots = new int[]{111,222,333};
}

//
// hand-made iterator:
class InventoryIterator
{
    Inventory inventory;
    int currentIndex;
    int length;

    InventoryIterator(Inventory inventory)
    {
        this.inventory = inventory;
        currentIndex = 0;
        length = inventory.slots.length;
    }

    int  get()
    {
        if(length <= 0) return 0;
        return inventory.slots[currentIndex];
    }
    void moveNext(){currentIndex = (currentIndex + 1)%length;}
    void movePrev()
    {
        if((currentIndex - 1) < 0) currentIndex = length-1;
        else currentIndex -= 1;
    }
}

public class x17_Iterator
{
    public static void main(String[] args)
    {
        Inventory inventory = new Inventory();
        InventoryIterator ii = new InventoryIterator(inventory);
        InventoryIterator i2 = new InventoryIterator(inventory);

        System.out.println("---");
        System.out.println(ii.get());
        System.out.println(ii.get());
        i2.moveNext(); i2.moveNext();
        System.out.println(i2.get());       // <- can be more than one iterators

        System.out.println("--");
        ii.moveNext();
        System.out.println(ii.get());
        ii.moveNext();
        System.out.println(ii.get());
        ii.moveNext();
        System.out.println(ii.get());       // <- cycle through the dataset forward

        System.out.println("---");
        ii.movePrev();
        System.out.println(ii.get());
        ii.movePrev();
        System.out.println(ii.get());
        ii.movePrev();
        System.out.println(ii.get());
        ii.movePrev();
        System.out.println(ii.get());       // <- cycle through the dataset backward
    }
}
