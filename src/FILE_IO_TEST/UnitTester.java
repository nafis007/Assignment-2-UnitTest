package FILE_IO_TEST;

import MainPackage.*;
import static org.junit.Assert.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;



public class UnitTester {
	////////////////////for sortFileTesting////////////////////
	public int extractNumber(String name) {
        int i = 0;
        try {
            int startPosition = 0;
            int endPosition = name.lastIndexOf('.');
            String number = name.substring(startPosition, endPosition);
            i = Integer.parseInt(number);
        } catch(Exception e) {
            i = 0; // if filename does not match the format
                  // then default to 0
        }
        return i;
    }
	////////////////////for sortFileTesting////////////////////
	
	////////////////for write test///////////////////////////////
	public String readForTest() {
		String writeCheckString = "";
		
		try{
			File inputFile = new File("D:\\testCase\\testWriter\\output1.txt");
			if (inputFile.isFile() && inputFile.getName().endsWith(".txt")) 
			{
				FileReader inputFileReader = new FileReader(inputFile);
				BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
				
				
				String inputLine = null;
				while ((inputLine = inputBufferedReader.readLine()) != null)
				{
			//		System.out.println(inputLine); //debug
					//inputStringsMerger.add(inputLine);  //merge
					writeCheckString = inputLine;
					
				} 
				inputBufferedReader.close();
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return writeCheckString;
	}
	////////////////for write test///////////////////////////////
	
	
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        System.out.println("before class");  
    }  
    @Before  
    public void setUp() throws Exception {  
        System.out.println("before");  
    }
	
    
    
    
    @After  
    public void tearDown() throws Exception {  
        System.out.println("after");  
    }  
  
    @AfterClass  
    public static void tearDownAfterClass() throws Exception {  
        System.out.println("after class");  
    }  
    
    /////////////////Reader Class Unit Tests///////////////////////
	
	
	/////////////////Writer Class Unit Tests///////////////////////
	/*@Test
	public void testWriter_methodWrite(){
		Writer writer = new Writer(new File("D:\\testCase\\testWrite"));
		System.out.println("test case Writer: Write Method");
		ArrayList<String> testInputString = new ArrayList<String>();
		testInputString.add("test write");
		writer.write(testInputString, 1);
		
		String writeCheckString = readForTest();

		assertEquals("test write",writeCheckString);
	}
	
	//FAIL
	@Test
	public void FAILtestWriter_methodWrite(){
		Writer writer = new Writer(new File("D:\\testCase\\testWrite"));
		System.out.println("test case Writer: FAIL Write Method");
		ArrayList<String> testInputString = new ArrayList<String>();
		testInputString.add("test write");
		writer.write(testInputString, 1);
		
		String writeCheckString = readForTest();
		
		boolean isCorrectlyWritten = true;
		if ( !(writeCheckString.equals("test write")) ) {
			isCorrectlyWritten = false;
		}

		assertFalse(!(isCorrectlyWritten));
	}
	
	/////////////////ThreadHandler Class Unit Test///////////////////////
	@Test
	public void testThreadHandler(){
		File inDir = new File("D:\\testCase\\testRunnableManager\\inDir");
		File outDir = new File("D:\\testCase\\testRunnableManager\\outDir");
		
		TextFileReadWriteThreadHandler thread = 
				new TextFileReadWriteThreadHandler (2,2,inDir,outDir);
				
		System.out.println("test case ThreadHandler");
		
		thread.runnableManager();
		thread.threadCreator();
		//assertEquals(2,listOfRunnables.size());
		assertEquals(listOfReadWriteThreads.size(),listOfJobs.size());
	}*/
	
}
