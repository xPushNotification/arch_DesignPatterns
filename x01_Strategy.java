package gghype.x00_DesignAndAchitecture.Patterns;

//
// Design Pattern: Strategy
// ========================

//
// interfaces:
// -----------
interface FlyAbility{void doFly();}
interface QuackAbility{void doQuack();}

//
// interface implementations:
// --------------------------
class FlyAbilityStandard implements FlyAbility
{
    @Override
    public void doFly(){System.out.println("do standard fly");}
}
class FlyAbilityNoHave implements FlyAbility
{
    @Override
    public void doFly(){System.out.println("have no fly");}
}
class QuackAbilityStandard implements QuackAbility
{
    @Override
    public void doQuack(){System.out.println("do standard quack");}
}
class QuackAbilityNoHave implements QuackAbility
{
    @Override
    public void doQuack(){System.out.println("have no quack");}
}

//
// one configured object:
// ----------------------
class Duck implements FlyAbility,QuackAbility
{
    private final FlyAbility flyAbility;
    private final QuackAbility quackAbility;

    Duck(FlyAbility flyAbility, QuackAbility quackAbility)
    {
        this.flyAbility = flyAbility;
        this.quackAbility = quackAbility;
    }

    @Override
    public void doFly(){flyAbility.doFly();}

    @Override
    public void doQuack(){quackAbility.doQuack();}
}

// --------------------------------------------------------------------------
public class x01_Strategy
{
    public static void main(String[] args)
    {
        Duck rubberDuck = new Duck(new FlyAbilityNoHave(), new QuackAbilityNoHave());
        Duck normalDuck = new Duck(new FlyAbilityStandard(), new QuackAbilityStandard());

        rubberDuck.doFly();
        rubberDuck.doQuack();

        normalDuck.doFly();
        normalDuck.doQuack();
    }
}
