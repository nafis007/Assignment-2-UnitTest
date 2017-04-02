package MainPackage;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main extends Resources
{
	
	public static void main(String[] args) {
		
		int inputFileNumber = 17;
		int threadNumber = 5;
		
		Initializer initializer = new Initializer(inputFileNumber, threadNumber);
		
		initializer.cleanDirectories();
		initializer.generateRandomInput();

		TextFileReadWriteThreadHandler textFileThreadManager = 
				new TextFileReadWriteThreadHandler(numberOfInputFiles, 
													numberOfThreads, 
													targetInputDirectory, 
													targetOutputDirectory);
		
		
		textFileThreadManager.threadReadWrite();
		textFileThreadManager.masterReadWrite(targetOutputDirectory, masterOutputDirectory);

	}
}
