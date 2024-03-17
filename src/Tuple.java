public class Tuple {
    private double key;
    private String value;

    public Tuple(double key, String value) {
        this.key = key;
        this.value = value;
    }

    public static double getKey(Tuple tuple) {
        return tuple.key;
    }

    public String getValue() {
        return value;
    }

}
