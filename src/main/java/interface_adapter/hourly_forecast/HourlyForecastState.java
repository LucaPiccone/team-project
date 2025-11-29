package interface_adapter.hourly_forecast;

public class HourlyForecastState {
    private String setPopUpMessage = "";
    private String cityName = "";

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSetPopUpMessage() {
        return setPopUpMessage;
    }

    public void setSetPopUpMessage(String setPopUpMessage) {
        this.setPopUpMessage = setPopUpMessage;
    }
}
