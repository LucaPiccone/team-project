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
            throw new ShareAppNotFoundException("No available email app detected.Please install one and try again.");
        }
        // 真系统会调用系统共享，这里简单输出模拟
        System.out.println("Share weather data by email：" + data.getSummary());
    }

    public void shareToFacebook(WeatherData data) throws ShareAppNotFoundException {
        if (simulateNoShareApp) {
            throw new ShareAppNotFoundException("No Facebook app detected.Please install one and try again.");
        }
        System.out.println("Share to Facebook：" + data.getSummary());
    }
}
