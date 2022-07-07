package bean.t112;

public class T112Service {
    private ParamBean paramBean;

    public void setParamBean(ParamBean paramBean) {
        this.paramBean = paramBean;
    }

    public ParamBean getParamBean() {
        return paramBean;
    }

    @Override
    public String toString() {
        return "T112Service{" +
                "paramBean=" + paramBean +
                '}';
    }
}
