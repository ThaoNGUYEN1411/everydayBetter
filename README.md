## Everyday better API

Java(JDk 21), Spring boot (3.4.5), PostgresSql

# Description

This is a Springboot 3 project base on multiple layer architecture

- Controller: contains the REST controller.
- Repository: for the persistence and DB with JPA/Hibernate
- Service: for the business logic

# Init database

We use **PostgreSQL** as a database for this project so you can Install postgres. Yo access our local database we use **DBeaver** community as a database manager

### 1. Launch PostgreSQL with the following command:

```$bash
psql -U postgres
```

### 2. Create DataBase

Create a database for your project with this command:

```sql
CREATE DATABASE everydaybetter-db;
```

### 3. Run DDL

Run the script:
`schema.ddl.sql`
to create the tables.

### 4. Run DML

then login as "api" and run the script:
`data.dml.sql`
to insert data.

# Update Backend Application Configuration

In the application-template.properties file of your backend application, you must make a copy and configure the connection to PostgreSQL with the following parameters:

```application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/everydaybetter-db
```

Replace dev2dev_user and your password with your "api" DB user credential.

# Other Configuration

# Launch the application

Once you've created your database and updated your application configuration, you can start your backend. If you're using Maven, you can run :
bash
`mvn spring-boot:run`
}
}
}
}
