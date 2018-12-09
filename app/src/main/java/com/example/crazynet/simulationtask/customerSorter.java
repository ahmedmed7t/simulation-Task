package com.example.crazynet.simulationtask;

import java.util.Comparator;

/**
 * Created by Medhat on 09/12/2018.
 */

public class customerSorter implements Comparator<customer> {
    @Override
    public int compare(customer o1, customer o2) {
        int returnVal = 0;

        if(o1.getCome() < o2.getCome()){
            returnVal =  -1;
        }else if(o1.getCome() > o2.getCome()){
            returnVal =  1;
        }else if(o1.getCome() == o2.getCome()){
            returnVal =  0;
        }
        return returnVal;
    }
}
