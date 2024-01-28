package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.HashMap;

//
// Mediator interface:
// -------------------
interface Mediator
{
    void sendMessage(String message, Object from);
}

//
// Objects:
// --------
abstract class UIElement
{
    Mediator mediator;
    UIElement(Mediator mediator){this.mediator = mediator;}
}
class LoginButton extends UIElement
{
    LoginButton(Mediator mediator)
    {
        super(mediator);
    }
    void click()
    {
        mediator.sendMessage("login button clicked!", this);
    }
}
class TextField extends UIElement
{
    String data;
    TextField(Mediator mediator)
    {
        super(mediator);
        data = "placeholder";
    }
    void setData(String newInput)
    {
        data = newInput;
        mediator.sendMessage("input field has changed", this);
    }
}

//
// Grouping of the objects:
// ------------------------
class Dialogue
{
    //      <- name     <- component
    HashMap<String,     UIElement> components = new HashMap<>();
    HashMap<UIElement,  String>    reverseComponents = new HashMap<>();
    void addComponent(String name, UIElement element)
    {
        components.put(name, element);
        reverseComponents.put(element, name);
    }
}

//
// Scenario controller:
// --------------------
class DialogueWithTheLoginButton extends Dialogue implements Mediator
{
    // <- data for the controller / initialization of the scenario:
    DialogueWithTheLoginButton()
    {
        addComponent("loginField",new TextField(this));
        addComponent("passwordField", new TextField(this));
        addComponent("loginButton", new LoginButton(this));
    }

    // <- router for the objects (control intersections):
    @Override
    public void sendMessage(String message, Object from)
    {
        if(!components.containsValue(from)) return;

        String name = reverseComponents.get(from);
        if(name.equals("loginButton") && message.equals("login button clicked!"))
        {
            onLoginButtonClick();
        }
    }

    // on event (reaction for the intersection):
    void onLoginButtonClick()
    {
        TextField login = (TextField)components.get("loginField");
        TextField password = (TextField)components.get("passwordField");
        System.out.println("login button clicked with the following conditions:");
        System.out.println(login.data);
        System.out.println(password.data);
    }
}

public class x16_Mediator
{
    public static void main(String[] args)
    {
        DialogueWithTheLoginButton dwb01 = new DialogueWithTheLoginButton();
        DialogueWithTheLoginButton dwb02 = new DialogueWithTheLoginButton();

        //
        // emulate work with the components:
        ((TextField)dwb01.components.get("loginField")).setData("myLogin");
        ((TextField)dwb01.components.get("passwordField")).setData("myPassword");
        ((TextField)dwb02.components.get("loginField")).setData("myLogin111");
        ((TextField)dwb02.components.get("passwordField")).setData("myPassword111");

        //
        // event:
        ((LoginButton)dwb01.components.get("loginButton")).click();
        ((LoginButton)dwb02.components.get("loginButton")).click();
    }
}
