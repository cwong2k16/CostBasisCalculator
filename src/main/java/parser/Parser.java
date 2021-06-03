package parser;

import java.io.*;
import java.util.*;

public abstract class Parser {
    protected FileInputStream fis = null;
    protected HashMap<String, ArrayList<ArrayList<Double>>> sheetMap = new HashMap<>();

    public abstract HashMap<String, ArrayList<ArrayList<Double>>> parse(String filePath);
}
