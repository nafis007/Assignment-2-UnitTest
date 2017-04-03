package FILE_IO_TEST;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MainPackage.*;

public class ReaderTester extends UnitTester{
	
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        System.out.println("before class");  
    }  
	
    @Before  
    public void setUp() throws Exception {  
        System.out.println("before");  
    }
    
    
    @Test
	public void testRead() throws ArrayIndexOutOfBoundsException, FileNotFoundException{
    	
    	System.out.println("test Reader: Read Method Success Test");
    	
		Reader reader = new Reader(new File("D:\\testCase\\testReader"));
		
		int expectedFileSize = 2;
		
		assertEquals(expectedFileSize, reader.read(0,0).size()); 
	}
	
    //FAIL ArrayIndexOutoFBound 
    @Test
	public void FAIL_testRead() throws ArrayIndexOutOfBoundsException, FileNotFoundException{
    	
    	System.out.println("test Reader: Read Method FAIL Test");
    	
		Reader reader = new Reader(new File("D:\\testCase\\testReader"));
		
		int expectedFileSize = 2;
		
		//there is only one text file but i am giving to read 2 files
		assertEquals(expectedFileSize, reader.read(0,1).size()); 
	}
    
    //FAIL NO Directory fail test
    @Test
	public void FAIL_testReadNoDirectory() throws ArrayIndexOutOfBoundsException, 
															FileNotFoundException{
    	
    	System.out.println("test Reader: Inside Reader Constructor, No Directory FAIL test");
    	
    	//wrong directory, 's' added
		Reader reader = new Reader(new File("D:\\testCase\\testReaders"));
		
		String expectedFailureString = "Root of any IO exception";
		
		assertEquals(expectedFailureString, reader.read(0,0).get(0)); 
	}
	
    
	@Test
	public void testSortFiles(){

		System.out.println("test Reader: SortFiles in Directory Success Test");
		
		Reader reader = new Reader(new File("D:\\testCase\\testSortFiles"));
		
		File fileFolder = new File("D:\\testCase\\testSortFiles");
		File[] listOfInputFiles = fileFolder.listFiles();
		
		File[] tempFileList = reader.sortFilesInDirectory(listOfInputFiles);
		int lastIndex = extractNumber(tempFileList[3].getName());
		
		assertEquals(10,lastIndex);
	}
	
	/*//FAIL
	@Test
	public void FAILtestSortFiles(){

		Reader reader = new Reader(new File("D:\\testCase\\testSortFiles"));
		
		System.out.println("test case Reader: testSortFiles FAIL Method");
		
		File fileFolder = new File("D:\\testCase\\testSortFiles");
		File[] listOfInputFiles = fileFolder.listFiles();
		
		File[] tempFileList = reader.sortFilesInDirectory(listOfInputFiles);
		int lastIndex = extractNumber(tempFileList[3].getName());
		boolean isSorted = true;
		if ( lastIndex != 10 ) {
			isSorted = false;
		}
		assertFalse(!(isSorted));
	}*/
}
