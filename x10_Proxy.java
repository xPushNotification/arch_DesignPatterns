package gghype.x00_DesignAndAchitecture.Patterns;

//
// - original functionality /coupling:
interface OriginalFunctionalityAbility
{
    void doFunctionality();
}
class OriginalFunctionality implements OriginalFunctionalityAbility
{
    String data;
    OriginalFunctionality(String data){this.data = data;}

    @Override
    public void doFunctionality(){System.out.println(data);}
}

//
// - control of access /additions:
class AuthTest{static boolean testAuth(){return true;}}
class OriginalFunctionalityProxy implements OriginalFunctionalityAbility
{
    OriginalFunctionalityAbility originalObject;
    OriginalFunctionalityProxy(String data){originalObject = new OriginalFunctionality(data);}

    @Override
    @SuppressWarnings("all")
    public void doFunctionality()
    {
        //
        // additional functionality:
        if(AuthTest.testAuth() != true) throw new Error("error with the auth");
        System.out.println("auth test is ok");

        //
        // original functionality:
        originalObject.doFunctionality();
    }
}


public class x10_Proxy
{
    public static void main(String[] args)
    {
        //
        // original functionality:
        OriginalFunctionalityAbility ofo = new OriginalFunctionality("the test data");
        ofo.doFunctionality();

        //
        // recreate object with the proxy /obtain control of the ref of the object:
        OriginalFunctionalityAbility proxyObject = new OriginalFunctionalityProxy("the test data");
        ofo = proxyObject;      // <- rewrite the link
        ofo.doFunctionality();  // <- now object act via proxy
    }
}
