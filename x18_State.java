package gghype.x00_DesignAndAchitecture.Patterns;

import java.util.HashMap;
import java.util.Map;

//
// State Declaration/Identification:
// ---------------------------------
interface StatePowerOnOff
{
    void pushPowerButton();
    void pushHomeButton();
}
enum PowerMode                  // <- axis/row of table of the object states
{
    POWER_IS_ON, POWER_IS_OFF
}

//
// States:
// -------
class PowerIsOnState implements StatePowerOnOff
{
    @Override
    public void pushPowerButton()
    {System.out.println("power is pressed: when power is on");}
    @Override
    public void pushHomeButton()
    {System.out.println("home is pressed: when power is on");}
}
class PowerIsOffState implements StatePowerOnOff
{
    @Override
    public void pushPowerButton()
    {System.out.println("power is pressed: when power is off");}
    @Override
    public void pushHomeButton()
    {System.out.println("home is pressed: when power is off");}
}

//
// User of States:
// ---------------
class PhoneWithStates
{
    //
    // <- state machine with...
    PowerMode powerMode = PowerMode.POWER_IS_OFF;
    HashMap<PowerMode, StatePowerOnOff> statesPowerOnOff = new HashMap<>(
            Map.of(
                PowerMode.POWER_IS_ON,new PowerIsOnState(),
                PowerMode.POWER_IS_OFF,new PowerIsOffState()
            )
    );
    // .. the Strategy:
    StatePowerOnOff currentPowerOnOffState = statesPowerOnOff.get(powerMode);

    void switchMode(PowerMode newMode)
    {
        powerMode = newMode;
        currentPowerOnOffState = statesPowerOnOff.get(powerMode);
    }

    void doPushPowerButton()
    {
        currentPowerOnOffState.pushPowerButton();

        // change the state:
        if(powerMode == PowerMode.POWER_IS_ON) switchMode(PowerMode.POWER_IS_OFF);
        else switchMode(PowerMode.POWER_IS_ON);
    }

    void doPushHomeButton()
    {
        currentPowerOnOffState.pushHomeButton();
    }
}

public class x18_State
{
    public static void main(String[] args)
    {
        PhoneWithStates phone = new PhoneWithStates();

        phone.doPushHomeButton();           // push when phone is off
        phone.doPushPowerButton();
        phone.doPushHomeButton();           // push when phone is on
    }
}
