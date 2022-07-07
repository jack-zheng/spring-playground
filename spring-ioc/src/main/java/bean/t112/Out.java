package bean.t112;

public class Out {
    private ParamBean paramBean;

    public Out(ParamBean paramBean) {
        this.paramBean = paramBean;
    }

    @Override
    public String toString() {
        return "Out{" +
                "paramBean=" + paramBean +
                '}';
    }
}
