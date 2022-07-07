package bean.t19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class Auto01 {
    private Auto02 auto02;
    private Auto03 auto03;
    @Autowired(required = false)
    private Auto04 auto04;
    private Optional<OptionalBean> optionalBean;
    private NullableBean nullableBean;
    private QualifiersBean qualifiersBean;

    @Autowired
    public Auto01(Auto02 auto02) {
        this.auto02 = auto02;
    }

    @Autowired
    public void aa(Auto03 auto03) {
        this.auto03 = auto03;
    }

    @Autowired
    public void setOptionalBean(Optional<OptionalBean> optionalBean) {
        this.optionalBean = optionalBean;
    }

    @Autowired
    public void setNullableBean(@Nullable NullableBean nullableBean) {
        this.nullableBean = nullableBean;
    }

    @Autowired
    //@Qualifier("qualifiersBean")
    public void setQualifiersBean(@Qualifier("qual01") QualifiersBean qualifiersBean) {
        this.qualifiersBean = qualifiersBean;
    }

    @Override
    public String toString() {
        return "Auto01{" +
                "auto02=" + auto02 +
                ", auto03=" + auto03 +
                ", auto04=" + auto04 +
                ", optionalBean=" + optionalBean +
                ", nullableBean=" + nullableBean +
                ", qualifiersBean=" + qualifiersBean +
                '}';
    }
}
