package MainPackage;
import java.io.File;
import java.util.ArrayList;

public class TextFileReadWriteThreadHandler extends Resources
{
	static int numberOfInputFiles;
	static int numberOfThreads;
	
	static File inputDir;
	static File outputDir;
	
	
	public TextFileReadWriteThreadHandler(int numberOfInputFiles, int numberOfThreads, File inDir, File outDir)
	{
		this.numberOfInputFiles = numberOfInputFiles;
		this.numberOfThreads = numberOfThreads;
		this.inputDir = inDir;
		this.outputDir = outDir;
		
	}
	
	public boolean notEquallyDistributed(int inputFiles, int runnableId, int chunksOfFiles)
	{
		boolean decision =  false;
		
		if ( inputFiles - ( ( runnableId + 1 ) * chunksOfFiles ) < chunksOfFiles ) 
		{
			decision = true;
		}
		return decision;
	}
	
	public void runnableManager()
	{
		int chunksOfFiles = numberOfInputFiles / numberOfThreads;
		
		int remainingFiles = numberOfInputFiles % numberOfThreads;
		
		System.out.println("debug chunk size: " + chunksOfFiles + " remaining size: " + remainingFiles);
		
		for ( int firstIndex = 0; firstIndex < numberOfInputFiles; firstIndex += chunksOfFiles ) {

			int lastIndex;
			
			int runnableId = firstIndex / chunksOfFiles;
			
			if ( notEquallyDistributed(numberOfInputFiles, runnableId, chunksOfFiles) 
					&& remainingFiles != 0 )
			{
				
				Reader inputReader = new Reader(inputDir);
				Writer outputWriter = new Writer(outputDir);
				
				// reading from current file index to the very last index as this chucnk is not equally distributed
				lastIndex = firstIndex + chunksOfFiles + remainingFiles - 1;  
				
				System.out.println("First Index: " + firstIndex + " Last Index: " + lastIndex);
				
				ReadWriteRunnable tempRunnable = 
					new ReadWriteRunnable(inputReader, outputWriter, firstIndex, lastIndex, runnableId);
				
				listOfRunnables.add(tempRunnable);
				
				break;
			}
			else
			{
				
				Reader inputReader = new Reader(inputDir);
				Writer outputWriter = new Writer(outputDir);
				
				// reading from current file index to the equally distributed last index
				lastIndex = firstIndex + chunksOfFiles - 1;
				
				System.out.println("First Index: "+ firstIndex + " Last Index: " + lastIndex);
				
				ReadWriteRunnable tempRunnable = 
					new ReadWriteRunnable(inputReader,outputWriter,firstIndex,lastIndex,runnableId);
				
				listOfRunnables.add(tempRunnable);
			}
	
		}
	}
	
	
	public void threadCreator()
	{
		for ( int runnableIndex = 0; runnableIndex < listOfRunnables.size(); runnableIndex++ ){
			
			Thread tempThread = new Thread(listOfRunnables.get(runnableIndex));
			
			listOfReadWriteThreads.add(tempThread);
			
		}
	}
	
	
	public void threadStartAndJoin()
	{
		for ( int threadIndex = 0; threadIndex < listOfReadWriteThreads.size(); threadIndex++ ){
			
			listOfReadWriteThreads.get(threadIndex).start();
			
		}
		
		
		for ( int threadIndex = 0; threadIndex < listOfReadWriteThreads.size(); threadIndex++ ) {
			
			try{
				
				listOfReadWriteThreads.get(threadIndex).join();
				
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			
		}
		
	}
	
	
	public void threadReadWrite()
	{
		runnableManager();

		threadCreator();

		threadStartAndJoin();
	}
	
	public void masterReadWrite(File masterInDir, File masterOutDir)
	{
		Reader masterReader = new Reader(masterInDir);
		Writer masterWriter = new Writer(masterOutDir);
		
		ReadWriteRunnable masterRunnable = 
				new ReadWriteRunnable(masterReader, masterWriter, 0, numberOfThreads-1, -1);
		
		Thread masterThread = new Thread(masterRunnable);
		
		masterThread.start();
		
		try {
			masterThread.join();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		/*ArrayList<String> masterAllInputStrings = new ArrayList<String>();
		masterAllInputStrings=masterReader.read(0, numberOfThreads-1);
		masterWriter.write(masterAllInputStrings, -1);*/
	}
}