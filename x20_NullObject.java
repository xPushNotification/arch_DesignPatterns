package gghype.x00_DesignAndAchitecture.Patterns;

interface FlyStrategy
{
    void fly();
}
class FlyStrategyFast implements FlyStrategy
{
    @Override
    public void fly(){System.out.println("Fly fast");}
}
class FlyStrategySlow implements FlyStrategy
{
    @Override
    public void fly(){System.out.println("Fly slow");}
}
class FlyStrategyNull implements FlyStrategy
{
    @Override
    public void fly(){return;}
}

class Mob
{
    FlyStrategy flyStrategy;
    Mob(FlyStrategy flyStrategy){this.flyStrategy = flyStrategy;}
    void fly(){flyStrategy.fly();}
}

public class x20_NullObject
{
    public static void main(String[] args)
    {
        var mob1 = new Mob(new FlyStrategyFast());
        var mob2 = new Mob(new FlyStrategyNull());
        var mob3 = new Mob(new FlyStrategySlow());

        mob1.fly();
        mob3.fly();

        mob2.fly();                                 // nothingness now, but..
        mob2.flyStrategy = new FlyStrategyFast();   // ..now can
        mob2.fly();
    }
}
