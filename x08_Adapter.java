package gghype.x00_DesignAndAchitecture.Patterns;

//
// (01) Some unrelated classes:
// ----------------------------
final class AdaptedClassA
{
    final int data = 111;
}
final class AdaptedClassB
{
    final String data1 = "one";
    final String data2 = "two";
}

//
// (02) Desired interface):
// -----------------------
// Can be one of implemented interfaces
// from AdaptedClassA / AdaptedClassB if they have such interfaces
// so then a conversion from A to B will happen

interface GetLineInterface
{
    String getLine();
}

//
// (03) Adapters:
// --------------
class AdapterFromClassAToGetLine implements GetLineInterface
{
    AdaptedClassA classA;
    AdapterFromClassAToGetLine(AdaptedClassA classA){this.classA = classA;}

    @Override
    public String getLine(){return ""+classA.data;}
}
class AdapterFromClassBToGetLine implements GetLineInterface
{
    AdaptedClassB classB;
    AdapterFromClassBToGetLine(AdaptedClassB classB){this.classB = classB;}

    @Override
    public String getLine(){return ""+classB.data1+""+classB.data2;}
}

public class x08_Adapter
{
    public static void main(String[] args)
    {
        AdaptedClassA classA = new AdaptedClassA();
        AdaptedClassB classB = new AdaptedClassB();

        //
        // now classes are fully compatible:
        GetLineInterface[] classes = {
                new AdapterFromClassAToGetLine(classA),
                new AdapterFromClassBToGetLine(classB),
        };

        for(var one : classes)
        {
            System.out.println(one.getLine());
        }
    }
}
