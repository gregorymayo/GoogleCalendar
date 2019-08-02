import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Container;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
/**
 * View.java
 * @author Tyler Lorenzi, Gregory Mayo, Ealrada Piroyan
 * @version 1.0 08/02/19
 */
public interface View{
	public void layoutText(Container c,String text);
	public void layoutText(Container c,ArrayList<Event> events);
	public void mutate(CalendarModel m, LocalDate d);
}
