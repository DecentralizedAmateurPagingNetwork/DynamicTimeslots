package dyntimeslot;

public class Main {

    public static void main(String[] args) {


        // Add some transmitters
        TransmitterManager TransmitterManager = new TransmitterManager();
        TransmitterManager.addTransmitter("db0abc");
        TransmitterManager.addTransmitter("db0def");
        TransmitterManager.addTransmitter("db0ghi");

        TransmitterManager.addTransmitItemToTransmitter("db0abc", "M1", 1 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0abc", "M2", 2 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0abc", "M3", 3 ,1,1);

        TransmitterManager.addTransmitItemToTransmitter("db0def", "M4", 4 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0def", "M5", 4 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0def", "M6", 5 ,1,1);

        TransmitterManager.addTransmitItemToTransmitter("db0ghi", "M7", 3 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0ghi", "M8", 3 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0ghi", "M9", 3 ,1,1);

        for (double Time = 0; Time < 10000; Time++) {
            TransmitterManager.Scheduler(Time);
            TransmitterManager.HelperProcessTransmitters(Time);
        }
        System.out.println("Done");
/*
        System.out.println("Hello World!");

        System.out.println(Trans.getHeadPriority());
        Trans.printTransmitItems();
*/
    }
}
