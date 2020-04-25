package state;

public class NoGumballsNoQuarterState extends State {
    NoGumballsNoQuarterState(GumballMachine context) {
        super(context);
    }

    @Override
    public void addGumballs(int count) {
        super.addGumballs(count);
        context.setState(new GumballsNoQuarterState(context));
    }

    @Override
    public void insertQuarter() {
        context.setState(new NoGumballsQuarterState(context));
    }
}
