# CostBasisCalculator
Interface to help calculate the cost basis and profit/loss of your cryptocurrencies.

![CostBasisCalculator UI](https://image.prntscr.com/image/ZFB_9-hiQmGvCygmYku8vw.png)

Click on the file chooser to select the Coinbase (Pro) CSV transaction statement.

![CostBasisCalculator FileChooser](https://image.prntscr.com/image/QsviUbI6SZ2RSOWBIewTdA.png)

Program will process the CSV formatted data and generate their overall profit, loss, and net profit & loss, along with a data table detailing each cryptocurrency's cost basis, amount, profit, loss, and net PNL.

![CostBasisCalculator data fed](https://image.prntscr.com/image/rqd1SkblQJWrDWGHSvnOqQ.png)

## Motivation
Coinbase (Pro) does not support these data columns for profit/loss or cost basis. These data are essential for reporting cryptocurrencies capital gains while filing for taxes.

## Technical
Cost basis / PNL are calculated utilizing First-in-first-out (FIFO) queueing strategy. Selling of cryptocurrencies are calculated based on the earliest buy transaction(s).
* Language: Java
* API/Frameworks: Apache POI, JavaFX
