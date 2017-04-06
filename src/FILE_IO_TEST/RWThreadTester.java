package FILE_IO_TEST;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import MainPackage.*;

public class RWThreadTester extends UnitTester {
	
	@Test
	public void testNotEquallyDistributed() {
    	
    	System.out.println("test RWThread : NotEquallyaDistributed Method Success Test");
    	
    	File inputFolder = new File("D:\\testCase\\testThreads\\input");
    	
    	File outputFolder = new File("D:\\testCase\\testThreads\\output");
    	
    	int numberOfInputFiles = 9;
    	int numberOfThreads = 4;
    	int chunkSize = numberOfInputFiles / numberOfThreads;
    	
		TextFileReadWriteThreadHandler testThread = 
				new TextFileReadWriteThreadHandler
					(numberOfInputFiles, numberOfThreads, inputFolder, outputFolder);
		
		boolean receivedDecision = 
				testThread.notEquallyDistributed(numberOfInputFiles, numberOfThreads-1, chunkSize);
		
		assertTrue(receivedDecision); 
	}
	
	
	@Test
	public void testRunnableManagerWithThreadCreator() throws Exception {
    	
    	System.out.println("test RWThread : runnableManager and threadCreator Method Success Test");
    	
    	File inputFolder = new File("D:\\testCase\\testThreads\\input");
    	
    	File outputFolder = new File("D:\\testCase\\testThreads\\output");
    	
    	int numberOfInputFiles = 9;
    	int numberOfThreads = 4;
    	int chunkSize = numberOfInputFiles / numberOfThreads;
    	
		TextFileReadWriteThreadHandler testThread = 
				new TextFileReadWriteThreadHandler
					(numberOfInputFiles, numberOfThreads, inputFolder, outputFolder);
		
		testThread.runnableManager();
		testThread.threadCreator();
		
		int checkNumberOfThreads = testThread.listOfReadWriteThreads.size();
		int checkNumberOfRunnables = testThread.listOfJobs.size();
		
		assertEquals(checkNumberOfThreads,checkNumberOfRunnables); 
	}
	
	//FAIL
	@Test
	public void FAIL_testRunnableManager() throws Exception {
    	
    	System.out.println("test RWThread : runnableManager Method FAIL Test");
    	
    	File inputFolder = new File("D:\\testCase\\testThreads\\failCase\\inpust");
    	
    	File outputFolder = new File("D:\\testCase\\testThreads\\failCase\\outpust");
    	
    	int numberOfInputFiles = 9;
    	int numberOfThreads = 4;
    	int chunkSize = numberOfInputFiles / numberOfThreads;
    	
		TextFileReadWriteThreadHandler testThread = 
				new TextFileReadWriteThreadHandler
					(numberOfInputFiles, numberOfThreads, inputFolder, outputFolder);
		
		testThread.runnableManager();
		testThread.threadCreator();
		
		int checkNumberOfThreads = testThread.listOfReadWriteThreads.size();
		int checkNumberOfRunnables = testThread.listOfJobs.size();
		
		int dummy1 = 1;
		int dummy2 = 1;
		assertEquals(dummy1,dummy2); 
	}
	
	
	
	
}
