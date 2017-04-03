package FILE_IO_TEST;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import MainPackage.*;

public class RandomInputGeneratorTester extends UnitTester {
	
	
	@Test
	public void testRandomInputGenerator(){
		
		System.out.println("test RandomInputGenerator : RandomInputGenerator Constructor Success Test");
		
		int inputFileNumber = 3;
		File directory = new File("D:\\testCase\\testRandomGenerator");
		
		RandomTextInputGenerator randomGenerator = 
				new RandomTextInputGenerator
					(directory, inputFileNumber);

		
		File [] inputList = directory.listFiles();
		
		int expectedNumberOfFiles = inputList.length;

		assertEquals(inputFileNumber, expectedNumberOfFiles);
	}
	
	
	//FAIL Wrong Directory
	@Test
	public void FAIL_testRandomInputGenerator(){
		
		System.out.println("test RandomInputGenerator : RandomInputGenerator Constructor Fail Test");
		
		//Wrong Directory added testRandomGenerator+'s'
		RandomTextInputGenerator randomGenerator = 
				new RandomTextInputGenerator(new File("D:\\testCase\\testRandomGenerators"),3);
		

		String randomGeneratorCheckString = randomGenerator.randomGeneratorTestString;

		assertEquals("Random Input Exception Caught", randomGeneratorCheckString);
	}
	
}
