package state;

public class GumballMachine {
    public static final float QUARTER = .25f;

    private int gumballCount;
    private float money;
    private State state;

    public GumballMachine() {
        gumballCount = 0;
        money = 0.0f;
        state = new NoGumballsNoQuarterState(this);
    }

    public int getGumballCount() {
        return gumballCount;
    }

    public float getMoney() {
        return money;
    }

    public void setGumballCount(int gumballCount) {
        this.gumballCount = gumballCount;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addGumballs(int count) {
        state.addGumballs(count);
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void removeQuarter() {
        state.removeQuarter();
    }

    public void turnHandle() {
        state.turnHandle();
    }

}