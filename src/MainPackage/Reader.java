package MainPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Reader 
{
	ArrayList<String> inputStringsMerger;  //merger StringList
	
	File targetDirectory;
	File[] listOfInputFiles;
	
	public Reader(File directory) throws NullPointerException
	{
		inputStringsMerger = new ArrayList<String>();
		try {
			targetDirectory=directory;
			listOfInputFiles = sortFilesInDirectory(targetDirectory.listFiles()); 
		}
		catch(NullPointerException exception) {
			System.out.println("NullPointer Excpetion Check");
		}
	}
	
	public File[] sortFilesInDirectory(File[] files)
	{
		Arrays.sort(files, 
				new Comparator<File>()
					{
						public int compare(File file1, File file2) {
			                int n1 = extractNumber(file1.getName());
			                int n2 = extractNumber(file2.getName());
			                return n1 - n2;
			            }
			
			            private int extractNumber(String name) {
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
			        });
		
        /*for ( File f : files ) {
            System.out.println(f.getName());    //debug sorted file list print
        }*/	 
		
        return files;
	}
	
	public ArrayList<String> read(int firstFileIndex, int lastFileIndex) 
			throws ArrayIndexOutOfBoundsException , FileNotFoundException, NullPointerException
	{
		try{
			
			if( targetDirectory.exists() ) {
				for ( int index = firstFileIndex; index <= lastFileIndex; index++ ) 
				{
					
					File inputFile = listOfInputFiles[index];
					
					if ( inputFile.isFile() && inputFile.getName().endsWith(".txt") ) 
					{
						FileReader inputFileReader = new FileReader(inputFile);
						BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
						
						String inputLine = null;
						
						while ( (inputLine = inputBufferedReader.readLine()) != null )
						{
					//		System.out.println(inputLine); //debug
							inputStringsMerger.add(inputLine);  //merge
							
						} 
						inputBufferedReader.close();
					} 
				}
			}
			else {
				throw new NotDirectoryException(targetDirectory.toString());
			}
			
			
			
		}
		catch(ArrayIndexOutOfBoundsException exception)
		{
			System.out.println("ArrayIndexOutOfBound Excpetion Check");
		}
		catch(NullPointerException exception)
		{
			System.out.println("NullPointer Excpetion Check");
		}
		catch(IOException exception) 
		{
			inputStringsMerger.add("Root of any IO exception");
			
			System.out.println("Any kind of IO Excpetion Check");
		}
		catch(Exception exception) 
		{
			System.out.println("Excpetion Handled Check");
			
		}
		return inputStringsMerger;
	}
	
	
	public void readerTestPrint()
	{   
		System.out.println("Inside reader");
		
		for ( String line : inputStringsMerger )
		{	
			System.out.println("Reading lines: "+line);	
		}
	}
}