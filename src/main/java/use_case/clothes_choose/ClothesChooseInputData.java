package use_case.clothes_choose;

public class ClothesChooseInputData {

    private final Double temperature;
    private final String clothes_choose_name;

    public ClothesChooseInputData(Double temp, String clothes_choose_name) {
        this.temperature = temp;
        this.clothes_choose_name = clothes_choose_name;
    }

    String getClothes_choose_name() {
        return clothes_choose_name;
    }

    String getTemperature() {return temperature.toString(); }

}
