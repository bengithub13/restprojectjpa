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


CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled Number(1,0) default 1,
  PRIMARY KEY (username));
  
  CREATE TABLE user_roles (
  user_role_id Number(11) NOT NULL, 
  username VARCHAR(45) NOT NULL,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  
   create sequence user_roles_seq;
   
   CREATE OR REPLACE TRIGGER dept_bir 
BEFORE INSERT ON departments 
FOR EACH ROW

BEGIN
  SELECT dept_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
   
   

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);


create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_username2 foreign key (username) references users (username),
    constraint authorities_idx_1 unique  (username, authority)
) ;
insert into  authorities (username,authority) values ('benpoon','admin');


  CREATE OR REPLACE FORCE VIEW "OWNER_ADDRESS_VIEW" ("OWNER_ID", "First Name", "Last Name", "Address ID","Street","zipcode","country") AS
  select oa.owner_fk,o.first_name,o.last_name,oa.address_fk,a.street,a.zip_code,c.country from
  owner_address oa,owner o, address a where oa.owner_fk=owner_id and oa.address_fk=a.address_id and a.country_fk=c.country_id;
  
CREATE OR REPLACE FORCE VIEW "OWNER_ADDRESS_VIEW" ("OWNER_ID", "First Name", "Last Name", "Address ID","Street","zipcode","country") AS
  select oa.owner_fk,o.first_name,o.last_name,oa.address_fk,a.street,a.zip_code,c.country from
  owner_address oa,owner o, address a, country c where oa.owner_fk=owner_id and oa.address_fk=a.address_id and a.country_fk=c.country_id;
