package cc.lseng.tool.blai;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LSeng
 * @date 2022/5/24 21:43
 */
public class BiliLiveSender {

    public static String sendDanmu(String roomId, String mes){
        Map<String, String> headers = new HashMap<>();
        headers.put("origin", "https://live.bilibili.com");
        headers.put("referer", "https://live.bilibili.com/blanc/1029?liteVersion=true");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

        Map<String, String> params = new HashMap<>();
        params.put("access_key", Configurations.accessKey);
        params.put("cid",roomId);
        params.put("msg", mes);
        params.put("rnd", String.valueOf(System.currentTimeMillis()).substring(0, 10));
        params.put("color", "16777215");
        params.put("fontsize", "25");

        return HttpUtils.sendPost("https://api.live.bilibili.com//xlive/app-room/v1/dM/sendmsg", headers, params);
    }

    public static String sendLike(String roomId){
        Map<String, String> headers = new HashMap<>();
        headers.put("origin", "https://live.bilibili.com");
        headers.put("referer", "https://live.bilibili.com/blanc/1029?liteVersion=true");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

        Map<String, String> params = new HashMap<>();
        params.put("access_key", Configurations.accessKey);
        params.put("roomid", roomId);

        return HttpUtils.sendPost("https://api.live.bilibili.com/xlive/web-ucenter/v1/interact/likeInteract", headers, params);
    }

    public static String sendShare(String roomId){
        Map<String, String> headers = new HashMap<>();
        headers.put("origin", "https://live.bilibili.com");
        headers.put("referer", "https://live.bilibili.com/blanc/1029?liteVersion=true");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

        Map<String, String> params = new HashMap<>();
        params.put("interact_type", "3");
        params.put("roomid", roomId);
        params.put("access_key", Configurations.accessKey);

        return HttpUtils.sendPost("https://api.live.bilibili.com/xlive/app-room/v1/index/TrigerInteract", headers, params);
    }

}
