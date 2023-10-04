package gersgarage2.GersGarage2.enumerates;

public enum Color {

    BLACK("black"),
    BLUE("blue"),
    BROWN("brown"),
    GRAY("gray"),
    GREEN("green"),
    ORANGE("orange"),
    PURPLE("purple"),
    RED("red"),
    YELLOW("yellow"),
    WHITE("white");

    private String displayColor;

    Color(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }
}
