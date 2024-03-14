import java.util.List;

public class Classifier {

    private List<Entry> dataSet;
    private int k;
    private int dimension;

    public Classifier(List<Entry> dataSet, int k) {
        this.dataSet = dataSet;
        this.k = k;
    }
}
