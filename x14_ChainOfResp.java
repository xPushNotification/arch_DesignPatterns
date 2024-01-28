package gghype.x00_DesignAndAchitecture.Patterns;

//
// - root handler:
//
abstract class HandlerUserNameAndPassword
{
    HandlerUserNameAndPassword next;
    HandlerUserNameAndPassword setNextHandler(HandlerUserNameAndPassword next)
    {
        this.next = next;
        return next;
    }
    abstract boolean handle(String name, String password);
    boolean handleNext(String name, String password)
    {
        if(next == null) return true;
        return next.handle(name,password);
    }
}
//
// - additional handlers:
//
class HandlerUserName extends HandlerUserNameAndPassword
{
    @Override
    boolean handle(String name, String password)
    {
        if(!name.equals("name")) return false;
        return handleNext(name,password);
    }
}
class HandlerPassword extends HandlerUserNameAndPassword
{
    @Override
    boolean handle(String name, String password)
    {
        if(!password.equals("password")) return false;
        return handleNext(name,password);
    }
}
//
// - handler client:
//
class AuthService
{
    HandlerUserNameAndPassword chainRoot;
    AuthService(HandlerUserNameAndPassword chainRoot)
    {
        this.chainRoot = chainRoot;
    }
    boolean isAuthOk(String name, String password)
    {
        if(chainRoot.handle(name,password)) return true;
        return false;
    }
}

public class x14_ChainOfResp
{
    public static void main(String[] args)
    {
        HandlerUserNameAndPassword chainOfHandlers =
                new HandlerUserName().setNextHandler(new HandlerPassword());
        var auth = new AuthService(chainOfHandlers);
        System.out.println(auth.isAuthOk("name","password"));       // true
        System.out.println(auth.isAuthOk("name1","password1"));     // false
    }
}
