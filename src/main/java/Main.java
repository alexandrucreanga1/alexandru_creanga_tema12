public class Main {



    public static void main(String[] args) {

        FestivalGate gate = new FestivalGate();
        final ThreadFestivalAttendees[] festivalAttendee = new ThreadFestivalAttendees[1];
        final TicketType[] ticketType = new TicketType[1];
        Thread generateAttendees = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                ticketType[0] = TicketType.randomTicket();
                festivalAttendee[0] = new ThreadFestivalAttendees(ticketType[0], gate);
                festivalAttendee[0].start();
                try {
                    festivalAttendee[0].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        ThreadStatistics statsThread = new ThreadStatistics(gate, generateAttendees);
        statsThread.start();
        generateAttendees.start();


    }

}
