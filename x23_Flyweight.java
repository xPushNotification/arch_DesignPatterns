package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.ArrayList;

//
// Unoptimized object:
// -------------------
class HeavyBook
{
    String one;
    String two;
    String repetitiveOne;
    String repetitiveTwo;
    HeavyBook(String one, String two, String repOne, String repTwo)
    {this.one = one; this.two = two; this.repetitiveOne = repOne;
    this.repetitiveTwo = repTwo;}
    String getOne(){return one;}
    String getTwo(){return two;}
    String getRepetitiveOne(){return repetitiveOne;}
    String getRepetitiveTwo(){return repetitiveTwo;}
}
//
// Optimized:
// ----------
class RepetitiveFields
{
    String repetitiveOne;
    String repetitiveTwo;
    RepetitiveFields(String repOne, String repTwo)
    {this.repetitiveOne = repOne; this.repetitiveTwo = repTwo;}
}
class LightBook
{
    String one;
    String two;
    RepetitiveFields repetitiveFields;
    LightBook(String one, String two, RepetitiveFields repetitiveFields)
    {this.one = one;this.two = two;this.repetitiveFields = repetitiveFields;}
    String getOne(){return one;}
    String getTwo(){return two;}
    String getRepetitiveOne(){return repetitiveFields.repetitiveOne;}
    String getRepetitiveTwo(){return repetitiveFields.repetitiveTwo;}
}

//
// Caching Factory:
// ----------------
class LightBookFactory
{
    ArrayList<RepetitiveFields> existingRepetitiveFields = new ArrayList<>();
    LightBook createLightBook(String one, String two, String repOne, String repTwo)
    {
        RepetitiveFields rbf = null;
        for(var exemplar : existingRepetitiveFields)
        {
            if(exemplar.repetitiveOne.equals(repOne) &&
               exemplar.repetitiveTwo.equals(repTwo))
            {
               rbf = exemplar;
            }
        }
        if(rbf == null)
        {
            rbf = new RepetitiveFields(repOne,repTwo);
            existingRepetitiveFields.add(rbf);
        }
        return new LightBook(one,two,rbf);
    }
}

public class x23_Flyweight
{
    public static void main(String[] args)
    {
        var factory = new LightBookFactory();
        var lightBook01 = factory.createLightBook("one","two","three","four");
        System.out.println(factory.existingRepetitiveFields.size());    // 1
        var lightBook02 = factory.createLightBook("one","two","three","four");
        System.out.println(factory.existingRepetitiveFields.size());    // 1
        var lightBook03 = factory.createLightBook("one","two","three3","four4");
        System.out.println(factory.existingRepetitiveFields.size());    // 2

        System.out.println(lightBook01.getRepetitiveOne());
        System.out.println(lightBook02.getRepetitiveOne());
        System.out.println(lightBook03.getRepetitiveOne());

        System.out.println(lightBook01.repetitiveFields.equals(lightBook02.repetitiveFields));  // same object
        System.out.println(lightBook01.repetitiveFields.equals(lightBook03.repetitiveFields));  // different object
    }
}
