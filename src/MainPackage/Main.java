package MainPackage;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main
{
	
	public static void main(String[] args) {
		
		int inputFileNumber = 17;
		int threadNumber = 5;
		
		Initializer initializer = new Initializer(inputFileNumber, threadNumber);
		
		initializer.cleanDirectories();
		initializer.generateRandomInput();

		TextFileReadWriteThreadHandler textFileThreadManager = 
				new TextFileReadWriteThreadHandler(initializer.numberOfInputFiles, 
												   initializer.numberOfThreads, 
												   initializer.targetInputDirectory, 
												   initializer.targetOutputDirectory);
		
		
		textFileThreadManager.threadReadWrite();
		textFileThreadManager.masterReadWrite(initializer.targetOutputDirectory,
											  initializer.masterOutputDirectory);

	}
}
