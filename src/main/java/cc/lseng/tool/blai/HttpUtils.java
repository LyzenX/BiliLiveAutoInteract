package cc.lseng.tool.blai;

import org.apache.commons.logging.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author LSeng
 * @date 2022/5/24 21:41
 */
public class HttpUtils {

    public static String sendPost(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuilder paramsSb = new StringBuilder();
        for(Map.Entry<String, String> entry : params.entrySet()){
            paramsSb.append(entry.getKey()+"="+entry.getValue());
            paramsSb.append("&");
        }
        int length = paramsSb.length();

        return sendPost(url, headers, paramsSb.substring(0, length-1));
    }

    public static String sendPost(String url, Map<String, String> headers, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            for(Map.Entry<String, String> entry : headers.entrySet()){
                conn.addRequestProperty(entry.getKey(), entry.getValue());
            }

            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());
            out.print(params);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            Logger.err(e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                Logger.err(ex);
            }
        }
        return result;
    }

}
