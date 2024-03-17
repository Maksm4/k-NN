import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Entry> trainingDataSet = new ArrayList<>();
        List<Entry> testDataSet = new ArrayList<>();

        //trainingSet
        readTheFile(trainingDataSet,args[0]);

        //testSet
        readTheFile(testDataSet,args[1]);



        Classifier classifier = new Classifier(trainingDataSet, testDataSet,args[2]);
        classifier.train();

        Scanner scanner = new Scanner(System.in);
        int action;
        do {
            System.out.println("enter your features to check the prediction");
            String read = scanner.next();
            String[] featuresAsString = read.split(",");
            float[] features = new float[featuresAsString.length];
            for (int i = 0; i < featuresAsString.length; i++) {
                features[i] = Float.parseFloat(featuresAsString[i]);
            }
            Entry entry = new Entry(features, null);
            classifier.checkNew(entry);
            System.out.println("do you want to exit? if yes, press 1");
            System.out.println("or any other number to keep working");
            action = scanner.nextInt();
        }while (action!=1);
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
