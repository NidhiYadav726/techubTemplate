# AI-Based Test Environment Management

This project leverages AI to predict the optimal number of browser instances for automated testing using Selenium, and then manages those instances dynamically during test execution. The project utilizes Weka for machine learning model training, with an ARFF file containing resource data to predict the required number of browser instances based on system resource usage (CPU and memory). This hence ensures optimized resource allocation promoting efficient AI driven environment management for efficient and preserved execution.

## Features

- **AI-Powered Resource Management**: Uses machine learning to predict the optimal number of browser instances based on system resource usage (CPU and memory).
- **Dynamic Browser Instance Pool**: Preloads and manages a pool of browser instances to execute tests efficiently.
- **Integration with Selenium and Cucumber**: Automates browser actions using preloaded instances for Selenium and Cucumber tests.
- **Test Execution & Cleanup**: Executes tests using preloaded browser instances and ensures that all drivers are properly closed after execution.

## Setup

### 1. Clone the repository
```bash
# Clone the repository
git clone https://github.com/yourusername/testEnvironment.git
cd testEnvironment
```

### 2. Configure WebDriver
Ensure that you have the appropriate WebDriver (e.g., ChromeDriver) available in your system path, or specify the path explicitly in the ResourcePage class.

### 3. Train the AI Model
The AI model is trained using the modelTrainer class, which uses an ARFF file (resourceData.arff) containing data on CPU and memory usage along with the optimal number of browser instances.

To train the model, run the modelTrainer class:
```bash
java -cp target/classes pages.modelTrainer
```
This will generate a trained model (resourcesModel.model) that is used in the ResourceMonitor class for predictions.

### 4.  Run Tests
You can execute the tests with Cucumber using the TestRunner class. To run the tests:
```bash
mvn test
```
