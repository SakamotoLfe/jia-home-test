# Jia home test.

This project contains the test answer from the company Jia.
The test consist in creating an a mini-app to generate projections of fees and installments for a given loan application. Through this app, one will be able to query the following information about a loan application.

## App Key points:

1. Applicable fees and the dates they will be incurred
2. Applicable installments and amounts expected for each installment
The app will have two REST endpoints;
1. Fee projections — returns all applicable fees for a given loan
2. Installment projections — returns all applicable installments for a given loan.

Write the app as a web service following REST API design principles. You can use either the
Scala/Play or Java/SpringBoot

## Constrains:

- Supported loan durations
    - Weekly; 1 to 4 weeks. 1 week should contain 7 days
    - Monthly; 1 to 12 months. Assume 1 month contains 30 days
- Supported installment frequencies
    - Weekly. This is only applicable to weekly loans
    - Monthly. This is applicable to weekly and monthly loans
    - The formula for calculating installment is as follows;
        - (principal evenly spread across all installments) + all incurred fees
- Weekly loan fees
    - An interest of 1% of the principal is incurred every week
    - A service fee of 0.5% of the principal with a cap of $50 is incurred every 2 weeks.
For example; if the principal is $20,000, the fee charged will be $50, and if it is
$2,000 it will be $10
- Monthly loan fees
    - An interest of 4% of the principal is incurred every month
    - A service fee of 0.5% of the principal with a cap of $100 is incurred every 3
months

## Input and output samples:

| Entry                  | Projected fees   | Projected installments |
| ---------------------- | ---------------- | ---------------------- |
| Loan duration: 1 week  |                  |                        |
| Start date: 01/06/2023 | 01/06/2023 => 10 | 08/06/2023 => 1010     |
| Amount: 1000           |                  |                        |
| ---------------------- | ---------------- | ---------------------- |
| Loan duration: 3 weeks | 01/06/2023 => 30 | 08/06/2023 => 1030     |
| Start date: 01/06/2023 | 08/06/2023 => 30 | 15/06/2023 => 1045     |
| Amount: 3000           | 08/06/2023 => 15 | 22/06/2023 => 1030     |
|                        | 15/06/2023 => 30 |                        |

## How to run

The mini-app is a Java REST application that uses Spring Framework, we can run it with the provided __.jar__ on the *release* page or, by cloning the repository and running it inside an IDE like JetBrains' IntelliJ, see below how to run the app.

### Release

- Command line __(Closing the terminal will terminate the application)__

    1. Download the __.jar__ on the *release* page.
    2. Open a terminal window.
    3. Go to where the __.jar__ is located. 
        1. On Windows, you can open an Explorer window, click on the linkbar and type `cmd` then press enter.
    4. execute the command `java -jar jia-loan-projections-BETA-1.1.jar` 
        1. You can type `jia` and press tab.
        2. If you're using Windows, you'll need to have Java 17+ installed and configured in your PATH variable on your system's variables.
    5. You can check the terminal for logs and when its done, you can check if the API is up by going to its [Swagger page](http://localhost:8785/jia-loan-projections/api/swagger-ui/index.html).

- Executable Jar __(The only way to close it is by going through the system's Task Manager)__

    1. Download the __.jar__ on the *release* page.
    2. Double click it.
    3. After a couple of seconds, you can check if the API is up by going to its [Swagger page](http://localhost:8785/jia-loan-projections/api/swagger-ui/index.html).

### Dev (IDE)

1. Clone the repository.
2. Open your IDE (Recommended IntelliJ).
3. Import the project/module (On IntelliJ, open *pom.xml* as a project).
4. Import maven dependencies.
5. Create a run job or right click on *JiaLoanProjectionsApplication* and click *Run*.
6. When the IDE is done, you can check if the API is up by going to its [Swagger page](http://localhost:8785/jia-loan-projections/api/swagger-ui/index.html).