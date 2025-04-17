create database Phone_store_manager;
use Phone_store_manager;

create table Account (
     id int auto_increment primary key,
     username varchar(50) not null unique,
     password varchar(255) not null
);

insert into Account (username, password)
values ('admin', '12345678');
delimiter //
create procedure log_in(
    username_in varchar(50),
    password_in varchar(255)
)
begin
select * from Account
    where username = username_in and password = password_in;
end ;
delimiter //

create table Product (
     pro_id int primary key auto_increment,
     pro_name varchar(100) not null,
     brand varchar(50) not null,
     price decimal(12,2) not null,
     stock int not null
);

delimiter //
create procedure add_product(
    name_in varchar(100),
    brand_in varchar(50),
    price_in decimal(12,2),
    stock_in int
)
begin
    insert into Product(pro_name, brand, price, stock)
        values (name_in, brand_in, price_in, stock_in);
end ;

create procedure get_all_product()
begin
    select * from Product;
end ;
delimiter //



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
     foreign key (product_id) references Product(pro_id)
);
