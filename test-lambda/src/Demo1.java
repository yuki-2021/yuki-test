import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Demo1 {

    static class Student {
        String name;
        double score;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }
    }

    public static <E> List<E> filter(List<E> list, Predicate<E> pred) {
        return list.stream().filter(pred).collect(Collectors.toList());

    }

    public static void main(String[] args) {

        //lambda不能修改局部变量

        //lambda可以修改 字段变量

        //函数式接口
//        FileFilter filter = path -> path.getName().endsWith(".txt");
//        FilenameFilter fileNameFilter = (dir, name) -> name.endsWith(".txt");
//        Comparator<File> comparator = (f1, f2) ->
//                f1.getName().compareTo(f2.getName());
//        Runnable task = () -> System.out.println("hello world");

        //测试
        List<Student> students = Arrays.asList(new Student[]{
                new Student("zhangsan", 89d), new Student("lisi", 89d),
                new Student("wangwu", 98d)});
        students = filter(students, t -> t.getScore() > 90);
    }
}
