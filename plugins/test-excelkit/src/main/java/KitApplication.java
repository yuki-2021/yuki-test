import com.wuwenze.poi.ExcelKit;
import db.Db;
import entity.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class KitApplication {

    public static void main(String[] args) throws FileNotFoundException {


        /*
         *  写入的时候  validator 和 maxLength没有任何作用
         *
         *
         */
        List<User> users = Db.get();

        users.get(0).setUsername("22222222");
        users.get(0).setPassword("22222222");

        OutputStream out = new FileOutputStream("D:/a.xlsx");

        ExcelKit.$Builder(User.class,new FileOutputStream(new File("D:/a.xlsx")))
                .writeXlsx(users,true);
    }
}
