package dyntimeslot;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wilke on 21.03.2018.
 */
public class TransmitItemBundle {
    public Queue<TransmitItem> TransmitItemQueue = new LinkedList<>();
    private double StartTime = 0;
    private double EndTime = 0;
    private double Duration = 0;

    TransmitItemBundle (double StartTime) {
        this.StartTime = StartTime;
    }

    public boolean isEmpty() {
        return this.TransmitItemQueue.isEmpty();
    }

    public void addTransmitItemToBundle (TransmitItem TransmitItem) {
        this.TransmitItemQueue.add(TransmitItem);
        this.Duration = this.Duration + TransmitItem.getTXTimeForTransmission();
        this.EndTime = this.StartTime + this.Duration;
    }

    public double getDuration () {
        return this.Duration;
    }

    public double getStartTime () {
        return this.StartTime;
    }

    public double getEndTime () {
        return this.EndTime;
    }

    public int size() {
        return this.TransmitItemQueue.size();
    }

    public void setTimes (double StartTime) {
        this.StartTime = StartTime;
        this.EndTime = this.StartTime + this.Duration;
    }

    public TransmitItem poll() {
        return this.TransmitItemQueue.poll();
    }
}
