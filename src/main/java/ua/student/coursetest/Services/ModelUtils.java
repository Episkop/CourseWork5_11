package ua.student.coursetest.Services;

import java.lang.reflect.Method;

public class ModelUtils {
    public static <T> T addMonthToTable(T source, T target) {
        try {
            for (String month : massive()) {
                Method getMethod = source.getClass().getMethod("get" + month);
                Method setMethod = target.getClass().getMethod("set" + month, getMethod.getReturnType());
                Object value = getMethod.invoke(source);
                setMethod.invoke(target, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }
    public static String[] massive (){
        return new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December", "Year"};
    }
}
