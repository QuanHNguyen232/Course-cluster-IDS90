# Course-cluster-IDS90
This project is a combination between Computer Science II (CS 112) and Accounting class (MGT 155), but most of the parts of this project are based heavily on CS.

---

#### Table of content

1. [Classes](#Classes)
2. [APIs and Libraries](#APIs-and-Libraries)
3. [How it works](#How-it-works)

---

## Classes

- MainProject.java: main class with javaFX for GUI and interaction with users
- Draw_BarChart_2 class: inner class of MainProject.java. It uses data from json file and visualize it as barchart and export the chart as image
- InOutJSON_1.java: to save and read data from json file using json-simple and jackson
- WritePDF.java: export all data, including the chart image as a PDF file using PDFBox
- CrawlWeb.java: get realtime exchange rate of some currencies from [ExchangeRates](https://www.exchangerates.org.uk/US-Dollar-USD-currency-table.html)
- Month.java: the most important element. It contains record of revenues and expenses of each month

## APIs and Libraries

For this project, I use these following APIs and Libraries:
- Read/Write json: JSON.simple ([download](https://search.maven.org/artifact/com.googlecode.json-simple/json-simple/1.1.1/bundle)) and Jackson ([download](https://search.maven.org/search?q=g:com.fasterxml.jackson.core)) (download all 3 Jackson jar files ver. 2.13.0: core, databind, and annotations)
- Crawl web: Jsoup ([download](https://search.maven.org/artifact/org.jsoup/jsoup/1.14.3/jar))
- Read/Write PDF: PDFBox ([download](https://search.maven.org/artifact/org.apache.pdfbox/pdfbox-app/3.0.0-alpha2/bundle))

## How it works

1. Run the MainProject.java. If the data file as json exists, InOutJSON_1.java will read and import data into MainProject
2. Input your revenue/expense or add a new month
3. The system will save your data into json file
4. Click export button in main class and choose currency
5. The system will get data from json, visualize it as bar chart and export report under PDF file 
