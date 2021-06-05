package parser;

import object.*;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CSVParser extends Parser {
    @Override
    public ArrayList<RecordData> parse(String filePath) {
        Reader in = null;
        Iterable<CSVRecord> records = null;

        try {
            in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            records = CSVFormat.DEFAULT
                    .withHeader("portfolio", "trade id", "product", "side", "created at", "size", "size unit", "price", "fee", "total", "price/fee/total unit")
                    .withFirstRecordAsHeader().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord record: records) {
            String side = record.get("side"); // buy or sell
            String unit = record.get("size unit"); // BTC, ETH, etc...
            double size = Double.parseDouble(record.get("size"));
            double price = Double.parseDouble(record.get("price"));
            price = side.equals("SELL") ? price * -1 : price;
            double amount = size*price;

            RecordData recordData = new RecordData(unit, size, price, amount);

            transactions.add(recordData);
        }
        return transactions;
    }
}
