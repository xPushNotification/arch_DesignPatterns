package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.HashMap;

//
// (01) Interfaces:
// ----------------
interface ActAsACommandAbility
{
    void doExecute();
    void unExecute();           // <- actually it's look like this is redundant
}
interface ActAsACommandReceiverAbility
{
    void doAction(String actionName);
}

//
// (02) Abstract classes:
// ----------------------
abstract class InvokerOfCommands
{
    HashMap<String /* commandName */, ActAsACommandAbility /* command */> commands = new HashMap<>();
    HashMap<String /* commandName */, Boolean /* executed/not */> commandStates = new HashMap<>();
    void addCommand(String commandName, ActAsACommandAbility command)
    {
        commands.put(commandName,command);
        commandStates.put(commandName, false);
    }
    void doCommand(String commandName)
    {
        if(commands.containsKey(commandName) && commandStates.containsKey(commandName))
        {
            if(!commandStates.get(commandName))
            {
                commands.get(commandName).doExecute();
                commandStates.replace(commandName,true);
            }
            else
            {
                commands.get(commandName).unExecute();
                commandStates.replace(commandName,false);
            }
        }
    }
}
//
// (03) Receiver "TableLamp" and its commands:
// -------------------------------------------
// (03.a) TableLamp:
class TableLamp implements ActAsACommandReceiverAbility
{
    void turnLightOn(){System.out.println("TableLamp: light is turned on");}
    void turnLightOff(){System.out.println("TableLamp: light is turned off");}

    @Override
    public void doAction(String actionName)
    {
        if(actionName.equals("turnLightOn")) turnLightOn();
        if(actionName.equals("turnLightOff")) turnLightOff();
    }
}
// (03.b) Commands:
class CommandTableLamp_LightOnOff implements ActAsACommandAbility
{
    ActAsACommandReceiverAbility receiver;
    CommandTableLamp_LightOnOff(ActAsACommandReceiverAbility receiver)
    {
        this.receiver = receiver;
    }
    @Override
    public void doExecute(){receiver.doAction("turnLightOn");}

    @Override
    public void unExecute(){receiver.doAction("turnLightOff");}
}
//
// (04) Receiver "TvSet" and its commands:
// ---------------------------------------
// (04.a) TvSet:
class TvSet implements ActAsACommandReceiverAbility
{
    void tvSetOn(){System.out.println("TvSet: turned on");}
    void tvSetOff(){System.out.println("TvSet: turned off");}
    void tvSetNextChannel(){System.out.println("TvSet: next channel");}
    void tvSetPrevChannel(){System.out.println("TvSet: prev channel");}

    @Override
    public void doAction(String actionName)
    {
        if(actionName.equals("turnTvSetOn")) tvSetOn();
        if(actionName.equals("turnTvSetOff")) tvSetOff();
        if(actionName.equals("turnNextChannel")) tvSetNextChannel();
        if(actionName.equals("turnPrevChannel")) tvSetPrevChannel();
    }
}
// (04.b) Commands:
class CommandTvSet_TurnOnOff implements ActAsACommandAbility
{
    ActAsACommandReceiverAbility receiver;
    CommandTvSet_TurnOnOff(ActAsACommandReceiverAbility receiver)
    {
        this.receiver = receiver;
    }
    @Override
    public void doExecute(){receiver.doAction("turnTvSetOn");}

    @Override
    public void unExecute(){receiver.doAction("turnTvSetOff");}
}
class CommandTvSet_NextChannel implements ActAsACommandAbility
{
    ActAsACommandReceiverAbility receiver;
    CommandTvSet_NextChannel(ActAsACommandReceiverAbility receiver)
    {
        this.receiver = receiver;
    }
    @Override
    public void doExecute(){receiver.doAction("turnNextChannel");}

    @Override
    public void unExecute(){receiver.doAction("turnNextChannel");}
}
class CommandTvSet_PrevChannel implements ActAsACommandAbility
{
    ActAsACommandReceiverAbility receiver;
    CommandTvSet_PrevChannel(ActAsACommandReceiverAbility receiver)
    {
        this.receiver = receiver;
    }
    @Override
    public void doExecute(){receiver.doAction("turnPrevChannel");}

    @Override
    public void unExecute(){receiver.doAction("turnPrevChannel");}
}

//
// (03) Invokers:
// --------------
class TvRemoteController extends InvokerOfCommands{}

public class x07_Command
{
    public static void main(String[] args)
    {
        // create an object:
        TableLamp tableLamp = new TableLamp();
        // create associated commands:
            CommandTableLamp_LightOnOff tableLamp_lightOnOff
                    = new CommandTableLamp_LightOnOff(tableLamp);
        TvSet tvSet = new TvSet();
            CommandTvSet_TurnOnOff tvSet_turnOnOff
                    = new CommandTvSet_TurnOnOff(tvSet);
            CommandTvSet_NextChannel tvSet_nextChannel
                    = new CommandTvSet_NextChannel(tvSet);
            CommandTvSet_PrevChannel tvSet_prevChannel
                    = new CommandTvSet_PrevChannel(tvSet);

        //
        // now we can bind commands to the controller:
        TvRemoteController remoteController = new TvRemoteController();
        remoteController.addCommand("blue button", tableLamp_lightOnOff);
        remoteController.addCommand("red button",tvSet_turnOnOff);
        remoteController.addCommand("+ button", tvSet_nextChannel);
        remoteController.addCommand("- button", tvSet_prevChannel);

        //
        // control sequence:
        remoteController.doCommand("blue button");
        remoteController.doCommand("blue button");

        remoteController.doCommand("red button"); // tvSet on
        remoteController.doCommand("+ button");     // next channel
        remoteController.doCommand("+ button");     // next channel
        remoteController.doCommand("- button");     // prev channel
        remoteController.doCommand("red button"); // tvSet off
    }
}
