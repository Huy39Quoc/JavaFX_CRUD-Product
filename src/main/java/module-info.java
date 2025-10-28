module main.dojavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;   // cho JPA repository
    requires java.sql;
    requires jakarta.persistence;
    requires spring.beans;           // cho SQL JDBC

    opens main.dojavafx to javafx.fxml, spring.core, spring.beans, spring.context, spring.data.jpa;
    opens main.dojavafx.Entity to spring.core, spring.beans, spring.context, spring.data.jpa;
}
