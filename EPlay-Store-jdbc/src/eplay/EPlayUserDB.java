/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author thinh
 */
public class EPlayUserDB {

    // Database parameters for connection - url, username, password
    static String url;
    static String username;
    static String password;

    static final String DB_TABLE = "EPLAY_USER";
    static final String DB_TABLE_CUSTOMER = "EPLAY_CUSTOMER";
    static final String DB_TABLE_EMPLOYEE = "EPLAY_EMPLOYEE";
    static final String DB_TABLE_PRODUCT = "EPLAY_PRODUCT";

    static final String DB_PK_CONSTRAINT = "PK_" + DB_TABLE;

    /**
     * constructor using default url, username and password
     */
    public EPlayUserDB() {
        // set default parameters for Derby and JavaDB
        url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        username = "APP";
        password = "APP";
    }

    public static Connection getConnection()
            throws SQLException, IOException {
        // first, need to set the driver for connection
        // for Derby
        System.setProperty("jdbc.drivers",
                "org.apache.derby.jdbc.ClientDriver");

        // next is to get the connection
        return DriverManager.getConnection(url, username, password);
    }

    /*
     * createDBTable
     *
     * @aim Use SQL commands to create the database table
     */
    public void createDBTable() {
        Connection cnnct = null;    // declare a connection object
        Statement stmnt = null;     // declare a statement object

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            /**
             * execute query to create a data table with the required fields:
             * EmpId, Name, Tel, Address, Bank_Account (for payroll), Salary,
             * Active (currently employed in the company)
             */
            stmnt.execute("CREATE TABLE " + DB_TABLE
                    + " (UserId VARCHAR(5) CONSTRAINT " + DB_PK_CONSTRAINT + " PRIMARY KEY,"
                    + " Username VARCHAR(25) UNIQUE, "
                    + " Password VARCHAR(300), "
                    + " UserGroup VARCHAR(20), "
                    + " Active BOOLEAN)");
            stmnt.execute("CREATE TABLE " + DB_TABLE_CUSTOMER
                    + " (CusId VARCHAR(5) PRIMARY KEY, "
                    + " Cusname VARCHAR(25), "
                    + " Phone VARCHAR(10), "
                    + " Address VARCHAR(30), "
                    + " Email VARCHAR(30), "
                    + " Rank VARCHAR(20), "
                    + " pBrought VARCHAR(5), "
                    + " FOREIGN KEY (CusId) REFERENCES EPLAY_USER(UserId))");
            stmnt.execute("CREATE TABLE " + DB_TABLE_EMPLOYEE
                    + " (EmpId VARCHAR(5) PRIMARY KEY, "
                    + " Empname VARCHAR(25), "
                    + " Phone VARCHAR(10), "
                    + " Address VARCHAR(30), "
                    + " Email VARCHAR(30), "
                    + " Salary DECIMAL(10,2), "
                    + " FOREIGN KEY (EmpId) REFERENCES EPLAY_USER(UserId))");
            stmnt.execute("CREATE TABLE " + DB_TABLE_PRODUCT
                    + " (ProId VARCHAR(5) PRIMARY KEY, "
                    + " Name VARCHAR(25), "
                    + " Catergory VARCHAR(10), "
                    + " Price DECIMAL(10,2))");
        } catch (SQLException ex) {
            System.out.print(ex);
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    /**
                     * cnnct.close() throws a SQLException, but we cannot
                     * recover at this point
                     */
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }

    /**
     * destroyDBTable()
     *
     * @aim Remove the database table
     */
    public void destroyDBTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            // execute action query to destroy a data table
            stmnt.execute("DROP TABLE " + DB_TABLE_PRODUCT);
            stmnt.execute("DROP TABLE " + DB_TABLE_EMPLOYEE);
            stmnt.execute("DROP TABLE " + DB_TABLE_CUSTOMER);
            stmnt.execute("DROP TABLE " + DB_TABLE);
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }

    /**
     * addRecord()
     *
     * @aim Add a record into the database table
     */
    public void addRecords(ArrayList<EPlayCustomer> userList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;
        PreparedStatement pStmntCus = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_TABLE
                    + " VALUES (?, ?, ?, ?, ?)";

            String preQueryStatementCus = "INSERT INTO " + DB_TABLE_CUSTOMER
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";

            for (EPlayCustomer user : userList) {

                // get statement
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmntCus = cnnct.prepareStatement(preQueryStatementCus);

                // set individual parameters at corresponding positions
                pStmnt.setString(1, user.getUserid());
                pStmnt.setString(2, user.getName());
                pStmnt.setString(3, user.getPassword());
                pStmnt.setString(4, user.getUserGroup());
                pStmnt.setBoolean(5, user.isActive());

                pStmntCus.setString(1, user.getUserid());
                pStmntCus.setString(2, user.getCusname());
                pStmntCus.setString(3, user.getPhone());
                pStmntCus.setString(4, user.getAddress());
                pStmntCus.setString(5, user.getEmail());
                pStmntCus.setString(6, user.getRank());
                pStmntCus.setInt(7, user.getpBrought());

                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();
                int rowCountCus = pStmntCus.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0 && rowCountCus == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }

    public void addRecordsE(ArrayList<EPlayEmployee> userList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;
        PreparedStatement pStmntCus = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_TABLE
                    + " VALUES (?, ?, ?, ?, ?)";

            String preQueryStatementCus = "INSERT INTO " + DB_TABLE_EMPLOYEE
                    + " VALUES (?, ?, ?, ?, ?, ?)";

            for (EPlayEmployee user : userList) {

                // get statement
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmntCus = cnnct.prepareStatement(preQueryStatementCus);

                // set individual parameters at corresponding positions
                pStmnt.setString(1, user.getUserid());
                pStmnt.setString(2, user.getName());
                pStmnt.setString(3, user.getPassword());
                pStmnt.setString(4, user.getUserGroup());
                pStmnt.setBoolean(5, user.isActive());

                pStmntCus.setString(1, user.getUserid());
                pStmntCus.setString(2, user.getEmpname());
                pStmntCus.setString(3, user.getPhone());
                pStmntCus.setString(4, user.getAddress());
                pStmntCus.setString(5, user.getEmail());
                pStmntCus.setDouble(6, user.getSalary());

                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();
                int rowCountCus = pStmntCus.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0 && rowCountCus == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }
    
        public void addRecordsP(ArrayList<EPlayProduct> userList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_TABLE_PRODUCT
                    + " VALUES (?, ?, ?, ?)";

            for (EPlayProduct user : userList) {

                // get statement
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                // set individual parameters at corresponding positions
                pStmnt.setString(1, user.getProductId());
                pStmnt.setString(2, user.getName());
                pStmnt.setString(3, user.getCatergory());
                pStmnt.setDouble(4, user.getPrice());

                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }
}
