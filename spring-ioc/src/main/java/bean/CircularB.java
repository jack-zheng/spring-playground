package bean;

public class CircularB {
    private CircularA circularA;

    public CircularB() {
    }

    public CircularB(CircularA circularA) {
        this.circularA = circularA;
    }

    public CircularA getCircularA() {
        return circularA;
    }

    public void setCircularA(CircularA circularA) {
        this.circularA = circularA;
    }
}
