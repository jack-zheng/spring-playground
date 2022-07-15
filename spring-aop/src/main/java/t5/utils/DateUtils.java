package t5.utils;

import org.aspectj.lang.ProceedingJoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public String parseDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        return sdf.format(new Date());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before join point...");
        Object ret = pjp.proceed();
        System.out.println("after join point...");
        return ret;
    }

    public void specifyArgsCall(String name, int times) {
        System.out.println("accept name: " + name + ", times: " + times);
    }
}