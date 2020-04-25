package state;

public class GumballsNoQuarterState extends State {

    GumballsNoQuarterState(GumballMachine context) {
        super(context);
    }

    @Override
    public void insertQuarter() {
        context.setState(new GumballsQuarterState(context));
    }
}
