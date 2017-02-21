package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.threads.Counter;

import javax.xml.bind.JAXBException;

/**
 * Created by dmitrii on 20.02.17.
 */
public interface DAOI {

    void insertAllRowDB(Counter counter);
    void selectAllRowDB();

    void saveXML();
    void parseXML();

    void deleteTableData();
    void resetAUTO_INCREMENT();
}
