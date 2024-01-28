package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.ArrayList;
import java.util.HashMap;

//
// Objects which can be used as a prototype:
// -----------------------------------------
class VehiclePrototype
{
    int one;
    int two;
    VehiclePrototype(int one, int two)
    {
        this.one = one; this.two = two;
    }
    VehiclePrototype(VehiclePrototype fromVehicle)      // copy constructor
    {
        this.one = fromVehicle.one;
        this.two = fromVehicle.two;
    }
    VehiclePrototype cloneMe()                          // cloning
    {
        VehiclePrototype vp = new VehiclePrototype(this);
        return vp;
    }

    @Override
    public String toString()
    {
        return "VehiclePrototype{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}
class CarPrototype extends VehiclePrototype
{
    int three;
    CarPrototype(int one, int two, int three)
    {
        super(one,two);         // !
        this.three = three;
    }
    CarPrototype(CarPrototype fromCar)
    {
        super(fromCar);         // !
        this.three = fromCar.three;
    }
    CarPrototype cloneMe()
    {
        CarPrototype nc = new CarPrototype(this);
        return nc;
    }

    @Override
    public String toString()
    {
        return "CarPrototype{" +
                "three=" + three +
                "} " + super.toString();
    }
}

//
// Prototype Registry:
// -------------------
enum carType {CAR100, CAR200, CAR300, VEH250}
class VehiclePrototypeRegistry
{
    HashMap<carType, VehiclePrototype> prototypes = new HashMap<>();
    VehiclePrototypeRegistry()
    {
        prototypes.put(carType.CAR100, new CarPrototype(100,100,100));
        prototypes.put(carType.CAR200, new CarPrototype(200,200,200));
        prototypes.put(carType.CAR300, new CarPrototype(300,300,300));
        prototypes.put(carType.VEH250, new VehiclePrototype(250,250));
    }
    VehiclePrototype get(carType type)
    {
        return prototypes.get(type);
    }
    ArrayList<VehiclePrototype> getBunch()
    {
        ArrayList<VehiclePrototype> list = new ArrayList<>();
        for(var element : prototypes.values())
        {
            list.add(element.cloneMe());            // clone in the for loop
        }
        return list;
    }
}

public class x13_Prototype
{
    public static void main(String[] args)
    {
        //
        // simple cloning from the prototype:
        var car = new CarPrototype(199,299,399);
        System.out.println(car);
        var newCar = car.cloneMe();
        System.out.println(newCar);
        System.out.println(car.equals(newCar));     // definitely a clone

        //
        // from the registry:
        var registry = new VehiclePrototypeRegistry();
        //
        var car100 = registry.get(carType.CAR100).cloneMe();
        var car200 = registry.get(carType.CAR200).cloneMe();
        var car300 = registry.get(carType.CAR300).cloneMe();
        System.out.println(car100);
        System.out.println(car200);
        System.out.println(car300);

        System.out.println("---");
        System.out.println("bunch cloning:");
        var list = registry.getBunch();
        list.forEach(System.out::println);
    }
}
