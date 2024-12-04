package pages;


import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

import java.util.logging.Logger;

public class modelTrainer {
    private static final Logger log = Logger.getLogger(modelTrainer.class.getName());

    public static void main(String[] args) {
        try {
            //Load resourceData arff file
            String arffFilePath = "src/main/resources/resourceData.arff";
            ConverterUtils.DataSource dataSource = new ConverterUtils.DataSource(arffFilePath);
            Instances data = dataSource.getDataSet();

            // Set the class index (target attribute)
            // Assuming the last attribute is the class to be predicted
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Train a Linear Regression model
            Classifier model = new LinearRegression();
            model.buildClassifier(data);

            // Save the model to a file
            String modelFilePath = "src/main/resources/resourcesModel.model";
            SerializationHelper.write(modelFilePath, model);

            log.info("Model training completed and saved at: " + modelFilePath);

        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error occurred while training the model");
        }
    }
}
