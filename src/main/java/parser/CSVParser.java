package parser;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CSVParser extends Parser {
    @Override
    public HashMap<String, ArrayList<ArrayList<Double>>> parse(String filePath) {
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
            double size = Double.parseDouble(record.get("size"));
            double price = Double.parseDouble(record.get("price"));
            double amount = size*price;
            String unit = record.get("size unit");
            String side = record.get("side");

            System.out.println(side + " " + size + " of " + unit + " at " + price + ": " + amount);
        }

        return sheetMap;
    }
}
