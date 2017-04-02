package MainPackage;
import java.io.File;
import java.util.ArrayList;

public class Resources 
{
	public static ArrayList<Thread> listOfReadWriteThreads = new ArrayList<Thread>();
	
	public static ArrayList<ReadWriteRunnable> listOfRunnables = new ArrayList<ReadWriteRunnable>();
	
	public static int numberOfInputFiles;
	public static int numberOfThreads;
	
	public static File targetInputDirectory;
	public static File targetOutputDirectory;
	public static File masterOutputDirectory;
}