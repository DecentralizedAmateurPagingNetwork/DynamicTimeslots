package dyntimeslot;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Transmitter {
    // Config
    private static int ItemsToSendAtOnce = 3;

    Queue<TransmitItemBundle> TransmitItemBundleQueue = new LinkedList<>();
    TransmitterPriorityComparator comp = new TransmitterPriorityComparator();
    PriorityQueue<TransmitItem> TransmitterPriorityQueue = new PriorityQueue<TransmitItem>(comp);

    private String TransmitterName;
    private double AirTime = 0;
    private double MinTimeforNextTransmit = 0;
    private boolean isTransmitting = false;


    public Transmitter(String TransmitterName) {
        this.TransmitterName = TransmitterName;
    }

    void addNewTransmitItem (String Message, int priority, int RIC, int SubRIC) {
        this.addNewTransmitItem(Message,priority,RIC,SubRIC, 1200);
    }

    void addNewTransmitItem (String Message, int priority, int RIC, int SubRIC, int Speed) {
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

    public boolean isTransmitItemBundleQueueEmpty () {
        return this.TransmitItemBundleQueue.isEmpty();
    }

    public boolean hasTransmitItemsWaiting (int Priority) {
        for (TransmitItem TI : this.TransmitterPriorityQueue) {
            if (TI.priority == Priority) {
                return true;
            }
        }
        return false;
    }

    public double getAirTime() {
        return this.AirTime;
    }

    public boolean isAllowedToSend(double Time) {
        return (this.MinTimeforNextTransmit < Time);
    }

    public int QueueBundleToSend(double Time) {
        int ItemsSent = 0;
        if (!this.isTransmitterPriorityQueueEmpty()) {
            TransmitItemBundle TIB = new TransmitItemBundle(Time);

            while ((!this.isTransmitterPriorityQueueEmpty()) && (ItemsSent < this.ItemsToSendAtOnce)) {
                // Get TransmitItem from Prio Queue
                TransmitItem T = this.TransmitterPriorityQueue.poll();
                TIB.addTransmitItemToBundle(T);
                ItemsSent++;
            }
            this.TransmitItemBundleQueue.add(TIB);
            this.MinTimeforNextTransmit = TIB.getEndTime();
        }
        return ItemsSent;
    }
    public void BlockTransmitterUntil (double Time) {
        this.MinTimeforNextTransmit = Time;
    }

    public double TransmittingUntil (){
        return this.MinTimeforNextTransmit;
    }

    public void HelperProcessTransmitter (double Time) {
        if (!TransmitItemBundleQueue.isEmpty()) {
            if ((Time >= this.TransmitItemBundleQueue.peek().getStartTime()) && (!this.isTransmitting)) {
                this.isTransmitting = true;
                this.AirTime = this.AirTime + this.TransmitItemBundleQueue.peek().getDuration();
                System.out.print("START Transmitter ");
                System.out.print(this.TransmitterName);
                System.out.print(" at ");
                System.out.print(Time);
                System.out.println("");
            }
            if (Time >= this.TransmitItemBundleQueue.peek().getEndTime()) {
                this.isTransmitting = false;

                // Remove TransmitItemBundle from Queue
                TransmitItemBundle TIB = this.TransmitItemBundleQueue.poll();
                System.out.println("Transmitted Messages");
                while (!TIB.isEmpty()) {
                    TIB.poll().HelperPrint();
                }
                // Remove TransmitItemBundle from Queue
                TransmitItemBundleQueue.poll();
                System.out.print("STOP Transmitter ");
                System.out.print(this.TransmitterName);
                System.out.print(" at ");
                System.out.print(Time);
                System.out.print("  AirTime: ");
                System.out.println(this.AirTime);
            }
        }
    }
}
