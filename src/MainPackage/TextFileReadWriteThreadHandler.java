package MainPackage;
import java.io.File;
import java.util.ArrayList;

public class TextFileReadWriteThreadHandler 
{
	public ArrayList<Thread> listOfReadWriteThreads = new ArrayList<Thread>();
	
	public ArrayList<ReadWriteJob> listOfJobs = new ArrayList<ReadWriteJob>();
	
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
	
	public boolean notEquallyDistributed(int inputFiles, int jobId, int chunksOfFiles)
	{
		boolean decision =  false;
		
		if ( inputFiles - ( ( jobId + 1 ) * chunksOfFiles ) < chunksOfFiles ) 
		{
			decision = true;
		}
		return decision;
	}
	
	public void runnableManager() throws Exception
	{
		int chunksOfFiles = numberOfInputFiles / numberOfThreads;
		
		int remainingFiles = numberOfInputFiles % numberOfThreads;
		
		System.out.println("debug chunk size: " + chunksOfFiles + " remaining size: " + remainingFiles);
		
		try {
			for ( int firstIndex = 0; firstIndex < numberOfInputFiles; firstIndex += chunksOfFiles ) {
	
				int lastIndex;
				
				int jobId = firstIndex / chunksOfFiles;
				
				if ( notEquallyDistributed(numberOfInputFiles, jobId, chunksOfFiles) 
						&& remainingFiles != 0 )
				{
					
					Reader inputReader = new Reader(inputDir);
					Writer outputWriter = new Writer(outputDir);
					
					// reading from current file index to the very last index as this chucnk is not equally distributed
					lastIndex = firstIndex + chunksOfFiles + remainingFiles - 1;  
					
					System.out.println("First Index: " + firstIndex + " Last Index: " + lastIndex);
					
					ReadWriteJob tempJob = 
						new ReadWriteJob(inputReader, outputWriter, firstIndex, lastIndex, jobId);
					
					listOfJobs.add(tempJob);
					
					break;
				}
				else
				{
					
					Reader inputReader = new Reader(inputDir);
					Writer outputWriter = new Writer(outputDir);
					
					// reading from current file index to the equally distributed last index
					lastIndex = firstIndex + chunksOfFiles - 1;
					
					System.out.println("First Index: "+ firstIndex + " Last Index: " + lastIndex);
					
					ReadWriteJob tempRunnable = 
						new ReadWriteJob(inputReader,outputWriter,firstIndex,lastIndex,jobId);
					
					listOfJobs.add(tempRunnable);
				}
				
			}
		}
		catch(Exception ex) {
			System.out.println("Exception Caught In ThreadHandler");
		}
	}
	
	
	public void threadCreator()
	{
		for ( int jobIndex = 0; jobIndex < listOfJobs.size(); jobIndex++ ){
			
			Thread tempThread = new Thread(listOfJobs.get(jobIndex));
			
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
	
	
	public void threadReadWrite() throws Exception
	{
		runnableManager();

		threadCreator();

		threadStartAndJoin();
	}
	
	public void masterReadWrite(File masterInDir, File masterOutDir)
	{
		Reader masterReader = new Reader(masterInDir);
		Writer masterWriter = new Writer(masterOutDir);
		
		ReadWriteJob masterReadWriteJob = 
				new ReadWriteJob(masterReader, masterWriter, 0, numberOfThreads-1, -1);
		
		Thread masterThread = new Thread(masterReadWriteJob);
		
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