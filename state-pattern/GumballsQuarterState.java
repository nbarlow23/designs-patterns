package state;

import static state.GumballMachine.QUARTER;

public class GumballsQuarterState extends State {
    GumballsQuarterState(GumballMachine context) {
        super(context);
    }

    @Override
    public void removeQuarter() {
        context.setState(new GumballsNoQuarterState(context));
    }

    @Override
    public void turnHandle() {
        int gumballCount = context.getGumballCount();
        float money = context.getMoney();
        context.setGumballCount(gumballCount - 1);
        context.setMoney(money + QUARTER);
        if (context.getGumballCount() > 0) {
            context.setState(new GumballsNoQuarterState(context));
        } else {
            context.setState(new NoGumballsNoQuarterState(context));
        }
    }
}
