package utils;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;


import java.util.logging.Logger;

public class ResourceMonitor {
    private static final Logger log = Logger.getLogger(ResourceMonitor.class.getName());
    private Classifier aiModel;

    public ResourceMonitor() throws Exception {
        log.info("Loading AI model");
        aiModel = loadAIModel();
    }

    public int predictOptimalInstances() throws Exception {
        log.info("Predicting optimal instances of drivers using AI");

        // Input (e.g., CPU and memory usage)
        double cpuUsage = 40.0;
        double memoryUsage = 50.0;

        // Create a Weka instance for prediction
        Instance instance = createInstance(cpuUsage, memoryUsage);
        double prediction = aiModel.classifyInstance(instance);

        return (int) Math.round(prediction); //Predicted number of browser instances
    }

    private Classifier loadAIModel() throws Exception {
        return (Classifier) weka.core.SerializationHelper.read("src/main/resources/resourcesModel.model");
    }

    private Instance createInstance(double cpuUsage, double memoryUsage) throws Exception {
        Instances datasetStructure = new ConverterUtils.DataSource("src/main/resources/resourceData.arff").getStructure();
        datasetStructure.setClassIndex(datasetStructure.numAttributes() - 1);

        Instance instance = new weka.core.DenseInstance(datasetStructure.numAttributes());
        instance.setDataset(datasetStructure);
        instance.setValue(0, cpuUsage);
        instance.setValue(1, memoryUsage);
        return instance;
    }
}