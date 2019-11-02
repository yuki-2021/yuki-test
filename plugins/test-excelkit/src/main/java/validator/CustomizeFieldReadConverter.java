package validator;

import com.wuwenze.poi.convert.ReadConverter;
import com.wuwenze.poi.exception.ExcelKitReadConverterException;

public class CustomizeFieldReadConverter implements ReadConverter {

    /**
     * 读取单元格时，将值进行转换（此处示例为计算单元格字符串char的总和）
     */
    public Object convert(Object o) throws ExcelKitReadConverterException {
        String value = (String) o;

        String substring = value.substring(0, value.indexOf("_") - 1);
        return Integer.valueOf(substring);
    }
}
