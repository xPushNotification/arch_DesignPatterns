package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.ArrayList;

//
// (01) Interfaces:
// ----------------
interface AbilityActAsASubscriber
{
    void updateMeAsASubscriber();
}
interface AbilityActAsASubscriptionService
{
    void addSubscriber(AbilityActAsASubscriber subscriberToBeAdd);
    void removeSubscriber(AbilityActAsASubscriber subscriberToBeRemoved);

    void notifySubscribers();
    void setData(int data);

    String getDataBySubscriber();
}

//
// (02) Implementation of Subscriber:
// ----------------------------------
class Subscriber implements AbilityActAsASubscriber
{
    int uid = (int)(Math.random()*100);
    AbilityActAsASubscriptionService subscriptionService;

    Subscriber(AbilityActAsASubscriptionService subscriptionService)
    {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void updateMeAsASubscriber()
    {
        System.out.println("subscriber "+uid+" updated");
        System.out.println("obtained data:");
        System.out.println(subscriptionService.getDataBySubscriber());
    }
}

//
// (03) Implementation of SubscriptionService:
// -------------------------------------------
class SubscriptionService implements AbilityActAsASubscriptionService
{
    ArrayList<AbilityActAsASubscriber> subscribers = new ArrayList<>();
    int data = 0;

    @Override
    public void setData(int data){this.data = data;}

    @Override
    public void addSubscriber(AbilityActAsASubscriber subscriberToBeAdd)
    {
        subscribers.add(subscriberToBeAdd);
    }

    @Override
    public void removeSubscriber(AbilityActAsASubscriber subscriberToBeRemoved)
    {
        subscribers.remove(subscriberToBeRemoved);
    }

    @Override
    public void notifySubscribers()
    {
        for(var sub : subscribers)
        {
            sub.updateMeAsASubscriber();
        }
    }

    @Override
    public String getDataBySubscriber()
    {
        return "generatedData:"+data;
    }
}

//
// (04) Testing:
// -------------
public class x02_Observer
{
    public static void main(String[] args)
    {
        AbilityActAsASubscriptionService service = new SubscriptionService();

        Subscriber subscriberA = new Subscriber(service);
        Subscriber subscriberB = new Subscriber(service);
        Subscriber subscriberC = new Subscriber(service);

        service.addSubscriber(subscriberA);
        service.addSubscriber(subscriberB);
        service.addSubscriber(subscriberC);

        service.setData(100);
        service.notifySubscribers();

        service.setData(777);
        service.notifySubscribers();

        System.out.println("---");

        service.removeSubscriber(subscriberC);
        service.setData(111);
        service.notifySubscribers();
    }
}
