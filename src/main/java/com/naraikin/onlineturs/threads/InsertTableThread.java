package com.naraikin.onlineturs.threads;

import com.naraikin.onlineturs.tables.DAOI;

import javax.xml.bind.JAXBException;

/**
 * Created by dmitrii on 21.02.17.
 */
public class InsertTableThread extends Thread {

    private DAOI daoi;
    private Counter counter;

    public InsertTableThread(DAOI daoi, Counter cnt){
        this.daoi = daoi;
        this.counter = cnt;
    }
    @Override
    public void run() {
        daoi.parseXML();
        daoi.resetAUTO_INCREMENT();
        daoi.insertAllRowDB(this.counter);
    }
}
