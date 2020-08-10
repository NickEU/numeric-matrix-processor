package processor;

class Util {
    static String doubleToString(Double num) {
        return num == (double) Math.round(num)
            ? String.format("%.0f", num) : String.valueOf(num);
    }

    static double parseNumber(String userInput) {
        return Double.parseDouble(userInput.contains(",")
            ? userInput.replaceAll(",", ".")
            : userInput);
    }
}
