package gghype.x00_DesignAndAchitecture.Patterns;

//
// (01) branch of the static inheritance:
// --------------------------------------
abstract class Brew
{
    int getCost(){return 0;}
    String getName(){return "Unnamed Brewery";}
}

class Coffee extends Brew
{
    String name = "Coffee";
    int cost = 1;
    int getCost(){return cost;}
    String getName(){return name;}
}

class Tea extends Brew
{
    String name = "Tea";
    int cost = 2;
    int getCost(){return cost;}
    String getName(){return name;}
}

//
// (02) branch of the dynamic inheritance with the decorators:
// -----------------------------------------------------------
abstract class BrewDecorator extends Brew
{
    Brew baseObject;
    String name;
    int cost;
    int getCost(){ return baseObject.getCost() + cost; }
    String getName()
    {
        if(baseObject instanceof BrewDecorator)
            return baseObject.getName() + " and " + name;

        return baseObject.getName() + " with " + name;
    }
}

class SugarDecorator extends BrewDecorator
{
    SugarDecorator(Brew baseObject)
    {
        super.name = "Sugar";
        super.cost = 3;
        super.baseObject = baseObject;
    }
}

class MilkDecorator extends BrewDecorator
{
    MilkDecorator(Brew baseObject)
    {
        super.name = "Milk";
        super.cost = 4;
        super.baseObject = baseObject;
    }
}

public class x03_Decorator
{
    public static void main(String[] args)
    {
        Brew myBrew = new Coffee();

        System.out.println("--");               // Coffee
        System.out.println(myBrew.getName());
        System.out.println(myBrew.getCost());
        System.out.println("--");               // Coffee with Sugar
        myBrew = new SugarDecorator(myBrew);
        System.out.println(myBrew.getName());
        System.out.println(myBrew.getCost());
        System.out.println("--");
        myBrew = new MilkDecorator(myBrew);     // Coffee with Sugar / Milk
        System.out.println(myBrew.getName());
        System.out.println(myBrew.getCost());
        System.out.println("--");

        // via one liner:
        Brew myNewBrew = new SugarDecorator(new Tea());
        System.out.println(myNewBrew.getName());
        System.out.println(myNewBrew.getCost());
        System.out.println("--");
    }
}
