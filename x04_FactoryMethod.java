package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.ArrayList;

import static gghype.x00_DesignAndAchitecture.Patterns.AnimalScenario.*;

abstract class Animal{String type; String getType(){return type;}}
class Cat extends Animal {String type = "Cat";  String getType(){return type;}}
class Dog extends Animal {String type = "Dog";  String getType(){return type;}}
class RubberDuck extends Animal{String type = "Duck"; String getType(){return type;}}

enum AnimalScenario
{
    MANY_CATS,
    MANY_DOGS,
}

class FactoryOfAnimals
{
    static Animal create(AnimalScenario scenario)
    {
        int random = (int)(Math.random()*100);
        switch(scenario)
        {
            case MANY_CATS:
                if(random >= 0 && random <= 60)
                    return new Cat();
                if(random > 60 && random <= 75)
                    return new Dog();
                return new RubberDuck();
            case MANY_DOGS:
                if(random >= 0 && random <= 60)
                    return new Dog();
                if(random > 60 && random <= 75)
                    return new RubberDuck();
                return new RubberDuck();            // <- no cats in the dog's park :)
            default:
                return new RubberDuck();
        }
    }
}

class Park
{
    ArrayList<Animal> animals = new ArrayList<>();
    Park(AnimalScenario scenario, int numberOfAnimals)
    {
        while(numberOfAnimals > 0)
        {
            numberOfAnimals -= 1;
            animals.add(FactoryOfAnimals.create(scenario));
        }
    }
}

public class x04_FactoryMethod
{
    public static void main(String[] args)
    {
        Park catPark = new Park(MANY_CATS,10);
        System.out.println("CatPark:");
        for(var animal : catPark.animals)
        {
            System.out.println(animal.getType());
        }
        System.out.println("---");
        Park dogPark = new Park(MANY_DOGS,5);
        System.out.println("DogPark:");
        for(var animal : dogPark.animals)
        {
            System.out.println(animal.getType());
        }
    }
}
