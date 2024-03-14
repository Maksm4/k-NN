import java.util.List;

public class Entry {
    private float[] features;
    private String label;


    public Entry(float[] features, String label) {
        this.features = features;
        this.label = label;
    }

    public float[] getFeatures() {
        return features;
    }

    public String getLabel() {
        return label;
    }
}
