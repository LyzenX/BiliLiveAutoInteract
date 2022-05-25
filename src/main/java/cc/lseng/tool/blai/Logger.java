package cc.lseng.tool.blai;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LSeng
 * @date 2022/5/24 22:46
 */
public class Logger {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void log(String info){
        String mes = timeFormat.format(new Date())+" "+info;
        System.out.println(mes);
        if(Configurations.saveLog) {
            Configurations.writeIntoFile("./logs/" + dateFormat.format(new Date()) + ".log", mes + "\n");
        }
    }

    public static void err(Throwable e){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        e.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        String info = stringWriter.toString();
        String mes = timeFormat.format(new Date())+" "+info;
        System.out.println(mes);
        if(Configurations.saveLog) {
            Configurations.writeIntoFile("./logs/" + dateFormat.format(new Date()) + ".log", mes + "\n");
        }
    }

}
