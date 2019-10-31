package entity;

import com.wuwenze.poi.config.Options;

/**
 *
 * 导入模板下拉框数据源
 *
 */
public class UserGroupNameOptions implements Options {

    public String[] get() {
        return new String[]{"管理组", "普通会员组", "游客"};
    }
}