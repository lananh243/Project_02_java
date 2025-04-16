create database Phone_store_manager;
use Phone_store_manager;

create table Account (
                         id int auto_increment primary key,
                         username varchar(50) not null unique,
                         password varchar(255) not null,
                         status bit,
                         role enum('ACTIVE', 'INACTIVE')
);

create table Product (
                         product_id char(10) primary key,
                         product_name varchar(100) not null,
                         brand varchar(50) not null,
                         price decimal(12,2) not null,
                         stock int not null
);


create table Customer (
                          customer_id char(20) primary key,
                          customer_name varchar(100) not null,
                          phone varchar(20) ,
                          email varchar(100) unique,
                          address varchar(255),
                          age int,
                          gender enum('MALE', 'FEMALE', 'OTHER')
);

create table Invoice (
                         invoice_id char(10) primary key,
                         customer_id char(10),
                         created_at datetime default current_timestamp,
                         total_amount decimal(12,2) not null,
                         foreign key (customer_id) references Customer(customer_id),
                         status enum('PENDING', 'CONFIRMED', 'DELIVERED', 'FAILED')
);


create table Invoice_details (
                                 id int primary key auto_increment,
                                 invoice_id char(10),
                                 product_id char(10),
                                 quantity int not null,
                                 unit_price decimal(12,2) not null,
                                 foreign key (invoice_id) references Invoice(invoice_id),
                                 foreign key (product_id) references Product(product_id)
);
