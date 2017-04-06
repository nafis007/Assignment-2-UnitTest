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
    
    
    @Test
	public void testRead() throws ArrayIndexOutOfBoundsException, FileNotFoundException{
    	
    	System.out.println("test Reader: Read Method Success Test");
    	
		Reader reader = new Reader(new File("D:\\testCase\\testReader\\readerAndRead"));
		
		int expectedFileLength = 2;
		int receivedFileLength = reader.read(0,0).size();
		
		assertEquals(expectedFileLength, receivedFileLength); 
	}
	
    //FAIL ArrayIndexOutoFBound 
    @Test
	public void FAIL_testRead() throws ArrayIndexOutOfBoundsException, FileNotFoundException{
    	
    	System.out.println("test Reader: Read Method FAIL Test");
    	
		Reader reader = new Reader(new File("D:\\testCase\\testReader\\readerAndRead"));
		
		int expectedFileLength = 2;
		
		//there is only one text file but i am giving to read 2 text files (index 0 and 1)
		int receivedFileLength = reader.read(0,1).size();
		
		assertEquals(expectedFileLength, receivedFileLength); 
	}
    
    //FAIL NO Directory fail test
    @Test
	public void FAIL_testReadNoDirectory() throws ArrayIndexOutOfBoundsException, 
															FileNotFoundException{
    	
    	System.out.println("test Reader: Inside Reader Constructor, No Directory FAIL test");
    	
    	//wrong directory, testReader+'s' added
		Reader reader = new Reader(new File("D:\\testCase\\testReaders\\readerAndRead"));
		
		String expectedFailureString = "Root of any IO exception";
		String receivedFailureString = reader.read(0, 0).get(0);
		
		assertEquals(expectedFailureString, receivedFailureString); 
	}
	
    
    
    
	@Test
	public void testSortFiles(){

		System.out.println("test Reader: SortFiles in Directory Success Test");
		
		Reader reader = new Reader(new File("D:\\testCase\\testReader\\testSortFiles"));
		
		File fileFolder = new File("D:\\testCase\\testReader\\testSortFiles");
		File[] listOfInputFiles = fileFolder.listFiles();
		
		File[] tempFileList = reader.sortFilesInDirectory(listOfInputFiles);
		
		int receivedLastIndex = extractNumber(tempFileList[4].getName());
		
		int expectedLastIndex = 15;
		
		assertEquals(expectedLastIndex,receivedLastIndex);
	}
	
	//FAIL File Name Format not matched
	@Test
	public void FAIL_testSortFiles(){

		System.out.println("test Reader: SortFiles in Directory Fail Test");
		
		Reader reader = new Reader(new File("D:\\testCase\\testReader\\sortFailCase"));
		
		File fileFolder = new File("D:\\testCase\\testReader\\sortFailCase");
		File[] listOfInputFiles = fileFolder.listFiles();
		
		reader.sortFilesInDirectory(listOfInputFiles);
		
		String expectedFailureString = "File Name Format Not Matched Caught";
		
		String receivedFailureString = reader.readerTestString;
		
		assertEquals(expectedFailureString, receivedFailureString);
	}
}
