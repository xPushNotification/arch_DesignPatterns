package gghype.x00_DesignAndAchitecture.Patterns;

//
// (01) Object (Product) interfaces:
// ---------------------------------
interface Window
{
    void doAsAWindow();
}
interface Button
{
    void doAsAButton();
}

//
// (02) Object (Product) implementations:
// --------------------------------------
class WindowLinux implements Window
{
    @Override
    public void doAsAWindow(){System.out.println("WindowLinux");}
}
class WindowMac implements Window
{
    @Override
    public void doAsAWindow(){System.out.println("WindowMac");}
}
class ButtonLinux implements Button
{
    @Override
    public void doAsAButton(){System.out.println("ButtonLinux");}
}
class ButtonMac implements Button
{
    @Override
    public void doAsAButton(){System.out.println("ButtonMac");}
}

//
// (03) Abstract Factory Interface:
// --------------------------------
interface UIFactory
{
    Window createWindow();
    Button createButton();
}

//
// (04) Factories implementations:
// -------------------------------
class UIFactoryLinux implements UIFactory
{
    @Override
    public Window createWindow(){return new WindowLinux();}
    @Override
    public Button createButton(){return new ButtonLinux();}
}
class UIFactoryMac implements UIFactory
{
    @Override
    public Window createWindow(){return new WindowMac();}
    @Override
    public Button createButton(){return new ButtonMac();}
}

//
// -----------------------------
public class x05_AbstractFactory
{
    public static void main(String[] args)
    {
        UIFactory linuxUIFactory = new UIFactoryLinux();
            var linuxWindow = linuxUIFactory.createWindow();
            var linuxButton = linuxUIFactory.createButton();
        linuxWindow.doAsAWindow();
        linuxButton.doAsAButton();

        UIFactory macUIFactory = new UIFactoryMac();
            var macWindow = macUIFactory.createWindow();
            var macButton = macUIFactory.createButton();
        macWindow.doAsAWindow();
        macButton.doAsAButton();
    }
}
