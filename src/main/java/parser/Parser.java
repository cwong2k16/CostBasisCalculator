package parser;

import object.*;

import java.io.*;
import java.util.*;

public abstract class Parser {
    protected FileInputStream fis = null;
    protected ArrayList<RecordData> transactions = new ArrayList<>();

    public abstract ArrayList<RecordData> parse(String filePath);
}
