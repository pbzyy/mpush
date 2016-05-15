package com.mpush.test.push;

import com.mpush.api.PushContent;
import com.mpush.api.PushContent.PushType;
import com.mpush.api.PushSender;
import com.mpush.push.PushClient;
import com.mpush.tools.Jsons;

import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by ohun on 2016/1/7.
 *
 * @author ohun@live.cn
 */
public class Main {
    public static void main(String[] args) throws Exception {
        PushClient client = new PushClient();
        client.start();
        Thread.sleep(1000);
        for (int i = 0; i < 100; i++) {
            PushContent content = PushContent.build(PushType.MESSAGE, "this a first push." + i);
            content.setMsgId("msgId_" + (i % 2));

            client.send(Jsons.toJson(content), Arrays.asList("doctor43test", "huang2", "huang"), new PushSender.Callback() {
                @Override
                public void onSuccess(String userId) {
                    System.err.println("push onSuccess userId=" + userId);
                }

                @Override
                public void onFailure(String userId) {
                    System.err.println("push onFailure userId=" + userId);
                }

                @Override
                public void onOffline(String userId) {
                    System.err.println("push onOffline userId=" + userId);
                }

                @Override
                public void onTimeout(String userId) {
                    System.err.println("push onTimeout userId=" + userId);
                }
            });
            Thread.sleep(10000);
        }
        LockSupport.park();
    }

}