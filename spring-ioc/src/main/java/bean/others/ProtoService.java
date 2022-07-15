package bean.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProtoService {
    private ProtoTestBean protoTestBean;

    @Autowired
    public void setProtoTestBean(ProtoTestBean protoTestBean) {
        this.protoTestBean = protoTestBean;
    }

    public void invokeBean() {
        System.out.println("print bean info: " + protoTestBean);
    }

    @Override
    public String toString() {
        return "ProtoService{" +
                "protoTestBean=" + protoTestBean +
                '}';
    }
}
