

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyClassifier {
FilteredClassifier classifier;
Instances trainData;

public MyClassifier(String trainDataFile) throws Exception {
ConverterUtils.DataSource trainSource = new ConverterUtils.DataSource(trainDataFile);

StringToWordVector filter = new StringToWordVector();
classifier = new FilteredClassifier();
classifier.setFilter(filter);
classifier.setClassifier(new NaiveBayes());
trainData = trainSource.getDataSet();
if (trainData.classIndex() == -1)
trainData.setClassIndex(trainData.numAttributes() - 1);

classifier.buildClassifier(trainData);
}

public void classifyTestData(String testDataFile) throws Exception {
// data/train-data-tweets.arff
// data/test-data-tweets.arff
ConverterUtils.DataSource testSource = new ConverterUtils.DataSource(testDataFile);

Instances testData = testSource.getDataSet();

if (testData.classIndex() == -1)
testData.setClassIndex(testData.numAttributes() - 1);

int counter = 0;
int all = 0;

for (int i = 0; i < testData.numInstances(); i++) {
double pred = classifier.classifyInstance(testData.instance(i));

String actual = testData.classAttribute().value((int) testData.instance(i).classValue());
String predicted = testData.classAttribute().value((int) pred);

if (actual.equals(predicted)){
counter++;
}
all++;
System.out.print("ID: " + testData.instance(i).value(0));
System.out.print(", actual: " + testData.classAttribute().value((int) testData.instance(i).classValue()));
System.out.print(", predicted: " + testData.classAttribute().value((int) pred));
System.out.println(", probability: " +
getMax(classifier.distributionForInstance(
testData.instance(i))[0],
classifier.distributionForInstance(testData.instance(i))[1])
);
}

System.out.println(counter);
System.out.println(all);

Evaluation eval = new Evaluation(trainData);
eval.evaluateModel(classifier, testData);
System.out.println(eval.toSummaryString("\nResults\n======\n", false));

}

private double getMax(double first, double second) {
if (first > second) return first;
return second;
}

public double getSpamStatus(ArrayList<String> messages) throws Exception {
Attribute attribute = new Attribute("Text", (FastVector) null);
FastVector classAttributeVector = new FastVector(2);
classAttributeVector.addElement("spam");
classAttributeVector.addElement("normal");
Attribute classAttribute = new Attribute("class-att", classAttributeVector);

FastVector attributesVector = new FastVector(2);
attributesVector.addElement(attribute);
attributesVector.addElement(classAttribute);
Instances instances = new Instances("my", attributesVector, 0);
instances.setClass(classAttribute);
int spamCount=0;

for (String text : messages){
Instance instance = new Instance(2);
instance.setValue(attribute, attribute.addStringValue(text));
instances.add(instance);
instance.setDataset(instances);
}

for(int i = 0; i < instances.numInstances(); i++){
double predicted = classifier.classifyInstance(instances.instance(i));
if(predicted == 0.0) spamCount++;
}

return (double)spamCount/(double)messages.size();
}
}
