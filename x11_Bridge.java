package gghype.x00_DesignAndAchitecture.Patterns;

//
// Method for the Pizza Cooking:
// ---------------------------------------------------------------
interface MethodOfThePizza
{
    void addMeat();
    void addVegetable();
    void addTopics();
}
class VegetablePizza implements MethodOfThePizza
{
    @Override
    public void addMeat(){System.out.println("Vegetable: add meat");}
    @Override
    public void addVegetable(){System.out.println("vegetable: add vegetable");}
    @Override
    public void addTopics(){System.out.println("vegetable: add topics");}
}
class OriginalPizza implements MethodOfThePizza
{
    @Override
    public void addMeat(){System.out.println("Original: add meat");}
    @Override
    public void addVegetable(){System.out.println("Original: add vegetable");}
    @Override
    public void addTopics(){System.out.println("Original: add topics");}
}
//
// Type for the Pizza Cooking:
// ---------------------------------------------------------------
abstract class Pizza
{
    MethodOfThePizza methodOfCooking;           // <- the bridge (!!)
    Pizza(MethodOfThePizza methodOfCooking){this.methodOfCooking = methodOfCooking;}
    void rust(){System.out.println("pizza: rust");}
    void deliver(){System.out.println("pizza: deliver");}
}
class AmericanPizza extends Pizza
{
    AmericanPizza(MethodOfThePizza methodOfThePizza){super(methodOfThePizza);}
}
class ItalianPizza extends Pizza
{
    ItalianPizza(MethodOfThePizza methodOfThePizza){super(methodOfThePizza);}
}
//
// Client of using the code:
// ---------------------------------------------------------------
public class x11_Bridge
{
    public static void main(String[] args)
    {
        //                   <- axis one       <- axis two
        Pizza pizzaOne = new AmericanPizza(new OriginalPizza());
        Pizza pizzaTwo = new ItalianPizza(new VegetablePizza());

        pizzaOne.methodOfCooking.addMeat();
        pizzaOne.methodOfCooking.addVegetable();
        pizzaOne.methodOfCooking.addTopics();
        pizzaOne.rust();
        pizzaOne.deliver();

        System.out.println("---");

        pizzaTwo.methodOfCooking.addMeat();
        pizzaTwo.methodOfCooking.addVegetable();
        pizzaTwo.methodOfCooking.addTopics();
        pizzaTwo.rust();
        pizzaTwo.deliver();
    }
}
