import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class FestivalGate {
    private List<TicketType> validatedTickets = Collections.synchronizedList(new ArrayList<>());
    public List<TicketType> getValidatedTickets() {
        return validatedTickets;
    }
}
