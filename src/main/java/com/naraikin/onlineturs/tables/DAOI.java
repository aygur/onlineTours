package com.naraikin.onlineturs.tables;

import javax.xml.bind.JAXBException;

/**
 * Created by dmitrii on 20.02.17.
 */
public interface DAOI {

    void insertAllRowDB();
    void selectAllRowDB();

    void saveXML() throws JAXBException;
    void parseXML() throws JAXBException;

    void resetAUTO_INCREMENT();
}
