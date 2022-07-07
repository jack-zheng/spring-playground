package bean;

public class CircularA {
    private CircularB circularB;

    public CircularA() {
    }

    public CircularA(CircularB circularB) {
        this.circularB = circularB;
    }

    public CircularB getCircularB() {
        return circularB;
    }

    public void setCircularB(CircularB circularB) {
        this.circularB = circularB;
    }
}
