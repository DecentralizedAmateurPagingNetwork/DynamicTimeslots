package dyntimeslot;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Transmitter {

    Queue<TransmitItem> QueueToTransmit = new LinkedList<>();
    TransmitterPriorityComparator comp = new TransmitterPriorityComparator();
    PriorityQueue<TransmitItem> TransmitterPriorityQueue = new PriorityQueue<TransmitItem>(comp);
    private String TransmitterName;
    private double AirTime = 0;

    public Transmitter(String TransmitterName) {
        this.TransmitterName = TransmitterName;
    }

    void addTransmitItem (String Message, int priority, int RIC, int SubRIC) {
        this.addTransmitItem(Message,priority,RIC,SubRIC, 1200);
    }

    void addTransmitItem (String Message, int priority, int RIC, int SubRIC, int Speed) {
        TransmitItem NewTransmitItem = new TransmitItem(Message,priority,RIC,SubRIC,Speed);
        TransmitterPriorityQueue.add(NewTransmitItem);
    }

    public int getHeadPriority () {
        if (!this.TransmitterPriorityQueue.isEmpty()) {
            return this.TransmitterPriorityQueue.peek().priority;
        } else {
            return -1;
        }
    }

    public String getTransmitterName() {
        return this.TransmitterName;
    }

    public boolean isTransmitterPriorityQueueEmpty () {
        return this.TransmitterPriorityQueue.isEmpty();
    }

    public boolean isQueueToTransmitEmpty () {
        return this.QueueToTransmit.isEmpty();
    }

    public void SendNextItem() {
        if (!this.isTransmitterPriorityQueueEmpty()) {
            TransmitItem T = this.TransmitterPriorityQueue.poll();
            this.QueueToTransmit.add(T);
        }
    }

    public void NextItemSentbyTransmitter() {
        if (!this.QueueToTransmit.isEmpty()) {
            TransmitItem T = this.QueueToTransmit.poll();
            System.out.print("Sent out: ");
            System.out.println(T.Message);
            System.out.print("Prio: ");
            System.out.print(T.priority);
        }
    }

    public void printTransmitItems () {
        while ( !this.TransmitterPriorityQueue.isEmpty() ) {
            TransmitItem T = this.TransmitterPriorityQueue.poll();
            System.out.print(String.valueOf(T.uuid));
            System.out.print("  ");
            System.out.print(T.Message);
            System.out.print("  ");
            System.out.println(T.priority);
        }
    }
}
