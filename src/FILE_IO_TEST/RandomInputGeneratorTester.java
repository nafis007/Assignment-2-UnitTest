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
		
		File directory = new File("D:\\testCase\\testRandomGenerator");
		
		int expectedNumberOfFiles = 3;
		
		RandomTextInputGenerator randomGenerator = 
				new RandomTextInputGenerator
					(directory, expectedNumberOfFiles);

		
		File [] inputList = directory.listFiles();
		
		
		int receivedNumberOfFiles = inputList.length;

		assertEquals(expectedNumberOfFiles, receivedNumberOfFiles);
	}
	
	
	//FAIL Wrong Directory
	@Test
	public void FAIL_testRandomInputGenerator(){
		
		System.out.println("test RandomInputGenerator : RandomInputGenerator Constructor Fail Test");
		
		//Wrong Directory added testRandomGenerator+'s'
		RandomTextInputGenerator randomGenerator = 
				new RandomTextInputGenerator(new File("D:\\testCase\\testRandomGenerators"),3);
		

		String randomGeneratorReceivedString = randomGenerator.randomGeneratorTestString;
		
		String expectedString = "Random Input Exception Caught";

		assertEquals(expectedString, randomGeneratorReceivedString);
	}
	
}
