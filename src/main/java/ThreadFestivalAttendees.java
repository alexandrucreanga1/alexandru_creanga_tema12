public class ThreadFestivalAttendees extends Thread{
    TicketType ticketType;
    FestivalGate festivalGate;

    public ThreadFestivalAttendees (TicketType ticketType, FestivalGate festivalGate) {
        this.ticketType = ticketType;
        this.festivalGate=festivalGate;

    }


    @Override
    public void run() {
        this.festivalGate.getValidatedTickets().add(ticketType);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
