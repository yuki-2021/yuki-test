package validator;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;

public class CustomizeFieldWriteConverter implements WriteConverter {

    /**
     * 写文件时，将值进行转换（此处示例为将数值拼接为指定格式的字符串）
     */
    public String convert(Object o) throws ExcelKitWriteConverterException {
        return (o + "_值转换");
    }
}
