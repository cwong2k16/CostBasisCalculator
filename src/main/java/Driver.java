import parser.*;

public class Driver {
    /* EXAMPLE
    Coin Fraction	Coin Price		Cost/Sold
    0.011192		4513.1946		50.51167396
    0.011756		4300			50.5508
    -0.02294		4500			103.23
    0.011709		4250			49.76325
    */
    public static void main (String [] args) {
        ExcelParser excelParser = new ExcelParser("src\\main\\resources\\Crypto Tracker.xlsx");
        excelParser.parse();
//		        File excelFile = new File("src\\main\\resources\\Crypto Tracker.xlsx");
//
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(excelFile);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        // we create an XSSF Workbook object for our XLSX Excel File
//        XSSFWorkbook workbook = null;
//        try {
//            workbook = new XSSFWorkbook(fis);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // we get first sheet
//        XSSFSheet sheet = workbook.getSheetAt(0);
//
//        // we iterate on rows
//        Iterator<Row> rowIt = sheet.iterator();
//
//        while(rowIt.hasNext()) {
//            Row row = rowIt.next();
//
//            // iterate on cells for the current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                if(cell.getCellType() == CellType.FORMULA) {
//                    switch(cell.getCachedFormulaResultType()) {
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "; ");
//                            break;
//                        case STRING:
//                            System.out.print(cell.getRichStringCellValue() + "; ");
//                            break;
//                    }
//                }
//                else {
//                    System.out.print(cell.toString() + "; ");
//                }
//            }
//
//            System.out.println();
//        }
//
//        try {
//            workbook.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try {
//            fis.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public static void calculate(double [][] arr){
        double coins = 0;
        double costBasis = 0;
        for(int i = 0; i < arr.length; i++) {
            // negative means sell
            if(arr[i][0] < 0) {
                coins += arr[i][0];
            }
            else {
                double totalCoins = coins + arr[i][0];
                double newCostBasis = (coins/totalCoins * costBasis) +
                        (arr[i][0]/totalCoins * arr[i][1]);
                coins = totalCoins;
                costBasis = newCostBasis;
            }
        }
        System.out.println("Coins: " + coins);
        System.out.println("Cost Basis: " + costBasis);

    }
}
