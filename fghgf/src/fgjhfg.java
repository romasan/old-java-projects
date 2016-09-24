import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class fgjhfg {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.print("lsukdfjkasdf");
		/*Instances train = ... // from somewhere
		Instances test = ... // from somewhere
		Classifier cls = new J48();
		cls.buildClassifier(train);
		// evaluate classifier and print some statistics
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls, test);
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));*/

		
		/*BufferedReader reader = new BufferedReader(
                 new FileReader("src/data.arff"));
Instances data = new Instances(reader);
reader.close();
// setting class attribute
data.setClassIndex(data.numAttributes() - 1);*/
		
		
		// data/train-data-tweets.arff
		// data/test-data-tweets.arff
		
		FilteredClassifier classifier;
		Instances trainData;
		//====================== тренировка?
		
		ConverterUtils.DataSource trainSource = new ConverterUtils.DataSource("src/pred2.arff");

		StringToWordVector filter = new StringToWordVector();
		classifier = new FilteredClassifier();
		classifier.setFilter(filter);
		classifier.setClassifier(new NaiveBayes());
		trainData = trainSource.getDataSet();
		if (trainData.classIndex() == -1)
		trainData.setClassIndex(trainData.numAttributes() - 1);

		classifier.buildClassifier(trainData);
		
		//======================
		
		
		
		
		
		
		ConverterUtils.DataSource testSource = new ConverterUtils.DataSource("src/pred.arff");

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
		System.out.println(", predicted: " + testData.classAttribute().value((int) pred));
		/*System.out.println(", probability: " +
		testData.instance(i))[0],
		classifier.distributionForInstance(testData.instance(i))[1])
		);*/
		}

		System.out.println(counter);
		System.out.println(all);

		Evaluation eval = new Evaluation(trainData);
		eval.evaluateModel(classifier, testData);
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
		
	}

}
