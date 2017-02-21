package com.naraikin;

import com.naraikin.onlineturs.tables.*;
import com.naraikin.onlineturs.threads.Counter;
import com.naraikin.onlineturs.threads.InsertTableThread;
import com.naraikin.onlineturs.threads.SelectTableToXMLThread;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }
    public static void main(String[] args) {


        List<DAOI> list = new ArrayList<>();
        list.add(new TableTravelVoucher());
        list.add( new TableClient());
       list.add(new TableVoucherStatus());
        list.add( new TableTour());

/*
       for (DAOI daoi: list){
           try {
               new SelectTableToXMLThread(daoi).join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }*/

        /**
         * Очистка таблицы и
         */
        for(DAOI daoi: list){
           daoi.deleteTableData();
           daoi.resetAUTO_INCREMENT();
       }

         Counter counter = new Counter();

        /**
         * Загрузка в базу данных из XML
         */
        List<Thread> threads = new ArrayList<>();
        for (DAOI daoi: list){
            Thread t = new InsertTableThread(daoi, counter);
            threads.add(t);
        }
        for (Thread t : threads){
            t.start();
        }







    }
}
