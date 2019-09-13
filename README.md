## static-code-analyzer-web

A reworked version of the static-code-analyzer with web API 

Link to the API documentation: http://localhost:8080/swagger-ui.html#/

## Project Overview: 
Static Code Analysis is a method of debugging by examining source code before a program is run. It’s done by analyzing a set of code against a set (or multiple sets) of coding rules.


## Tools Used: 
Checkstyle, PMD, Simian tool

## Steps to install Checkstyle 
Download the checkstyle jar from the link provided below 
https://github.com/checkstyle/checkstyle/releases/download/checkstyle-8.24/checkstyle-8.24-all.jar
and update the path of the checkstyle jar in applications.properties file

## Steps to install PMD 
Download PMD from the link below
https://github.com/pmd/pmd/releases/download/pmd_releases%2F6.17.0/pmd-bin-6.17.0.zip
after installation add the pmd.bat in the path variable
(for eg:C:\pmd-bin-6.16.0\pmd-bin-6.16.0\bin)

## Steps to install Simian
Download the Simian tool jar from the below link
https://www.harukizaemon.com/simian/simian-2.5.10.tar.gz
after install, update the path of the simian jar in applications.properties file

## Steps to run the webApplication
Run the AnalyzerWebApplication.java as java application
enter the http://localhost:8080/api/?path=
