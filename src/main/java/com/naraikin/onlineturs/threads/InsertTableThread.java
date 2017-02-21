package com.naraikin.onlineturs.threads;

import com.naraikin.onlineturs.tables.DAOI;

import javax.xml.bind.JAXBException;

/**
 * Created by dmitrii on 21.02.17.
 */
public class InsertTableThread extends Thread {

    private DAOI daoi;

    public InsertTableThread(DAOI daoi){
        this.daoi = daoi;
        this.start();
    }
    @Override
    public void run() {

        try {
            daoi.parseXML();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        daoi.resetAUTO_INCREMENT();
        daoi.insertAllRowDB();
    }
}
