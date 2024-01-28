package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.PriorityQueue;

class Resource{}
class NotSoSingleton
{
    //
    // static fields:
    // -------------------------------------
    private final static Resource resourceA = new Resource();
    private final static Resource resourceB = new Resource();
    private final static NotSoSingleton singletonA = new NotSoSingleton(resourceA);
    private final static NotSoSingleton singletonB = new NotSoSingleton(resourceB);

    //
    // normal fields:
    // -------------------------------------
    PriorityQueue<String> tasks = new PriorityQueue<>();
    private final Resource resource;

    private NotSoSingleton(Resource resource)
    {
        this.resource = resource;
    }

    // <- not so singleton / nor the factory / is a manager of resources associated with only this class
    static NotSoSingleton getInstance()
    {
        if(singletonA.tasks.size() < singletonB.tasks.size())
        {
            System.out.println("instantiated singletonA");
            return singletonA;
        }
        else
        {
            System.out.println("instantiated singletonB");
            return singletonB;
        }
    }
}

public class x06_NotSoSingleton
{
    public static void main(String[] args)
    {
        // -- singletonB
        var notSoSingleton01 = NotSoSingleton.getInstance();
            notSoSingleton01.tasks.add("task 01");
            notSoSingleton01.tasks.add("task 02");
            notSoSingleton01.tasks.add("task 03");

        for(var element : notSoSingleton01.tasks)
        {
            System.out.println(element);
        }

        System.out.println("---");
        // -- singletonA
        var notSoSingleton02 = NotSoSingleton.getInstance();
            notSoSingleton02.tasks.add("task 04");
            notSoSingleton02.tasks.add("task 05");
            notSoSingleton02.tasks.add("task 06");
            notSoSingleton02.tasks.add("task 07");
        for(var element : notSoSingleton02.tasks)
        {
            System.out.println(element);
        }

        System.out.println("---");
        // -- singletonB
        var notSoSingleton03 = NotSoSingleton.getInstance();
            notSoSingleton03.tasks.add("task 08");
            notSoSingleton03.tasks.add("task 09");
            notSoSingleton03.tasks.add("task 10");
        for(var element : notSoSingleton03.tasks)
        {
            System.out.println(element);
        }
    }
}
