package beans;

import java.util.Date;

public class DateFoo {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateFoo{" +
                "date=" + date +
                '}';
    }
}
