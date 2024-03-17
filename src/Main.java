import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Entry> trainingDataSet = new ArrayList<>();
        List<Entry> testDataSet = new ArrayList<>();

        //trainingSet
        readTheFile(trainingDataSet,"wdbc.data");

        //testSet
        readTheFile(testDataSet,"wdbc.test.data");



        Classifier classifier = new Classifier(trainingDataSet, testDataSet,5);
        classifier.train();

        classifier.checkNew();
    }

    public static void readTheFile(List<Entry> dataSet, String filename)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
    {
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(",");
            float[] features = new float[parts.length-1];
            for (int i = 0; i < parts.length - 1; i++) {
                features[i] = Float.parseFloat(parts[i]);
            }
            String label = parts[parts.length-1];
            dataSet.add(new Entry(features,label));
        }


    } catch (IOException e) {
        throw new RuntimeException(e);
    }


    }
}
