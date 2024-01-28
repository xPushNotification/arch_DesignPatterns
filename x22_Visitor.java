package gghype.x00_DesignAndAchitecture.Patterns;

//
// Functionality:
// --------------
class SendMessageToTheClientFunctionality
{
    void sendMessageToTheClientA(BaseClient client)
    {
        System.out.println("---");
        System.out.println("sending message to the client type A");
        System.out.println(client.name);
        System.out.println(client.address);
    }
    void sendMessageToTheClientB(BaseClient client)
    {
        System.out.println("---");
        System.out.println("sending message to the client type B");
        System.out.println(client.name);
        System.out.println(client.address);
    }
}

//
// Base types and interfaces:
// --------------------------
interface Visitor
{
    void acceptVisitor(SendMessageToTheClientFunctionality visitor);
}
abstract class BaseClient implements Visitor
{
    String name = "name";
    String address = "address";
}

//
// Classes with the Visitor pattern:
// ---------------------------------
class ClientClassA extends BaseClient
{
    @Override
    public void acceptVisitor(SendMessageToTheClientFunctionality visitor)
    {
        visitor.sendMessageToTheClientA(this);
    }
}
class ClientClassB extends BaseClient
{
    @Override
    public void acceptVisitor(SendMessageToTheClientFunctionality visitor)
    {
        visitor.sendMessageToTheClientB(this);
    }
}

//
// use iteration instead of "instanceof" checking
// ----------------------------------------------
public class x22_Visitor
{
    public static void main(String[] args)
    {
        BaseClient[] clients = new BaseClient[]{
                new ClientClassA(), new ClientClassA(),
                new ClientClassB(), new ClientClassA()
        };
        var messagingService = new SendMessageToTheClientFunctionality();

        for(var client : clients)
        {
            client.acceptVisitor(messagingService);
        }
    }
}
