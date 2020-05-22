# Spring Boot Exchange Rates
This repository includes a project done as a coding challenge during a job application.  
It is a Spring Boot Application that queries exchange rates.  
The requirements are the following:  

## Task Description
Our company wants to implement a REST service to display exchange rates for our
customers. The service receives three parameters: two currencies and a date using the
URL path. 

The format of the URL path is  **`/api/exchange-rate/{date}/{baseCurrency}/{targetCurrency}`**

The REST service returns:
- The exchange rate of the requested date,
- the average of the five days before the requested date (excluding Saturday and
Sunday )
- the exchange rate trend.


The exchange rate trend is determined using following definition:

- descending: when the exchange rates in the last five days are in strictly
descending order,

- ascending: when the exchange rates in the last five days are in strictly ascending
- constant: when the exchange rates in the last five days are the same
- undefined: in other cases.

## Basic Features
- Allow users to query the exchange rate using two currencies and a date using the
API.
Only dates between 2000-01-01 and yesterday are allowed. The API of
**https://exchangeratesapi.io/** should be used as data source to implement our REST
service. Only the currencies supported by **https://exchangeratesapi.io/** can be
used.

- Return an error in case of incorrect input parameters
The error should be returned using the format of http://jsonapi.org (status, title
and description are required, other fields are optional)

- All successful queries should be persisted in the DB. The customer can get
historical information using two API’s, one for the daily information and other for
the monthly information.
    - daily: **`/api/exchange-rate/history/daily/{yyyy}/{MM}/{dd}`**
    - monthly: **`/api/exchange-rate/history/monthly/{yyyy}/{MM}`**
## Technology stack
We want you to use the following technology stack:
- Spring Boot (http://start.spring.io)
- Hibernate, Spring Data JPA
- MAVEN