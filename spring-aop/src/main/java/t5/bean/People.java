package t5.bean;

public class People {
    public void doWork() {
        System.out.println("working.....");
    }

    public void loopCall(String name, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("loop call: " + name + "," + i + " times.");
        }
    }
}

