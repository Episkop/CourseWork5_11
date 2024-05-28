package ua.student.coursetest.Services;

import java.lang.reflect.Method;

public class ModelUtils {
    public static <T> T addMonthToTable(T source, T target) {
        try {
            for (String month : new String[] {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            }) {
                Method getMethod = source.getClass().getMethod("get" + month);
                Method setMethod = target.getClass().getMethod("set" + month, getMethod.getReturnType());
                Object value = getMethod.invoke(source);
                setMethod.invoke(target, value);
            }
            // Если у модели есть идентификатор, копируем его
//            Method getIdMethod = source.getClass().getMethod("getId");
//            Method setIdMethod = target.getClass().getMethod("setId", getIdMethod.getReturnType());
//            setIdMethod.invoke(target, getIdMethod.invoke(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }
}
