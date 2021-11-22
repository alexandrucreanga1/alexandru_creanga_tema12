public class ThreadStatistics extends Thread {

    private int numberOfFullTickets;
    private int numberOfFullVIP;
    private int numberOfFreePasses;
    private int numberOfOneDayPass;
    private int numberOfOneDayVIPPass;
    private int totalNumberOfPeople;

    private FestivalGate gate;
    private Thread generateAttendees;

    public ThreadStatistics(FestivalGate gate, Thread generateAttendees) {
        this.gate = gate;
        this.generateAttendees = generateAttendees;
    }

    @Override
    public void run() {
        boolean shouldStillRun = true;
        while (!generateAttendees.getState().equals(Thread.State.TERMINATED) || shouldStillRun) {
            resetCalculations();
            calculateNumberOfEachTicketType();
            calculateNumberOfPeople();
            showStats();

            if(generateAttendees.getState().equals(Thread.State.TERMINATED)) {
                shouldStillRun = false;
            }

            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void resetCalculations() {
        numberOfOneDayVIPPass = 0;
        numberOfOneDayPass = 0;
        numberOfFullVIP = 0;
        numberOfFullTickets = 0;
        numberOfFreePasses = 0;
        totalNumberOfPeople = 0;
    }

    private void calculateNumberOfEachTicketType() {
        synchronized (this.gate.getValidatedTickets()) {
            for (TicketType ticketType : this.gate.getValidatedTickets()) {
                if (ticketType.equals(TicketType.FULL)) {
                    numberOfFullTickets += 1;
                } else if (ticketType.equals(TicketType.FULL_VIP)) {
                    numberOfFullVIP += 1;
                } else if (ticketType.equals(TicketType.FREE_PASS)) {
                    numberOfFreePasses += 1;
                } else if (ticketType.equals(TicketType.ONE_DAY)) {
                    numberOfOneDayPass += 1;
                } else if (ticketType.equals(TicketType.ONE_DAY_VIP)) {
                    numberOfOneDayVIPPass += 1;
                }
            }
        }
    }


    private void calculateNumberOfPeople() {
        totalNumberOfPeople +=
                numberOfFullTickets +
                        numberOfFullVIP +
                        numberOfFreePasses +
                        numberOfOneDayPass +
                        numberOfOneDayVIPPass
        ;
    }

    public void showStats() {
        System.out.println(
                totalNumberOfPeople + " people have entered; " + "\n" +
                        numberOfFullTickets + " have full tickets, " + "\n" +
                        numberOfFreePasses + " have free passes, " + "\n" +
                        numberOfFullVIP + " have full vip passes, " + "\n" +
                        numberOfOneDayPass + " have one day passes, " + "\n" +
                        numberOfOneDayVIPPass + " have one day vip passes."
        );
        System.out.println("-----------------------------------------------------------"+ "\n" +
                           "-----------------------------------------------------------");
    }

}
