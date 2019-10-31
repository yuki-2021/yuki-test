import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import entity.User;

import java.io.File;
import java.util.List;

public class KitInport {

    public static void main(String[] args) {

        /**
         * 在读取的时候  会对于validator进行验证
         *
         * maxlength + commnet
         * validator
         *
         * */

        ExcelKit.$Import(User.class).readXlsx(new File("D:/a.xlsx"), new ExcelReadHandler<User>() {
            //单行读取成功
            public void onSuccess(int i, int i1, User user) {
                System.out.println(user.toString());
            }

            //单行读取失败 输出原因
            public void onError(int i, int i1, List<ExcelErrorField> list) {
                System.out.println(list.toString());
            }
        });
    }
}
