package com.naraikin;

import com.naraikin.onlineturs.tables.*;
import com.naraikin.onlineturs.threads.InsertTableThread;
import com.naraikin.onlineturs.threads.ReaderTable;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<DAOI> list = new ArrayList<>();
        list.add( new TableClient());
        list.add(new TableVoucherStatus());
        list.add(new TableTravelVoucher());
        list.add( new TableTour());

        for (DAOI daoi: list){
            new InsertTableThread(daoi);
        }
/*
        for (DAOI daoi: list){
            new ReaderTable(daoi);
        }

*/

    }
}
