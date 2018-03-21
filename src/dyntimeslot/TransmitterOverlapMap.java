package dyntimeslot;

import javafx.util.Pair;
import java.util.HashMap;

public class TransmitterOverlapMap {
    private HashMap<Pair<String,String>, Boolean> OverlapMap = new HashMap<Pair<String,String>, Boolean>();

    public void AddOverlappingTransmitters(String T1Name, String T2Name) {
        Pair p = new Pair(T1Name,T2Name);
        Pair pInvers = new Pair(T2Name,T1Name);
        OverlapMap.put(p,true);
        OverlapMap.put(pInvers,true);
    }

    public boolean AreTransmittersOverlapping(String T1Name, String T2Name) {
        Pair p = new Pair(T1Name,T2Name);
        if (OverlapMap.get(p) == null) {
            return false;
        }
        return OverlapMap.get(p);
    }

}
