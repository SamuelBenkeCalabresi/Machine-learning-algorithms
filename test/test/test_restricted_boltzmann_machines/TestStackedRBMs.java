package test.test_restricted_boltzmann_machines;

/**
 * This was the test on stacked RBMs with on top a Softmax layer 
 * It did not perform right like MLP and DBN 
 * therefore it is pointless for the project. */
//package test.test_restricted_boltzmann_machines;
//
//import classifiers.generative_graphical_models.RBM;
//import classifiers.softmax_regression.SoftmaxRegression;
//import dataset.BinaryData;
//import dataset.LabelledData;
//import test.test_softmax_regression.TwoFoldTest;
//import util.BinaryParser;
//import util.Parser;
//
//public class Main {
//	public static void main(String [] args) throws Exception {
//
//		long startTime = System.currentTimeMillis();
//		/* data as binary BernoulliRBM */
//		BinaryData [] binaryDataSet = BinaryParser.parse("cw2DataSet1.csv");
//
//		double [][] trainingDataSet = new double[binaryDataSet.length][BinaryData.SIZE];
//		//		trainingDataSet = BinaryData.getLabelledData(binaryDataSet);
//		trainingDataSet = BinaryData.getUnlabelledData(binaryDataSet);
//
//		// These is data to test in binary for RBMs not for Softmax
//		// Question is, if Softmax is trained on binary data
//		// How can recognise then stuff not binary??
//		// Try to test with both the testing data sets
//		//		BinaryData [] binaryTestDataSet = BinaryParser.parse("cw2DataSet2.csv");
//		//		double [][] testDataSet = new double [binaryTestDataSet.length][BinaryData.SIZE_LABELLED_DATA];
//
//		// 1 set the visible units to these vectors
//		int numberVisibleUnits = BinaryData.SIZE; // 64 
//		int numberHiddenUnits =  BinaryData.SIZE/2; // 64 
//
//		/// TRAINING 3 RBM LAYERS
//		// input for new layer
//		RBM rbmLayer1 = new RBM(numberVisibleUnits, numberHiddenUnits);
//		rbmLayer1.initRandomWeights();
//		rbmLayer1.training(trainingDataSet, 100); // this should have only trainingData
//		double [][] hiddenStates = rbmLayer1.outputMatrix(trainingDataSet);
//
//		// try to make some new stuff with a new RBM layer
//		RBM rbmLayer2 = new RBM(numberVisibleUnits, numberHiddenUnits);	
//		//have to get same weights from first layer to place in second layer
//		rbmLayer2.weights = rbmLayer1.weights;	
//		rbmLayer2.training(hiddenStates, 100);		
//		// get input of Layer 2 from Layer 1 output
//		double [][] hiddenStates2 = rbmLayer2.outputMatrix(trainingDataSet);
//
//		RBM rbmLayer3 = new RBM(numberVisibleUnits, numberHiddenUnits);		
//		//have to get same weights from first layer to place in second layer
//		rbmLayer3.weights = rbmLayer2.weights;
//		rbmLayer3.training(hiddenStates2, 100);
//		// get input of Layer 3 from Layer 2 output
//		double [][] hiddenStates3 = rbmLayer3.outputMatrix(trainingDataSet);
//
//		// NOW: I should add the labels to the unlabelled training data
//		// These data should have been changed since 3 feature detectors were applied
//		// The new generative data has to be combined with labels and trained in 1 SoftmaxLayer
//		// The real value should be made with a MLP for the end of this week
//
//
//		/// TRAINING SOFTMAX LAYER 
//		// training data for the SoftmaxRegressionc clf generated by stack of RBMs 
//		double[][] generativeLabelledData = new double[binaryDataSet.length][BinaryData.SIZE];
//		generativeLabelledData = hiddenStates3;
//		// labels of the  training data
//		int[][] trainingLabels = new int[binaryDataSet.length][LabelledData.SOLUTION_LENGTH];
//		for(int i = 0 ; i < trainingLabels.length; i++) {
//			trainingLabels[i] = binaryDataSet[i].returnBinarySolutionsAsInt((int)binaryDataSet[i].getSolution());
//		}
//
//		// I've to make instance of SoftmaxRegression clf
//		double learningRate = 0.15;
//		int numberOfEpochs = 500;
//
//		SoftmaxRegression softmaxClassifier = new SoftmaxRegression(LabelledData.SIZE, LabelledData.SOLUTION_LENGTH,
//				learningRate, numberOfEpochs);
//		//		softmaxClassifier.fit(generativeLabelledData, trainingLabels);
//
//		// I can add the epochs and matrices in the train or leave them in fit
//		for(int epoch = 0; epoch < numberOfEpochs; epoch ++) {
//			for(int i = 0; i < trainingDataSet.length; i++) {
//				softmaxClassifier.train(generativeLabelledData[i], trainingLabels[i]);
//			}
//		}
//
//		/// TESTING SOFTMAX LAYER
//		/// 4 different times with 2 fold test: binary data x 2, normal data x 2.
//		LabelledData[] normalTrainingDataSet = Parser.parse("cw2DataSet1.csv");
//		LabelledData[] normalTestingDataSet  = Parser.parse("cw2DataSet2.csv");
//		
//		BinaryData [] binaryTestDataSet = BinaryParser.parse("cw2DataSet2.csv");
//	    double [][] testDataSet = new double [binaryTestDataSet.length][BinaryData.SIZE];
//
//		double[][] testData = new double[normalTestingDataSet.length][LabelledData.SIZE];
//		double[][] testLabels = new double[normalTestingDataSet.length][LabelledData.SOLUTION_LENGTH];
// 
//		TwoFoldTest twoFoldTest = new TwoFoldTest(normalTrainingDataSet, normalTestingDataSet);
//		
//		// testData of normal data: With this test performed only 9% right, that's real crap!
//		// testDataSet of binary data: again performed only 9% right, that's real crap!
//		// Testing set size: 2810
//		// Classes predicted right: 274
//		// Accuracy on the testing set: 9.750889679715302%
//		// Took: 374 seconds.
//		// 
//		// With number of epochs = 100 for each RBM = doesn't change anything
//		// Thus, with generative data the softmax doesn't learn anything
//		// Testing set size: 2810
//		// Classes predicted right: 274
//		// Accuracy on the testing set: 9.750889679715302%
//		// Took: 69 seconds.
//		//
////		twoFoldTest.score(softmaxClassifier, testDataSet, false);
//
//		long endTime   = System.currentTimeMillis();
//		System.out.println("Took: " + ((endTime - startTime) / 1000) + " seconds.");
//	}
//}