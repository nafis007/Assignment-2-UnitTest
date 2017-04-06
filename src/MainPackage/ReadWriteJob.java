package MainPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ReadWriteJob implements Runnable 
{
	Reader inputReader;
	Writer outputWriter;
	
	int firstIndex;
	int lastIndex;
	int threadId;
	
	ArrayList<String> chunkInputStrings;
	
	public ReadWriteJob(Reader inReader, Writer outWriter, int first, int last, int id)
	{
		this.inputReader = inReader;
		this.outputWriter = outWriter;
		this.firstIndex = first;
		this.lastIndex = last;
		this.threadId = id;
	}
	
	
	public void insideThreadRead() throws ArrayIndexOutOfBoundsException, FileNotFoundException
	{
		System.out.println("ReaderWriter Thread running: " + threadId);
		
		chunkInputStrings = inputReader.read(firstIndex, lastIndex);
		
		//inputReader.readerTestPrint(); //debug print
	
	}
	
	public void insideThreadWrite(){
		
		outputWriter.write(chunkInputStrings,threadId);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			insideThreadRead();
		} 
		catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insideThreadWrite();	
	}
	
}