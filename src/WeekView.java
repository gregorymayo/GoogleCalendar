import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Container;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.time.LocalDate;
/**
 * WeekView.java
 * @author Tyler Lorenzi, Gregory Mayo, Ealrada Piroyan
 * @version 1.0 08/02/19
 */
public class WeekView implements View{
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
            textArea.setText("No Events this week!");
        }
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            String eventsForTheWeek = "";
            int i = 0;
            for(Event event : events) {
            	if(i == 0) {
            		eventsForTheWeek = "                                           " + formatter.format(event.getDate()) + "\n";
            		i++;
            	}
                eventsForTheWeek = eventsForTheWeek + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " +
                            event.getName() + "\n";
            }
            eventsForTheWeek = eventsForTheWeek + "\n";
            textArea.setText(eventsForTheWeek);
        }
    */
    }
	/*
	 * A mutator function to call the view of the day
	 * @param m, the CalendarModel 
	 * @param d, the selected date
	 */
	public void mutate(CalendarModel m, LocalDate date) {
		// TODO Auto-generated method stubmodel = m;
		model = m;
		LocalDate start = date;
		LocalDate end = date;
		DayOfWeek day = date.getDayOfWeek();
		int d = day.getValue();
		if (d == 7) 
			d = 0;   
        start = start.minusDays(d);	
		end = end.plusDays(7 - d - 1);
		model.getEventsWeek(start,end);
	}
}


