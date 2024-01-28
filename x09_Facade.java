package gghype.x00_DesignAndAchitecture.Patterns;

//
// some hard to explain and understand classes:
//
class ContentLibraryGetFromInteger
{
    int generateInt(){return (int)(Math.random()*100);}
    int returnInt(){return generateInt();}
}
class ContentLibraryGetFromString
{
    String returnString(){return ""+(new ContentLibraryGetFromInteger()).generateInt();}
}
//
// facade for the listed classes (offer to use all functionality via one object):
//
class ContentLibraryFacade
{
    int returnInt(){return (new ContentLibraryGetFromInteger()).returnInt();}
    String returnString(){return (new ContentLibraryGetFromString()).returnString();}
}

public class x09_Facade
{
    public static void main(String[] args)
    {
        ContentLibraryFacade clf = new ContentLibraryFacade();
        System.out.println(clf.returnInt());          // <- use via the facade
        System.out.println(clf.returnString());

        //                 <- use via direct access:
        System.out.println(new ContentLibraryGetFromString().returnString());
    }
}
