package state;

public abstract class State {
    protected GumballMachine context;

    State(GumballMachine context) {
        this.context = context;
    }

    public void addGumballs(int count) {
        int prevCount = context.getGumballCount();
        context.setGumballCount(prevCount + count);
    }

    public void insertQuarter() {

    }

    public void removeQuarter() {

    }

    public void turnHandle() {

    }

}
