import java.util.*;

public class Classifier {

    private List<Entry> trainingDataSet;
    private List<Entry> testDataSet;

    private int k;
    private int dimension;
    double accuracyCount = 0;

    public Classifier(List<Entry> trainingDataSet, List<Entry> testDataSet, int k) {
        this.trainingDataSet = trainingDataSet;
        this.testDataSet = testDataSet;
        this.dimension = trainingDataSet.get(0).getFeatures().length;
        this.k = Math.min(k, trainingDataSet.size());
    }

    public double train()
    {
        for (int i = 0; i < testDataSet.size(); i++) {
            List<Tuple> distance_label = new ArrayList<>();
            for (int j = 0; j < trainingDataSet.size(); j++) {
                double distance = 0;
                for (int l = 0; l < dimension; l++) {
                    distance += Math.abs(testDataSet.get(i).getFeatures()[l] - trainingDataSet.get(j).getFeatures()[l]);
                }
                distance_label.add(new Tuple(Math.sqrt(distance),trainingDataSet.get(j).getLabel()));
            }

            distance_label.sort(Comparator.comparing(Tuple::getKey));


            Map<String,Integer> outputs = new HashMap<>();
            for (int j = 0; j < k; j++) {
                String key = distance_label.get(j).getValue();
                    outputs.put(key, outputs.getOrDefault(key,0)+1);
            }

            int biggestNumberOfOccurence = Integer.MIN_VALUE;
            String prediction = "";
            for (Map.Entry<String,Integer> entry : outputs.entrySet()){
                    if (entry.getValue() > biggestNumberOfOccurence){
                        biggestNumberOfOccurence = entry.getValue();
                        prediction = entry.getKey();
                    }
            }
            if (prediction.equals(testDataSet.get(i).getLabel()))
                    accuracyCount++;
        }

        System.out.println((accuracyCount/ testDataSet.size()));
        return (accuracyCount/ testDataSet.size());
    }


    public void checkNew(Entry toTest)
    {
        List<Tuple> distance_label = new ArrayList<>();
        for (int j = 0; j < trainingDataSet.size(); j++) {
            double distance = 0;
            for (int l = 0; l < dimension; l++) {
                distance += Math.abs(toTest.getFeatures()[l] - trainingDataSet.get(j).getFeatures()[l]);
            }
            distance_label.add(new Tuple(Math.sqrt(distance),trainingDataSet.get(j).getLabel()));
        }

        distance_label.sort(Comparator.comparing(Tuple::getKey));


        Map<String,Integer> outputs = new HashMap<>();
        for (int j = 0; j < k; j++) {
            String key = distance_label.get(j).getValue();
            outputs.put(key, outputs.getOrDefault(key,0)+1);
        }

        int biggestNumberOfOccurence = Integer.MIN_VALUE;
        String prediction = "";
        for (Map.Entry<String,Integer> entry : outputs.entrySet()){
            if (entry.getValue() > biggestNumberOfOccurence){
                biggestNumberOfOccurence = entry.getValue();
                prediction = entry.getKey();
            }
        }
        System.out.println(prediction);
    }

}
