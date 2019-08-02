import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Container;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
/**
 * DayView.java
 * @author Tyler Lorenzi, Gregory Mayo, Ealrada Piroyan
 * @version 1.0 08/02/19
 */
public class DayView implements View{
	private CalendarModel model;
    public void layoutText(Container c, String text){
    /*
        JTextArea textArea =  (JTextArea)c;
        textArea.setText(text);
    */
    }
    public void layoutText(Container c, ArrayList<Event> events){
        /*
    	JTextArea textArea =  (JTextArea)c;
        if (events.size()==0){
            textArea.setText("No Events today!");
        }
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            String eventsForTheDay = "";
            int i = 0;
            for(Event event : events) {
            	if(i == 0) {
            		eventsForTheDay = "                                           " + formatter.format(event.getDate()) + "\n";
            		i++;
            	}
                eventsForTheDay = eventsForTheDay + "                                        "+
                        formatter.format(event.getDate())+" "
                        + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " +
                            event.getName() + "\n";
            }
            textArea.setText(eventsForTheDay);
        }
        */
    }
	/*
	 * A mutator function to call the view of the day
	 * @param m, the CalendarModel 
	 * @param d, the selected date
	 */
	public void mutate(CalendarModel m, LocalDate d) {
		// TODO Auto-generated method stub
		model = m;
		model.getEventsDay(d);
	}
}
