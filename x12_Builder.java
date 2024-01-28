package gghype.x00_DesignAndAchitecture.Patterns;

//
// - what we build:
//
class CarExemplar
{
    Integer uid = 0;
    String color = "Red";
    CarExemplar(Integer uid, String color)
    {
        // <- pattern assumes that we check for the parameter's nulls:
        if(uid != null) this.uid = uid;
        if(color != null) this.color = color;
    }

    @Override
    public String toString()
    {
        return "CarExemplar{" +
                "uid=" + uid +
                ", color='" + color + '\'' +
                '}';
    }
}

//
// - how we build
//
interface CarBuilderInterface
{
    CarBuilderInterface uid(int uid);
    CarBuilderInterface color(String color);
    CarExemplar build();
}
class CarBuilder implements CarBuilderInterface
{
    Integer uid;
    String color;

    @Override
    public CarBuilderInterface uid(int uid)
    {
        this.uid = uid;
        return this;
    }

    @Override
    public CarBuilderInterface color(String color)
    {
        this.color = color;
        return this;
    }

    @Override
    public CarExemplar build()
    {
        //                     <- "null" can come from this place
        return new CarExemplar(uid, color);
    }
}

//
// - predefined configurations:
//
class DirectorCarBuilder
{
    static CarExemplar[] buildBlueCars(int number)
    {
        CarExemplar[] cars = new CarExemplar[number];
        CarBuilder cb = new CarBuilder();
        cb.color("blue");
        for(int i = 0; i < number; i++)
        {
            cb.uid(i);
            cars[i] = cb.build();
        }
        return cars;
    }
}

public class x12_Builder
{
    public static void main(String[] args)
    {
        var cb = new CarBuilder();
        cb.color("magenta");

        var car01 = cb.build();
        System.out.println(car01);

        cb = new CarBuilder();      // <- reset
        var car02 = cb.uid(20).color("blue").build();
        System.out.println(car02);

        System.out.println("--");
        var blueCars = DirectorCarBuilder.buildBlueCars(10);
        for(int i = 0; i < 10; i++)
        {
            System.out.println(blueCars[i]);
        }
    }
}
