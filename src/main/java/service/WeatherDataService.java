package service;

import exception.WeatherDataLoadException;
import model.Location;
import model.WeatherData;

public class WeatherDataService {

    private final boolean simulateLoadFail;

    public WeatherDataService(boolean simulateLoadFail) {
        this.simulateLoadFail = simulateLoadFail;
    }

    public WeatherData loadWeatherData(Location location) throws WeatherDataLoadException {
        if (simulateLoadFail) {
            throw new WeatherDataLoadException("网络错误，无法加载天气数据");
        }
        // 这里模拟一下天气内容，真实系统应从接口获取
        String summary = "location：" + location.getName() + "，晴 25°C，湿度 40%，风速 3m/s";
        return new WeatherData(location, summary);
    }
}
