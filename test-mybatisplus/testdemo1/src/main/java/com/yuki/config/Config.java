package com.yuki.config;

        import com.baomidou.mybatisplus.core.parser.ISqlParser;
        import com.baomidou.mybatisplus.core.toolkit.Assert;
        import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
        import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
        import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
        import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
        import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
        import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
        import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
        import net.sf.jsqlparser.expression.LongValue;
        import net.sf.jsqlparser.expression.StringValue;
        import net.sf.jsqlparser.statement.delete.Delete;
        import org.apache.ibatis.reflection.MetaObject;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import net.sf.jsqlparser.expression.Expression;

        import java.util.*;

@Configuration
public class Config {



//    配置分页
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
//        // paginationInterceptor.setOverflow(false);
//        // 设置最大单页限制数量，默认 500 条，-1 不受限制
//        // paginationInterceptor.setLimit(500);
//        return paginationInterceptor;
//    }

    //配置分页
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);


        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 创建租户SQL解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        // 设置租户处理器
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId(boolean where) {
                // 设置当前租户ID，实际情况你可以从cookie、或者缓存中拿都行
                return new StringValue("李金");
            }

            @Override
            public String getTenantIdColumn() {
                // 对应数据库租户ID的列名
                return "name";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 是否需要需要过滤某一张表
              /*  List<String> tableNameList = Arrays.asList("sys_user");
                if (tableNameList.contains(tableName)){
                    return true;
                }*/
                return false;
            }
        });

        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);

        return  paginationInterceptor;
    }


    /*
     * 分页插件 动态表名
     *
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //分页器
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 动态表名SQL解析器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        //需要替换原始表名集合
        tableNameHandlerMap.put("user",new ITableNameHandler(){
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                // 自定义表名规则，或者从配置文件、request上下文中读取
                // 假设这里的用户表根据年份来进行分表操作
                Date date = new Date();
                String year = String.format("%tY", date);
                // 返回最后需要操作的表名，user_2019
                return "user_" + year;
            }
        });
        //设置hashMap
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        //添加解析器
        sqlParserList.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(sqlParserList);


        return paginationInterceptor;
    }



    /*
     * 攻击sql阻断解析器
     *
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        //创建sql阻断解析器
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        // 攻击 SQL 阻断解析器、加入解析链
//        sqlParserList.add(new BlockAttackSqlParser());
//        paginationInterceptor.setSqlParserList(sqlParserList);
////        // 攻击 SQL 阻断解析器、加入解析链
////        sqlParserList.add(new BlockAttackSqlParser() {
////            @Override
////            public void processDelete(Delete delete) {
////                // 如果你想自定义做点什么，可以重写父类方法像这样子
////                if ("user".equals(delete.getTable().getName())) {
////                    System.out.println(delete.getTable().getName());
////                    // 自定义跳过某个表，其他关联表可以调用 delete.getTables() 判断
//////                    throw ExceptionUtils.mpe(message, params);
////                }
////                super.processDelete(delete);
////            }
////        });
//        //添加sql阻断解析器
//        paginationInterceptor.setSqlParserList(sqlParserList);
//        return paginationInterceptor;
//    }
}
