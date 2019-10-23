package com.yuki;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SimpleMapperDemo {

    static class Student {
        String name;
        int age;
        Double score;


        public Student(String name, int age, Double score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public Student() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Student zhangsan = new Student("AA", 18, 89d);
        String str = SimpleMapper.toString(zhangsan);
        System.out.println(str);
        Student zhangsan2 = (Student) SimpleMapper.fromString(str);
        System.out.println(zhangsan2);
    }

    static class SimpleMapper{
        public static String toString(Object obj) {
            try {
                Class<?> cls = obj.getClass();
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getName() + "\n");
                for(Field f : cls.getDeclaredFields()) {
                    if(!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    sb.append(f.getName() + "=" + f.get(obj).toString() + "\n");
                }
                return sb.toString();
            } catch(IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


        public static Object fromString(String str) {
            try {
                String[] lines = str.split("\n");
                if(lines.length < 1) {
                    throw new IllegalArgumentException(str);
                }
                Class<?> cls = Class.forName(lines[0]);
                Object obj = cls.newInstance();
                if(lines.length > 1) {
                    for(int i = 1; i < lines.length; i++) {
                        String[] fv = lines[i].split("=");
                        if(fv.length != 2) {
                            throw new IllegalArgumentException(lines[i]);
                        }
                        Field f = cls.getDeclaredField(fv[0]);
                        if(!f.isAccessible()){
                            f.setAccessible(true);
                        }
                        setFieldValue(f, obj, fv[1]);
                    }
                }
                return obj;
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }

         private static void setFieldValue(Field f, Object obj, String value) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
            Class<?> type = f.getType();
            if(type == int.class) {
                f.setInt(obj, Integer.parseInt(value));
            } else if(type == byte.class) {
                f.setByte(obj, Byte.parseByte(value));
            } else if(type == short.class) {
                f.setShort(obj, Short.parseShort(value));
            } else if(type == long.class) {
                f.setLong(obj, Long.parseLong(value));
            } else if(type == float.class) {
                f.setFloat(obj, Float.parseFloat(value));
            } else if(type == double.class) {
                f.setDouble(obj, Double.parseDouble(value));
            } else if(type == char.class) {
                f.setChar(obj, value.charAt(0));
            } else if(type == boolean.class) {
                f.setBoolean(obj, Boolean.parseBoolean(value));
            } else if(type == String.class) {
                f.set(obj, value);
            } else {
                Constructor<?> ctor = type.getConstructor(
                        new Class[] { String.class });
                f.set(obj, ctor.newInstance(value));
            }
        }
    }
}
