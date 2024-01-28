package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.ArrayList;
import java.util.Arrays;

//
// Common interface for the leaves & composites:
// ---------------------------------------------
interface CanCalculatePrice
{
    double calculatePrice();
}

//
// Leaves:
// -------
abstract class Product implements CanCalculatePrice
{
    String title;
    double price;
    Product(String title, double price){this.title = title; this.price = price;}
    abstract public double calculatePrice();
}
class VideoGame extends Product
{
    VideoGame(String title, double price){super(title,price);}
    @Override
    public double calculatePrice(){return price;}
}
class Book extends Product
{
    Book(String title, double price){super(title,price);}
    @Override
    public double calculatePrice(){return price;}
}

//
// Composites:
// -----------
class CompositeBox implements CanCalculatePrice
{
    ArrayList<CanCalculatePrice> inBox = new ArrayList<>();
    CompositeBox(CanCalculatePrice... goods)
    {
        inBox.addAll(
            Arrays.asList(goods)
        );
    }
    @Override
    public double calculatePrice()
    {
        var sum = inBox.stream()
                .mapToDouble(CanCalculatePrice::calculatePrice) // <- recursion is here
                .sum();
        return sum;
    }
}

//
// Tree composer:
// --------------
class DeliveryService
{
    CanCalculatePrice rootBox;
    void setupOrder(CanCalculatePrice... goods){rootBox = new CompositeBox(goods);}
    double calculatePrice(){return rootBox.calculatePrice();}
}

public class x19_Composite
{
    public static void main(String[] args)
    {
        var ds = new DeliveryService();
        ds.setupOrder(
            new CompositeBox(
                new Book("1",1_000_000),
                new VideoGame("2",1_000_001)
            ),
            new CompositeBox(
                new Book("3",1_000_010)
            ),
            new CompositeBox(
                new CompositeBox(
                    new VideoGame("4",1_000_100),
                    new VideoGame("5",1_001_000)
                ),
                new Book("6",1_010_000)
            )
        );
        System.out.println(ds.calculatePrice());
    }
}
