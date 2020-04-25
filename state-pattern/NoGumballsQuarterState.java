package state;

import static state.GumballMachine.QUARTER;

public class NoGumballsQuarterState extends State {

    NoGumballsQuarterState(GumballMachine context) {
        super(context);
    }

    @Override
    public void addGumballs(int count) {
        super.addGumballs(count);
        context.setState(new GumballsQuarterState(context));
    }

    @Override
    public void removeQuarter() {
        context.setState(new NoGumballsNoQuarterState(context));
    }

    @Override
    public void turnHandle() {
        float money = context.getMoney();
        context.setMoney(money + QUARTER);
        context.setState(new NoGumballsNoQuarterState(context));
    }
}
