
DROP DATABASE IF EXISTS oceanPulse;

CREATE DATABASE IF NOT EXISTS oceanPulse;

USE oceanPulse;

//Create the user table
CREATE TABLE user (
    id VARCHAR(20) NOT NULL,
    userName VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);

//Create the employee table
CREATE TABLE employee (
    e_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(20) NOT NULL,
    contact VARCHAR(20) NOT NULL
);

//Create the item table
CREATE TABLE item (
    i_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    brand VARCHAR(20) NOT NULL,
    size VARCHAR(5) NOT NULL,
    price DOUBLE NOT NULL,
    qtyOnHand INT NOT NULL
);

//Create the item_manage_details table
CREATE TABLE item_manage_details (
    status VARCHAR(20) NOT NULL,
    i_id VARCHAR(20) NOT NULL,
    e_id VARCHAR(20) NOT NULL,
    CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee (e_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (i_id) REFERENCES item (i_id) ON DELETE CASCADE ON UPDATE CASCADE
);

//Create the customer table
CREATE TABLE customer (
    c_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(20) NOT NULL,
    contact VARCHAR(20) NOT NULL,
    NIC VARCHAR(20) NOT NULL
);

//Create the instructor table
CREATE TABLE instructor (
    ins_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(20) NOT NULL,
    contact VARCHAR(20) NOT NULL,
    qualification VARCHAR(20) NOT NULL
);

//Create the driver table
CREATE TABLE driver (
    d_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(20) NOT NULL,
    contact VARCHAR(20) NOT NULL,
    experience VARCHAR(20) NOT NULL
);

//Create the boat table
CREATE TABLE boat (
    b_id VARCHAR(20) PRIMARY KEY,
    capacity VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    model VARCHAR(20) NOT NULL
);

//Create the schedule table
CREATE TABLE schedule (
    s_id VARCHAR(20) PRIMARY KEY,
    event_datetime DATETIME NOT NULL,
    b_id VARCHAR(20) NOT NULL,
    ins_id VARCHAR(20) NOT NULL,
    CONSTRAINT FOREIGN KEY (b_id) REFERENCES boat (b_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (ins_id) REFERENCES instructor (ins_id) ON DELETE CASCADE ON UPDATE CASCADE
);

//Create the orders table
CREATE TABLE orders (
    o_id VARCHAR(20) PRIMARY KEY,
    date DATE NOT NULL,
    qty INT NOT NULL,
    c_id VARCHAR(20) NOT NULL,
    s_id VARCHAR(20) NOT NULL,
    CONSTRAINT fOREIGN KEY (c_id) REFERENCES customer (c_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (s_id) REFERENCES schedule (s_id) ON DELETE CASCADE ON UPDATE CASCADE
);

//Create the order_manage_details table
CREATE TABLE order_manage_details (
    duration VARCHAR(20) NOT NULL,
    description VARCHAR(20) NOT NULL,
    o_id VARCHAR(20) NOT NULL,
    i_id VARCHAR(20) NOT NULL,
    CONSTRAINT FOREIGN KEY (o_id) REFERENCES orders (o_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (i_id) REFERENCES item (i_id) ON DELETE CASCADE ON UPDATE CASCADE
);



