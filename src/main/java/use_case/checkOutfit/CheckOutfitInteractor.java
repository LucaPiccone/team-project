package use_case.checkOutfit;

import java.util.Map;
import java.util.Objects;

public class CheckOutfitInteractor implements CheckOutfitInputBoundary {
    private final CheckOutfitOutputBoundary presenter;

    // Warmth score: bigger = warmer
    private static final Map<String, Integer> WARMTH = Map.of(
            "T_SHIRT", 1,
            "DRESS", 1,
            "SHORTS", 1,
            "HOODIE", 2,
            "JEANS", 2,
            "SWEATER", 3,
            "COAT", 4,
            "TRENCH", 4,
            "PUFFER", 5
    );

    public CheckOutfitInteractor(CheckOutfitOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(CheckOutfitInputData inputData) {
        final String outfitId = inputData.getOutfitId();
        final String weather = inputData.getWeatherMain();
        final double temp = parseTemp(inputData.getTemperature());

        if (!WARMTH.containsKey(outfitId) || Double.isNaN(temp)) {
            presenter.prepareFailView("Missing outfit or temperature.");
            return;
        }

        final int chosen = WARMTH.get(outfitId);
        final int recommended = recommendedWarmth(temp);

        final boolean wet = containsIgnoreCase(weather, "rain") || containsIgnoreCase(weather, "drizzle")
                || containsIgnoreCase(weather, "thunder") || containsIgnoreCase(weather, "snow");

        final StringBuilder msg = new StringBuilder();
        msg.append("Weather: ").append(weather).append("\n");
        msg.append("Temperature: ").append(String.format("%.1f", temp)).append("°C\n\n");

        // Weather-specific rule (simple)
        if (wet && (Objects.equals(outfitId, "SHORTS") || outfitId.equals("T_SHIRT") || outfitId.equals("DRESS"))) {
            msg.append("Not recommended for wet/snowy weather.\n");
            msg.append("Try: Coat / Trench / Puffer + Jeans.");
            presenter.prepareSuccessView(new CheckOutfitOutputData(msg.toString()));
            return;
        }

        final int diff = chosen - recommended;
        if (Math.abs(diff) <= 1) {
            msg.append("Good choice! This outfit matches the temperature.");
        }
        else if (diff < -1) {
            msg.append("Too light for this temperature.\n");
            msg.append("Suggestion: ").append(suggestWarmer(recommended));
        }
        else {
            msg.append(" Too warm for this temperature.\n");
            msg.append("Suggestion: ").append(suggestCooler(recommended));
        }

        presenter.prepareSuccessView(new CheckOutfitOutputData(msg.toString()));
    }

    private int recommendedWarmth(double t) {
        if (t <= 0) {
            return 5;
        }
        if (t <= 5) {
            return 4;
        }
        if (t <= 10) {
            return 4;
        }
        if (t <= 15) {
            return 3;
        }
        if (t <= 20) {
            return 2;
        }
        if (t <= 25) {
            return 2;
        }
        return 1;
    }

    private String suggestWarmer(int recommended) {
        if (recommended >= 5) {
            return "Puffer Jacket";
        }
        if (recommended == 4) {
            return "Coat / Trench Coat";
        }
        if (recommended == 3) {
            return "Sweater";
        }
        return "Hoodie / Jeans";
    }

    private String suggestCooler(int recommended) {
        if (recommended <= 1) {
            return "T-Shirt / Shorts";
        }
        if (recommended == 2) {
            return "Hoodie / Jeans";
        }
        return "Sweater";
    }

    private double parseTemp(String s) {
        if (s == null) {
            return Double.NaN;
        }
        final String str = s.trim();
        // extract first number (works for "9.99", "9.99°C", "Temp: 9.99")
        final StringBuilder num = new StringBuilder();
        boolean seenDigit = false;
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (c >= '0' && c <= '9' || c == '.' || c == '-' && !seenDigit) {
                num.append(c);
                if (c >= '0') {
                    seenDigit = true;
                }
            }
            else if (seenDigit) {
                break;
            }
        }
        try {
            return Double.parseDouble(num.toString());
        }
        catch (Exception e) {
            return Double.NaN;
        }
    }

    private boolean containsIgnoreCase(String a, String b) {
        return a.toLowerCase().contains(b.toLowerCase());
    }

    @Override
    public void goBack() {
        presenter.goBack();
    }
}
