package bean;

public class EagerBean {
    private LazyBean lazyBean;

    public EagerBean(LazyBean lazyBean) {
        this.lazyBean = lazyBean;
        System.out.println("Eager bean initialized...");
    }
}
