
use ebs;
create table login(meter_no varchar(20),username varchar(30), name varchar(30), password varchar(30), user varchar(30));
create table customer (name varchar(30),meter varchar(20),address varchar(50), city varchar(20),state varchar(30),email varchar(30),phone varchar(20));
create table meter_info (meter_number varchar (20), meter_location varchar(20), meter_type varchar(20), phase_code varchar(20),bill_type varchar(20), days varchar (20));
create table tax (cost_per_unit varchar(20), meter_rent varchar(20), service_tax varchar(20),swacch_bharat_cess varchar(20),fixed_tax varchar(20));
ALTER TABLE tax ADD COLUMN service_charge varchar(20) AFTER meter_rent;

insert into tax values ('9','47','22','57','6','18');

create table bill (meter varchar (20),month varchar (20) ,units varchar (20),total_bill varchar(20) , status varchar (20));

 