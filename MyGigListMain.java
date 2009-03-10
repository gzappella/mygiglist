import java.io.*;



public class MyGigListMain {

	final static int maxThreadNumber = 10;

	public static void main (String args[]){
		spaceList spaces = new spaceList("./"+args[0]);
		spacesEvents events = new spacesEvents();
		getDataThread[] threadArray = new getDataThread[maxThreadNumber];

		for(int i=0; i < maxThreadNumber; i++){
			threadArray[i] = new getDataThread(spaces,events);
			threadArray[i].start();	
		}

		try {
			spaces.isDone(maxThreadNumber);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		String content="",page="",strLine;
		Event evt =null;
		while(!events.isEmpty()){
			evt = events.get();
			content+="<li>IL "+ evt.getDate() +" ALLE "+evt.getTime() + "  @  " + evt.getName() + " - <b>"+evt.getLocation()+ ", " + evt.getCity() +"</b></li>";
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream("./"+args[1]))));
			 while ((strLine = br.readLine()) != null)   {
			      page+=strLine;
			    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		page=page.replaceAll("PUTTHECONTENTHERE", content);
		
		
		try {
			PrintStream output = new PrintStream(new FileOutputStream("./"+args[2]));
			output.println(page);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	

	}



}
