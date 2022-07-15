package beans;

import org.springframework.beans.factory.annotation.Autowired;

public class FXNewsProvider {
    @Autowired
    private IFXNewsListener ifxNewsListener;
    @Autowired
    private IFXNewsPersister ifxNewsPersister;

    public FXNewsProvider(IFXNewsListener ifxNewsListener, IFXNewsPersister ifxNewsPersister) {
        this.ifxNewsListener = ifxNewsListener;
        this.ifxNewsPersister = ifxNewsPersister;
    }

    @Override
    public String toString() {
        return "beans.FXNewsProvider{" +
                "ifxNewsListener=" + ifxNewsListener +
                ", ifxNewsPersister=" + ifxNewsPersister +
                '}';
    }
}
