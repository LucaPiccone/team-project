package service;

import exception.ShareAppNotFoundException;
import model.WeatherData;

public class ShareService {

    private final boolean simulateNoShareApp;

    public ShareService(boolean simulateNoShareApp) {
        this.simulateNoShareApp = simulateNoShareApp;
    }

    public void shareByEmail(WeatherData data) throws ShareAppNotFoundException {
        if (simulateNoShareApp) {
            throw new ShareAppNotFoundException("未检测到可用邮箱应用，请安装后重试。");
        }
        // 真系统会调用系统共享，这里简单输出模拟
        System.out.println("通过邮件共享天气数据：" + data.getSummary());
    }

    public void shareToFacebook(WeatherData data) throws ShareAppNotFoundException {
        if (simulateNoShareApp) {
            throw new ShareAppNotFoundException("未检测到 Facebook 应用，请安装后重试。");
        }
        System.out.println("分享到 Facebook：" + data.getSummary());
    }
}
