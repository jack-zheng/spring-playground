package bean;

public class LazyBean {
    private String name;

    public LazyBean(String name) {
        this.name = name;
        System.out.println("lazy bean: " + name + " created...");
    }
}
