import java.util.ArrayList;



public class spacesEvents {
	private ArrayList<Event> eventsList = new ArrayList<Event>();
	
	
	public synchronized boolean isEmpty(){
		return eventsList.isEmpty();
	}
	
	
	public synchronized void add(Event e){
			eventsList.add(e);
			notifyAll();
	}



	public synchronized Event get(){
		Event e = null;
		
		try {
			while(eventsList.isEmpty()){
				wait();	
			}
			
			e = eventsList.get(0);
			eventsList.remove(e);

		} 
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		return e;

	} 
	
}
