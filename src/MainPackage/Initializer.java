package MainPackage;
import java.io.File;

public class Initializer 
{
	public static int numberOfInputFiles;
	public static int numberOfThreads;
	
	public static File targetInputDirectory;
	public static File targetOutputDirectory;
	public static File masterOutputDirectory;
	
	public Initializer(int inputFileNumber, int threadNumber)
	{
		
		numberOfInputFiles = inputFileNumber;
		numberOfThreads = threadNumber;
		
		targetInputDirectory = new File( "D:\\randomInputFolder" );
		targetOutputDirectory = new File( "D:\\targetOutputFolder" );
		masterOutputDirectory = new File( "D:\\masterOutputFolder" );
		
		//cleanDirectories();
		
		//new RandomTextInputGenerator(targetInputDirectory,numberOfInputFiles);
	}
	
	public void generateRandomInput()
	{
		
		new RandomTextInputGenerator(targetInputDirectory, numberOfInputFiles);
		
	}
	
    public void cleanDirectories(){
		
		System.out.println("Cleaning Directories");
		
		for ( File file : targetInputDirectory.listFiles() ) 
		{
			if ( !file.isDirectory() ) 
			{
				file.delete();
			}
		}
		
		for ( File file : targetOutputDirectory.listFiles() ) 
		{
			if ( !file.isDirectory() ) 
			{
				file.delete();
			}
		}
	}
}