package FILE_IO_TEST;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
	public void testRead(){
		Reader reader = new Reader(new File("D:\\testCase\\testRead\\success"));
		System.out.println("test case Reader: Read Method");
		int expectedFileSize = 2;
		assertEquals(expectedFileSize,reader.read(0, 0).size());
	}
	//FAIL
	@Test
	public void FAILtestRead(){
		Reader reader = new Reader(new File("D:\\testCase\\testRead\\fail"));
		System.out.println("test case Reader: FAIL Read Method");
		
		boolean isWronglyRead = true;
		
		int readFileLength = reader.read(0, 0).size();
		int expectedFileSize = 2;
		
		if ( readFileLength == expectedFileSize ) {
			isWronglyRead = false;
		}
		assertTrue(isWronglyRead);
	}
	
	@Test
	public void testSortFiles(){

		Reader reader = new Reader(new File("D:\\testCase\\testSortFiles"));
		System.out.println("test case Reader: testSortFiles Method");
		File fileFolder = new File("D:\\testCase\\testSortFiles");
		File[] listOfInputFiles = fileFolder.listFiles();
		
		File[] tempFileList = reader.sortFilesInDirectory(listOfInputFiles);
		int lastIndex = extractNumber(tempFileList[3].getName());
		assertEquals(10,lastIndex);
	}
	
	//FAIL
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
	}
}
