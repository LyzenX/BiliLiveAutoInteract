package cc.lseng.tool.blai;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author LSeng
 * @date 2022/5/24 21:49
 */
public class Configurations {

    public static String accessKey = "accessKey";
    public static String danmu = "(=・ω・=)";
    public static boolean sendLike = true;
    public static boolean saveLog = true;
    public static List<String> danmuAndLikeRooms = new ArrayList<>();
    public static List<String> shareRooms = new ArrayList<>();

    public static boolean reloadConfig() throws IOException {
        String fileName = "./config.json";

        File f = new File(fileName);
        System.out.println("正在检查配置文件："+f.getAbsolutePath());
        if(!f.exists()){
            try {
                f.createNewFile();
                writeIntoFile(fileName,
                        new JSONObject(
                                "{\"accessKey\":\"accessKey\"," +
                                        "\"sendLike\":true," +
                                        "\"saveLog\":true," +
                                        "\"danmu\":\"(=^_^=)\"," +
                                        "\"danmuAndLikeRooms\":[\"621810\",\"1319456\"]," +
                                        "\"shareRooms\":[\"621810\",\"1319456\"]" +
                                        "}").toString(4));

                Logger.log("未找到配置文件，已生成新配置文件，请修改config.json后再重新运行");
                return false;
            } catch (IOException e) {
                Logger.err(e);
                return false;
            }
        }

        Stream<String> lines = Files.lines(Paths.get(fileName));

        StringBuilder sb = new StringBuilder();
        lines.forEach(str -> {
            sb.append(str);
        });

        JSONObject json = new JSONObject(sb.toString());

        danmu = json.getString("danmu");
        accessKey = json.getString("accessKey");
        sendLike = json.getBoolean("sendLike");
        saveLog = json.getBoolean("saveLog");

        danmuAndLikeRooms.clear();
        danmuAndLikeRooms = new ArrayList<>();
        for(Object obj : json.getJSONArray("danmuAndLikeRooms")){
            if(obj instanceof String){
                danmuAndLikeRooms.add((String) obj);
            } else if (obj instanceof Integer){
                danmuAndLikeRooms.add(String.valueOf(obj));
            }
        }

        shareRooms.clear();
        shareRooms = new ArrayList<>();
        for(Object obj : json.getJSONArray("shareRooms")){
            if(obj instanceof String){
                shareRooms.add((String) obj);
            } else if (obj instanceof Integer){
                shareRooms.add(String.valueOf(obj));
            }
        }

        if(shareRooms.size() > 26){
            Logger.log("警告：需要分享的直播间超过26个，超出部分可能无法成功分享");
        }

        return true;
    }

    public static String getEstimatedTime(){
        long t = 0;
        t += 4L * danmuAndLikeRooms.size();
        if(sendLike) {
            t += 12L * danmuAndLikeRooms.size() * 3L;
        }
        t += 6L * shareRooms.size() * 5L;

        if(t > 3600){
            int hour = (int)(t / 3600);
            int minute = (int)((t % 3600) / 60);
            return hour + "时" + minute + "分";
        } else {
            return (t / 60L) + "分";
        }
    }

    public static void writeIntoFile(String filePath, String info){
        String filePath2 = filePath.replace("\\", "/");
        int index = filePath2.lastIndexOf("/");
        String dir = filePath2.substring(0, index);
        File fileDir = new File(dir);
        fileDir.mkdirs();
        File file = new File(filePath);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            try(FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(info);
                fileWriter.flush();
            } catch (IOException e){
                Logger.err(e);
            }
        } catch (IOException e) {
            Logger.err(e);
        }
    }

}
