package com.naraikin.onlineturs.threads;

import com.naraikin.onlineturs.tables.DAOI;

import javax.xml.bind.JAXBException;

/**
 * Created by dmitrii on 21.02.17.
 */
public class ReaderTable extends Thread {

    private DAOI daoi;

    public ReaderTable(DAOI daoi){
        this.daoi = daoi;
        this.start();
    }
    @Override
    public void run() {
        daoi.selectAllRowDB();
        try {
            daoi.saveXML();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
