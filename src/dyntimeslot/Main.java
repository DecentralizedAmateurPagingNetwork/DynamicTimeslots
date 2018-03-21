package dyntimeslot;

public class Main {

    public static void main(String[] args) {

        // Create Overlap Map
	    TransmitterOverlapMap TransmitterOverlapMap = new TransmitterOverlapMap();
        TransmitterOverlapMap.AddOverlappingTransmitters("db0abc", "db0def");
        TransmitterOverlapMap.AddOverlappingTransmitters("db0abc", "db0ghi");

/*      //Test geht
        if (TransmitterOverlapMap.AreTransmittersOverlapping("db0def", "db0ghi")) {
            System.out.println("Überlappend");
        } else {
            System.out.println("Nicht überlappend");
        }
*/

        // Add some transmitters
        TransmitterManager TransmitterManager = new TransmitterManager();
        TransmitterManager.addTransmitter("db0abc");
        TransmitterManager.addTransmitter("db0def");
        TransmitterManager.addTransmitter("db0ghi");

        TransmitterManager.addTransmitItemToTransmitter("db0abc", "MessagePrio1", 1 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0abc", "MessagePrio2", 2 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0abc", "MessagePrio3", 3 ,1,1);

        TransmitterManager.addTransmitItemToTransmitter("db0def", "MessagePrio4", 4 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0def", "MessagePrio4", 4 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0def", "MessagePrio5", 5 ,1,1);

        TransmitterManager.addTransmitItemToTransmitter("db0ghi", "MessagePrio3", 3 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0ghi", "MessagePrio3", 3 ,1,1);
        TransmitterManager.addTransmitItemToTransmitter("db0ghi", "MessagePrio3", 3 ,1,1);


/*
        System.out.println("Hello World!");

        System.out.println(Trans.getHeadPriority());
        Trans.printTransmitItems();
*/
    }
}
