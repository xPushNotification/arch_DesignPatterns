package gghype.x00_DesignAndAchitecture.Patterns;

abstract class Loader
{
    void beforeLoading(){}
    void afterLoading(){}
    void beforeDbConnection(){}
    void afterDbConnection(){}
    void load()
    {
        beforeLoading();
        System.out.println("Loading begin..");

        beforeDbConnection();
        System.out.println("Db connection..");
        afterDbConnection();

        System.out.println("Loading end..");
        afterLoading();
    }
}

class LoaderWithExtension extends Loader
{
    @Override
    void beforeLoading()
    {
        System.out.println("Before loading hooked..");
    }
}

public class x15_TemplateMethod
{
    public static void main(String[] args)
    {
        var loader = new LoaderWithExtension();
        loader.load();
    }
}
