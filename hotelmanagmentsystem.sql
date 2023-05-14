create database hotelmanagmentsystem;
use hotelmanagmentsystem;

create table login(username varchar(40), passwrod varchar (40));
insert into login values ('admin',12345);

create table customer (id varchar(30),number varchar (30),name varchar(30),gender varchar (30),country varchar(30)
,room_number varchar (30),status varchar (30), deposit varchar (30));

create table room(room_number varchar (20),availability varchar(20) , clean_status varchar(20),price varchar(20),bed_type varchar(30));

create table employee(name varchar (30), age varchar(10), gender varchar (30),job varchar(30), salary varchar(30),phone varchar(30), aadhar varchar(30),email varchar(40));

create table driver (name varchar(30),age varchar(10),gender varchar(20), company varchar (30), brand varchar(30),available varchar(10), location varchar(50));

 select * from login;
 
 select * from driver;
 
   select * from employee ;