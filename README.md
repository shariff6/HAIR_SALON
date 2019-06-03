# Hair Salon

An app for hair salons to help them keep track of stylists and clients.



## Getting Started


### Seeting Up the Database

In PSQL:
```
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar,age int, varchar, phoneNumber varchar, email varchar, clientCount varchar);
CREATE TABLE clients (id serial PRIMARY KEY, firstName varchar, lastName varchar,email varchar, phoneNumber varchar, stylistId int);
```

### Running the Application
the application can be run sing gradle , run


```
gradle run.

```

to get the application started on your local machine.

### Prerequisites


```
Java, Graddle, Maven, Heroku.
```

## Running the tests

Tests are run using gradle. From the root of the project directory run gradle test


## Authors

* **Shariff Mohammed**


## License

This project is licensed under the MIT License - see the [MIT LICENCE](https://opensource.org/licenses/MIT) file for details

## Website

The web-application is available at [here](https://polar-chamber-91556.herokuapp.com/)
