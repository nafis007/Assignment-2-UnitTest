package FILE_IO_TEST;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import MainPackage.*;

public class WriterTester extends UnitTester{
	
	
	@Test
	public void testWrite(){
		
		System.out.println("test Writer: Write Method Success Test");
		
		Writer writer = new Writer(new File("D:\\testCase\\testWriter"));
		
		ArrayList<String> testInputString = new ArrayList<String>();
		testInputString.add("test write");
		writer.write(testInputString, 1);
		
		String expectedString = "test write";
		String afterWriteReceivedString = readForTest();

		assertEquals(expectedString, afterWriteReceivedString);
	}
	
	
	//FAIL Null String Passing and Wrong Directory
	@Test
	public void FAIL_testWrite(){
		
		System.out.println("test Writer: Write Method FAIL Test");
		
		//wrong directory, testWriter+'s' added
		Writer writer = new Writer(new File("D:\\testCase\\testWriters"));
		
		ArrayList<String> testInputString = new ArrayList<String>();
		testInputString.add("test write");
		writer.write(testInputString, 1);
		
		String expectedString = "Exception in Write Method Caught";
		String afterWriteReceivedString = writer.writerTestString;

		assertEquals(expectedString, afterWriteReceivedString);
	}
}
