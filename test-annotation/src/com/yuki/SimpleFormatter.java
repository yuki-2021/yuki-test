package com.yuki;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SimpleFormatter {

    public static String format(Object obj) throws IllegalAccessException {
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();
        //遍历Fields
        for(Field f : cls.getDeclaredFields()) {
            if(!f.isAccessible()) {
                f.setAccessible(true);
            }
            //getAnnotation
            Label label = f.getAnnotation(Label.class);
            //get name and value
            String name = label != null ? label.value() : f.getName();
            Object value = f.get(obj);
            //format
            if(value != null && f.getType() == Date.class) {
                value = formatDate(f, value);
            }
            sb.append(name + "ғ" + value + "\n");
        }
        return sb.toString();
    }

    private static Object formatDate(Field f, Object value) {
        Format format = f.getAnnotation(Format.class);
        if(format != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format.pattern());
            sdf.setTimeZone(TimeZone.getTimeZone(format.timezone()));
            return sdf.format(value);
        }
        return value;
    }


    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
     @interface Label {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Format {
        String pattern() default "yyyy-MM-dd HH:mm:ss";

        String timezone() default "GMT+8";
    }

    static class Student {
        @Label("姓名")
        String name;
        @Label("出生日期")
        @Format(pattern = "yyyy/MM/dd")
        Date born;
        @Label("分数")
        double score;

        public Student(String name, Date born, double score) {
            this.name = name;
            this.born = born;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBorn() {
            return born;
        }

        public void setBorn(Date born) {
            this.born = born;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }


    public static void main(String[] args) throws ParseException, IllegalAccessException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Student zhangsan = new Student("AAA", sdf.parse("1990-12-12"), 80.9d);
        System.out.println(SimpleFormatter.format(zhangsan));
    }
}
