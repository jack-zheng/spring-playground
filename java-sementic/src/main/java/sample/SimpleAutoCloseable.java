package sample;

public class SimpleAutoCloseable implements AutoCloseable {

    public SimpleAutoCloseable(String name) {
        System.out.println("Start to record time cost for scope: " + name);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Stop record, print time cost: xx ms");
    }
}
