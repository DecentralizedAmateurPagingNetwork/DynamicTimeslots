package dyntimeslot;

import java.util.Comparator;
import java.util.UUID;

/*
    Priority:
    1: Highest
    5: Lowest
*/

public class TransmitItem {
    public String Message;
    public int priority;
    public int RIC;
    public int SubRIC;
    public int Speed;
    public boolean Transmitted = false;
    public boolean SentToTransmitter = false;
    public boolean ArrivedAtTransmiter = false;
    public UUID uuid;

    private double GuardInterval = 400;
    private double PreambleTime = 300000;

    public TransmitItem(String Message, int priority, int RIC, int SubRic, int Speed) {
        this.Message = Message;
        this.priority = priority;
        this.RIC = RIC;
        this.SubRIC = SubRic;
        this.Speed = Speed;
        this.uuid = UUID.randomUUID();
    }

    public TransmitItem(String Message, int priority, int RIC, int SubRic) {
        this (Message,priority,RIC,SubRic,1200);
    }

    public double getTXTimeForTransmission () {
        double TransmissionDuration = (this.Message.length() * 2000) + this.PreambleTime + this.GuardInterval;
        return (TransmissionDuration);
    }
}

// Implement Comparator for priority in TransmitItems
class TransmitterPriorityComparator implements Comparator<TransmitItem> {
    @Override public int compare (TransmitItem t1, TransmitItem t2) {
        if (t1.priority == t2.priority)
            return 0;
        if (t1.priority < t2.priority)
            return -1;
        return 1;
    }
}
