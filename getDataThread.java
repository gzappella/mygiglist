import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;



public class getDataThread extends Thread {
	String[] bandData;
	spaceList spacesURL;
	spacesEvents spacesEvents;
   
public getDataThread(spaceList sl, spacesEvents el){
	spacesURL = sl;
	spacesEvents = el;
}
   
public void run() {
       while(!spacesURL.isEmpty()){
        try {
        	bandData = spacesURL.get().split("#");
            URL u = new URL(bandData[0]);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            if (huc.getResponseCode() == HttpURLConnection.HTTP_OK) {
         		 
                     InputStreamReader isr = new InputStreamReader(huc.getInputStream());
                     BufferedReader br = new BufferedReader(isr);
                     String buffer,cleanBuffer;
                     Boolean eventArea = false;
                     int step = 0;
                     Event e = new Event();
                    
                    while ((buffer = br.readLine()) != null) {
             
                        if (buffer.contains("Prossimi spettacoli"))
                            eventArea = true;
                        else if(buffer.contains("</div>") && eventArea == true)
                            eventArea = false;
                       
                        cleanBuffer = buffer.replaceAll("\\<.*?\\>", "");
                        buffer = cleanBuffer.replaceAll("\\&.*\\;", "");       
                        cleanBuffer = buffer.trim();
                        
                        
                           
                        if(cleanBuffer.length()!=0 && eventArea == true && !cleanBuffer.equals("( visualizza tutto )") && !cleanBuffer.equals("Prossimi spettacoli"))
                        	
                        	switch(step){
                        	case 0:
                        		e.setDate(cleanBuffer);
                        		step++;
                        		break;
                        	case 1:
                        		e.setTime(cleanBuffer);
                        		step++;
                        		break;
                        	case 2:
                        		e.setLocation(cleanBuffer);
                        		step++;
                        		break;
                        	case 3:
                        		e.setName("<a href='"+u.toString()+"'>"+bandData[1]+"</a>");
                        		e.setCity(cleanBuffer);
                        		step=0;
                        		spacesEvents.add(e);
                        		e = new Event();
                        		break;
                        	}
                     }

               
               
            }else{
                System.out.println("Connection Error");
               
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
       
       }
		
    }