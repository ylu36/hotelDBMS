import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seed {
    public static void seed() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = conn.createStatement();
                // Drop existed tables
                stmt.executeUpdate("Drop TABLE IF EXISTS Service_Request");
                stmt.executeUpdate("Drop TABLE IF EXISTS Billing_info");
                stmt.executeUpdate("Drop TABLE IF EXISTS Check_in");
                stmt.executeUpdate("Drop TABLE IF EXISTS Room");
                stmt.executeUpdate("Drop TABLE IF EXISTS Hotel");
                stmt.executeUpdate("Drop TABLE IF EXISTS Customer");
                stmt.executeUpdate("Drop TABLE IF EXISTS Staff");

                // Create the tables

                // Staff Table @author Cosmo
                stmt.executeUpdate("CREATE TABLE Staff (id INTEGER NOT NULL PRIMARY KEY auto_increment, " +
                        "name VARCHAR(20) NOT NULL, " +
                        "age INTEGER NOT NULL, " +
                        "title VARCHAR(20) NOT NULL, " +
                        "hotelId INTEGER NOT NULL, " +
                        "department VARCHAR(20) NOT NULL, " +
                        "phone INTEGER NOT NULL, " +
                        "address VARCHAR(50) NOT NULL, " +
                        "assignedRoomId INTEGER)");

                stmt.executeUpdate("CREATE TABLE Hotel (id INTEGER NOT NULL PRIMARY KEY auto_increment,name VARCHAR(20) NOT NULL,address VARCHAR(50) NOT NULL,city VARCHAR(10) NOT NULL,phone INTEGER UNIQUE,manager_id INTEGER NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Customer (id  INTEGER NOT NULL PRIMARY KEY auto_increment,name VARCHAR(20) NOT NULL,dob  DATE NOT NULL,phone INTEGER,email VARCHAR(20) )");
                stmt.executeUpdate("CREATE TABLE Room (room_id INTEGER PRIMARY KEY auto_increment, hotel_id integer NOT NULL, category VARCHAR(20) NOT NULL, max_occu integer(40) NOT NULL, rate float NOT NULL, avai boolean NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Billing_info(id INTEGER PRIMARY KEY auto_increment,ssn INTEGER NOT NULL,payment_type VARCHAR(20) NOT NULL, card_number INTEGER NOT NULL, hotel_card BOOLEAN NOT NULL,check_in BOOLEAN NOT NULL,room_id INTEGER NOT NULL,customer_id INTEGER NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Check_in(id INTEGER PRIMARY KEY NOT NULL auto_increment,start_date DATE NOT NULL,end_date DATE NOT NULL,guestCnt INTEGER NOT NULL,customer_id INTEGER NOT NULL,room_id INTEGER NOT NULL,foreign key(room_id) REFERENCES Room(room_id))");
                stmt.executeUpdate("CREATE TABLE Service_Request (service_request_id integer NOT NULL PRIMARY KEY auto_increment, room_id integer NOT NULL, submitter_id integer NOT NULL, customer_id integer NOT NULL, type varchar(10) NOT NULL, complete boolean NOT NULL, date varchar(40) NOT NULL, cost float NOT NULL)");

                // Enter table information based on demo data

                // Enter Staff Data from Demo Data @author Cosmo
                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (100, 'Mary', 40, 'Manager', 0001, 'Management', 654, " +
                        "'90 ABC St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (101, 'John', 45, 'Manager', 0002, 'Management', 564, " +
                        "'798 XYZ St , Rochester NY 54')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (102, 'Carol', 55, 'Manager', 0003, 'Management', 546, " +
                        "'351 MH St , Greensboro NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (103, 'Emma', 55, 'Front Desk Staff', 0001, 'Management', 546, " +
                        "'49 ABC St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (104, 'Ava', 55, 'Catering Staff', 0001, 'Catering', 777, " +
                        "'425 RG St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (105, 'Peter', 52, 'Manager', 0004, 'Management', 724, " +
                        "'475 RG St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (106, 'Olivia', 27, 'Front Desk Staff', 0004, 'Management', 799, " +
                        "'325 PD St , Raleigh NC 27')");

            } finally {
                close(rs);
                close(stmt);
                // close(conn);
            }
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable whatever) {
            }
        }
    }
}