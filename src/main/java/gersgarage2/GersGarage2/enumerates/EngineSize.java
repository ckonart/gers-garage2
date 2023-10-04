package gersgarage2.GersGarage2.enumerates;

public enum EngineSize {

    Zero125CC("125cc"),
    Zero250CC("250cc"),
    Zero300CC("300cc"),
    Zero500CC("500cc"),
    Zero600CC("600cc"),
    Zero750CC("750cc"),
    Zero900CC("900cc"),
    OneThousandCC("1.0"),
    OneThousand200CC("1.2"),
    OneThousand300CC("1.3"),
    OneThousand400CC("1.4"),
    OneThousand500CC("1.5"),
    OneThousand800CC("1.8"),
    TwoThousandCC("2.0"),
    TwoThousand200CC("2.2"),
    TwoThousand500CC("2.5"),
    ThreeThousandCC("3.0"),
    FourThousandCC("4.0"),
    ;

    private String displayEngineSize;

    EngineSize(String displayEngineSize) {
        this.displayEngineSize = displayEngineSize;
    }

    public String getDisplayEngineSize() {
        return displayEngineSize;
    }

    public void setDisplayEngineSize(String displayEngineSize) {
        this.displayEngineSize = displayEngineSize;
    }
}
