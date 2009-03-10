import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class spaceList {
	private ArrayList<String> spacesURL = new ArrayList<String>();
	private String strLine;
	private int endedThreads = 0;
	
	public spaceList(String configFile) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(configFile))));
			 while ((strLine = br.readLine()) != null)   {
			      this.add(strLine);
			    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public synchronized boolean isEmpty(){
		if(spacesURL.isEmpty()){
			endedThreads++;
			notifyAll();
			return true;
		}else{
			return false;
		}
	}
	
	public synchronized void add(String s){
		
			spacesURL.add(s);
			notifyAll();
	}



	public synchronized String get(){
		String s = null;
		
		
		try {
			while(spacesURL.isEmpty()){
				wait();	
			
			}
			s = spacesURL.get(0);
			spacesURL.remove(0);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		return s;

	} 
	
	public synchronized void isDone(int threadsNumber) throws InterruptedException{
			while(threadsNumber != endedThreads){
				wait();
			}
			
		}
		
	}

