package com.yuki;

/**
 * 异常链
 *
 * @author yuki
 */
public class ExceptionChain {

    public static void throw1() throws Exception {
        throw new Exception("throw1");
    }


    public static void throw2() throws Exception {
        try {
            throw1();
        } catch (Exception e) {
            throw new Exception("throw 2",e);
        }
    }


    public static void throw3() throws Exception {
        try {
            throw2();
        } catch (Exception e) {
            throw new Exception("throw 3",e);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            throw3();
        } catch (Exception e) {
            //输出stack长度
            System.out.println(e.getStackTrace().length);
            //输出throw2
            Throwable cause = e.getCause();
            System.out.println(cause.getMessage());
            //输出throw1
            Throwable cause1 = cause.getCause();
            System.out.println(cause1.getMessage());
        }
        test();
        System.out.println(ret);
    }

    public static int ret = 0;
    public static int test(){
        try {
            return ret;
        } finally {
            ret = 2;
        }

    }
}
