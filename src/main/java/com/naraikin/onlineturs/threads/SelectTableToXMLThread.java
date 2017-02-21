package com.naraikin.onlineturs.threads;

import com.naraikin.onlineturs.tables.DAOI;

import javax.xml.bind.JAXBException;

/**
 * Created by dmitrii on 21.02.17.
 */
public class SelectTableToXMLThread extends Thread {

    private DAOI daoi;

    public SelectTableToXMLThread(DAOI daoi){
        this.daoi = daoi;
        this.start();
    }
    @Override
    public void run() {
        daoi.selectAllRowDB();
        daoi.saveXML();
    }
}
