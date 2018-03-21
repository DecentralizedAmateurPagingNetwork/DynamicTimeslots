package dyntimeslot;

import sun.nio.ch.Net;

import java.util.ArrayList;



public class TransmitterManager {
    private ArrayList<Transmitter> TransmitterList = new ArrayList<>();
    private TransmitterOverlapMap TransmitterOverlapMap = new TransmitterOverlapMap();


    public TransmitterManager() {
        // Create Overlap Map
        this.TransmitterOverlapMap.AddOverlappingTransmitters("db0abc", "db0def");
        this.TransmitterOverlapMap.AddOverlappingTransmitters("db0abc", "db0ghi");

    /*      //Test geht
            if (TransmitterOverlapMap.AreTransmittersOverlapping("db0def", "db0ghi")) {
                System.out.println("Überlappend");
            } else {
                System.out.println("Nicht überlappend");
            }
    */
    }


    public void addTransmitter (String TransmitterName) {
        Transmitter T = new Transmitter(TransmitterName);
        this.TransmitterList.add(T);
    }

    public void addTransmitItemToTransmitter (String TransmitterName, String Message, int priority, int RIC, int SubRIC) {

        for (Transmitter T : this.TransmitterList) {
            if (T.getTransmitterName().equals(TransmitterName)) {
                T.addNewTransmitItem(Message, priority, RIC, SubRIC);
                break;
            }
        }
    }

    public void Scheduler(double Time) {
        for (int Priority = 1; Priority <= 5; Priority++) {
            Transmitter NextTransmitterToSend = findNextTransmitterWithItemWaiting(Priority, Time);
            if (NextTransmitterToSend != null) {
                NextTransmitterToSend.QueueBundleToSend(Time);
                for (Transmitter T: this.TransmitterList) {
                    if (TransmitterOverlapMap.AreTransmittersOverlapping(NextTransmitterToSend.getTransmitterName(), T.getTransmitterName())) {
                        T.BlockTransmitterUntil(NextTransmitterToSend.TransmittingUntil());
                    }
                }
            }
        }
    }


    public void HelperProcessTransmitters(double Time) {
        for (Transmitter T: TransmitterList) {
            T.HelperProcessTransmitter(Time);
        }

    }
    private Transmitter findNextTransmitterWithItemWaiting (int Priority, double Time) {
        double MinAirTime = -1;
        boolean foundOne = false;
        Transmitter TransmitterWithMinAirTime = null;

        if (!TransmitterList.isEmpty()) {
            int i = 0;
            while (i < TransmitterList.size()) {
                Transmitter T = TransmitterList.get(i);
                if (T.hasTransmitItemsWaiting(Priority)) {
                    if (!foundOne) {
                        if (T.isAllowedToSend(Time)) {
                            TransmitterWithMinAirTime = T;
                            MinAirTime = T.getAirTime();
                            foundOne = true;
                        }
                    } else {
                        if (T.getAirTime() < MinAirTime) {
                            if (T.isAllowedToSend(Time)) {
                                TransmitterWithMinAirTime = T;
                                MinAirTime = T.getAirTime();
                            }
                        }
                    }
                }
                i++;
            }
        }
        return TransmitterWithMinAirTime;
    }
}
