CREATE TABLE country (
  id   NUMBER NOT NULL ENABLE,
  country      VARCHAR(50) NOT NULL,
  last_update  TIMESTAMP   NOT NULL,
  CONSTRAINT COUNTRY_PK PRIMARY KEY  (id)
);

create sequence country_seq;


CREATE TABLE ADDRESS 
   (	
   ID NUMBER NOT NULL, 
	STREET VARCHAR2(50 BYTE) NOT NULL, 
	ZIP_CODE VARCHAR2(10 BYTE) NOT NULL, 
	COUNTRY_FK NUMBER NOT NULL, 
	CONSTRAINT ADDRESS_PK PRIMARY KEY (ID)
	CONSTRAINT ADDRESS_FK1 FOREIGN KEY (COUNTRY_FK)
	REFERENCES COUNTRY (ID) 
 );




create table owner(
id  Number not null,
first_name varchar(20),
last_name varchar(20),
constraint owner_pk primary key(id)
);

create table owner_address_pivot(
owner_fk number not null,
address_fk number not null,
CONSTRAINT OWNER_ADDRESS_PIVOT_PK PRIMARY KEY (OWNER_FK, ADDRESS_FK),
 CONSTRAINT OWNER_ADDRESS_PIVOT_FK1 FOREIGN KEY (OWNER_FK)
	  REFERENCES OWNER (ID),
 CONSTRAINT OWNER_ADDRESS_PIVOT_FK2 FOREIGN KEY (ADDRESS_FK)
	  REFERENCES ADDRESS (ID)
);


  CREATE OR REPLACE FORCE VIEW "OWNER_ADDRESS_VIEW" ("OWNER_ID", "First Name", "Last Name", "Address ID","Street","zipcode","country") AS
  select oa.owner_fk,o.first_name,o.last_name,oa.address_fk,a.street,a.zip_code,c.country from
  owner_address oa,owner o, address a where oa.owner_fk=owner_id and oa.address_fk=a.address_id and a.country_fk=c.country_id;
  
CREATE OR REPLACE FORCE VIEW "OWNER_ADDRESS_VIEW" ("OWNER_ID", "First Name", "Last Name", "Address ID","Street","zipcode","country") AS
  select oa.owner_fk,o.first_name,o.last_name,oa.address_fk,a.street,a.zip_code,c.country from
  owner_address oa,owner o, address a, country c where oa.owner_fk=owner_id and oa.address_fk=a.address_id and a.country_fk=c.country_id;
