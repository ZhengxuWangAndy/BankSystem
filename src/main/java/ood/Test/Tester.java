package ood.Test;

import ood.Person.Manager;
import ood.utils.DBReader;

import java.util.LinkedHashMap;
import java.util.UUID;

public class Tester {
    public Tester() {
        Manager m = new Manager(UUID.fromString("b480b4fc-88e3-4cc7-bee4-6a61857fbb71"),"admin1","admin1");
        m.getDailyTransactions();

//        DBReader db = new DBReader();
//
//        // get one row
//        db.getData("CustomerInfo","username","test1");
//
//        // get whole table
//        db.getTable("ManagerInfo");
//
//        // change data
//        db.changeData("Currencies","currencyName","CNY","cid","acbcf47f-480d-4a74-906a-affcb891dfb8");
//
//
//        // write a new row
//        LinkedHashMap<String, String> data = new LinkedHashMap<>();
//        data.put("cid","1");
//        data.put("currencyName","2");
//        data.put("exchangeRate","1");
//        db.addData("Currencies",data);



    }
}
