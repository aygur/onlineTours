package com.naraikin.onlineturs.parser;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by dmitrii on 19.02.17.
 */
public interface Parser {
    Object getObject(File file, Class c) throws JAXBException;
    void saveObject(File file, Object o) throws JAXBException;
}
