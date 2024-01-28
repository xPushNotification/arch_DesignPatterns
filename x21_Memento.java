package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.Deque;
import java.util.LinkedList;

//
// Object to save / restore:
// -------------------------
class TextArea
{
    String text;

    // memento pattern:
    public static class Memento
    {
        String savedText;
        Memento(String textToSave){savedText = textToSave;}
    }
    Memento takeSnapshot(){return new Memento(text);}
    void restore(Memento memento){text = memento.savedText;}
}

//
// Client with the history:
// ------------------------
class Editor
{
    Deque<TextArea.Memento> history = new LinkedList<>();
    TextArea ta = new TextArea();

    void undo()
    {
        if(history.size() > 0) ta.restore(history.pop());
    }
}

public class x21_Memento
{
    public static void main(String[] args)
    {
        Editor ed = new Editor();
            ed.ta.text = "some";
            ed.history.add(ed.ta.takeSnapshot());
        System.out.println(ed.ta.text);
            ed.ta.text = "some 111";
            ed.history.add(ed.ta.takeSnapshot());
        System.out.println(ed.ta.text);

        System.out.println("--");
            ed.undo();
        System.out.println(ed.ta.text);
            ed.undo();
        System.out.println(ed.ta.text);
            ed.undo();
        System.out.println(ed.ta.text);
    }
}
