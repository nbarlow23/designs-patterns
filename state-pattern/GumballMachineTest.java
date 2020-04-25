package state;

import junit.framework.*;

public class GumballMachineTest extends TestCase {

    public static Test suite() {
        return new TestSuite(GumballMachineTest.class);
    }

    private GumballMachine machine;

    public void setUp() {
        machine = new GumballMachine();
    }

    public void tearDown() {
        return;
    }

    public void test() {
        assertEquals(machine.getGumballCount(), 0);
        assertEquals(machine.getMoney(), 0.0f);

        machine.turnHandle();
        assertEquals(machine.getGumballCount(), 0);
        assertEquals(machine.getMoney(), 0.0f);

        machine.insertQuarter();
        machine.removeQuarter();
        machine.turnHandle();
        assertEquals(machine.getGumballCount(), 0);
        assertEquals(machine.getMoney(), 0.0f);

        machine.insertQuarter();
        machine.turnHandle();
        assertEquals(machine.getGumballCount(), 0);
        assertEquals(machine.getMoney(), 0.25f);

        machine.addGumballs(10);
        assertEquals(machine.getGumballCount(), 10);
        assertEquals(machine.getMoney(), 0.25f);

        for (int i=0; i < 10; ++i) {
            machine.insertQuarter();
            machine.turnHandle();
            assertEquals(machine.getGumballCount(), 9-i);
            assertEquals(machine.getMoney(), (i+2)*0.25f);
        }

        float money = machine.getMoney();
        machine.addGumballs(1);
        machine.turnHandle();
        assertEquals(machine.getGumballCount(), 1);
        assertEquals(machine.getMoney(), money);

        machine.insertQuarter();
        machine.turnHandle();
        assertEquals(machine.getGumballCount(), 0);
        assertEquals(machine.getMoney(), money + 0.25f);

        machine.insertQuarter();
        machine.removeQuarter();
        machine.insertQuarter();
        machine.turnHandle();
        assertEquals(machine.getGumballCount(), 0);
        assertEquals(machine.getMoney(), money + 0.50f);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}