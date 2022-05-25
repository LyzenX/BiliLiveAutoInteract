package cc.lseng.tool.blai;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LSeng
 * @date 2022/5/24 21:39
 */
public class Main {

    private static int date = -1;
    private static Thread senderThread;

    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                Calendar calendar = Calendar.getInstance();
                int currentDate = calendar.get(Calendar.DATE);
                if (currentDate != date) {
                    date = currentDate;
                    if (senderThread != null && senderThread.isAlive()) {
                        senderThread.stop();
                        senderThread = null;
                    }

                    senderThread = new Thread(() -> {
                        Logger.log("正在进行新一轮互动");
                        Logger.log("读取配置文件");
                        try {
                            if(!Configurations.reloadConfig()){
                                return;
                            }
                        } catch (IOException e) {
                            Logger.err(e);
                            return;
                        }


                        Logger.log("读取配置文件完成，正在发送互动数据包");
                        Logger.log("预计用时 "+Configurations.getEstimatedTime());

                        Logger.log("-----------------");
                        Logger.log("正在发送弹幕");
                        for (String roomId : Configurations.danmuAndLikeRooms) {
                            BiliLiveSender.sendDanmu(roomId, Configurations.danmu);
                            Logger.log("为" + roomId + "发送弹幕" + Configurations.danmu);
                            try {
                                Thread.sleep(3000L);
                            } catch (InterruptedException e) {
                                Logger.err(e);
                            }
                        }

                        if(Configurations.sendLike) {
                            Logger.log("\n");
                            Logger.log("-----------------");
                            Logger.log("正在发送点赞");
                            for (String roomId : Configurations.danmuAndLikeRooms) {
                                for (int i = 0; i < 3; i++) {
                                    BiliLiveSender.sendLike(roomId);
                                    Logger.log("为" + roomId + "发送点赞");
                                    try {
                                        Thread.sleep(11000L);
                                    } catch (InterruptedException e) {
                                        Logger.err(e);
                                    }
                                }
                            }
                        }

                        Logger.log("\n");
                        Logger.log("-----------------");
                        Logger.log("正在发送分享，该操作可能需要很长时间(每11分钟一次)");
                        for (String roomId : Configurations.shareRooms) {
                            for (int i = 0; i < 5; i++) {
                                BiliLiveSender.sendShare(roomId);
                                Logger.log("为" + roomId + "分享直播间");
                                try {
                                    Thread.sleep(1000L * 5L);
                                } catch (InterruptedException e) {
                                    Logger.err(e);
                                }
                            }
                        }
                        Logger.log("今日直播互动已完成");
                        Logger.log("-----------------");
                    });
                    senderThread.start();
                }
                try {
                    Thread.sleep(1000L * 60L);
                } catch (InterruptedException e) {
                    Logger.err(e);
                }
            }
        }).start();
    }

}
