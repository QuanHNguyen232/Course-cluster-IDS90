# Course-cluster-IDS90
This project is a combination between Computer Science II (CS 112) and Accounting class (MGT 155), but most of the parts of this project are based heavily on CS.

---

#### Table of content

1. [Classes](#Classes)
2. [How it works](#How-it-works)

---

## Classes

- MainProject.java: main class with javaFX for GUI and interaction with users
- Draw_BarChart_2 class: inner class of MainProject.java. It uses data from json file and visualize it as barchart and export the chart as image
- InOutJSON_1.java: to save and read data from json file using json-simple and jackson
- WritePDF.java: export all data, including the chart image as a PDF file using PDFBox
- CrawlWeb.java: get realtime exchange rate of some currencies from https://www.exchangerates.org.uk/US-Dollar-USD-currency-table.html
- Month.java: the most important element. It contains record of revenues and expenses of each month

## How it works

1. Run the MainProject.java. If the data file as json exists, InOutJSON_1.java will read and import data into MainProject
2. Input your revenue/expense or add a new month
3. The system will save your data into json file
4. Click export button in main class and choose currency
5. The system will get data from json, visualize it as bar chart and export report under PDF file 
