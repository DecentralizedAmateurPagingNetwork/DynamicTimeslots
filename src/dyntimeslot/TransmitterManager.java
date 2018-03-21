package dyntimeslot;

import java.util.ArrayList;



public class TransmitterManager {
    private ArrayList<Transmitter> TransmitterList = new ArrayList<>();

    public void addTransmitter (String TransmitterName) {
        Transmitter T = new Transmitter(TransmitterName);
        this.TransmitterList.add(T);
    }

    public void addTransmitItemToTransmitter (String TransmitterName, String Message, int priority, int RIC, int SubRIC) {

        for (Transmitter T : this.TransmitterList) {
            if (T.getTransmitterName() == TransmitterName) {
                T.addTransmitItem(Message, priority, RIC, SubRIC);
            }
            break;
        }
    }
}
