package entity;

import com.wuwenze.poi.config.Options;

public class SexOptions implements Options {
    public String[] get() {
        return new String[]{"男","女","未知"};
    }
}
