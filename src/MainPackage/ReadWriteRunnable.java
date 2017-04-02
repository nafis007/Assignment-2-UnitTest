package MainPackage;
import java.io.File;
import java.util.ArrayList;

public class ReadWriteRunnable implements Runnable 
{
	Reader inputReader;
	Writer outputWriter;
	
	int firstIndex;
	int lastIndex;
	int threadId;
	
	ArrayList<String> chunkInputStrings;
	
	public ReadWriteRunnable(Reader inReader, Writer outWriter, int first, int last, int id)
	{
		this.inputReader = inReader;
		this.outputWriter = outWriter;
		this.firstIndex = first;
		this.lastIndex = last;
		this.threadId = id;
	}
	
	
	public void insideThreadRead()
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
		
		insideThreadRead();
		
		insideThreadWrite();	
	}
	
}