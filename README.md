# Rabobank_BE_With_SpringBoot

**Pre requisites:**
- java-1.8,
- maven-3.3.3 or higher

download code base from https://github.com/maheshpvd/Rabobank_BE_SpringBoot
- Redirect to **Rabobank** folder
- Run **mvn clean install** command

redirect to **target** folder and 
- Run **java -jar Rabobank.jar** command

GotoPostman/any supporting tool:
- Method: **POST**
- URI: http:localhost:8080/rabobank
- formdata: key: **file**, type: **file**, value: select **records.csv/records.xml** file present in **src/main/resources** folder of project

- send request --> Response will be in json format if any failure records present, else will get **Statements successfully validated.**
- Sample response for failed records
- **[
    {
        "transactionReference": 167875,
        "description": "Tickets from Erik de Vries"
    }
]**

- Logs will be created under "logs/rabobank/rabobank.log"
