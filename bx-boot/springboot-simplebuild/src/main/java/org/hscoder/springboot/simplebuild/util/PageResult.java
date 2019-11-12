package org.hscoder.springboot.simplebuild.util;

import java.util.List;

public class PageResult<T> {

    private long totalCount;
    private List<T> list;

    public long getTotalCount() {
        return totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public boolean isEmpty() {
        return list == null || list.isEmpty();

    }

    public static <T> PageResult<T> empty() {
        return new PageResult<T>();
    }

    public static <T> PageResult<T> of(long totalCount, List<T> list) {
        PageResult<T> result = new PageResult<T>();
        result.totalCount = totalCount;
        result.list = list;
        return result;
    }

}
