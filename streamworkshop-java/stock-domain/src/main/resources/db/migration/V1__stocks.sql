create sequence hibernate_sequence start 1 increment 1 ;
create table STOCK (
                     id int4 auto_increment,
                     symbol varchar(255) NOT NULL,
                     price double ,
                     last_close double,
                     high52_week double,
                     low52_week double,
                     primary key (id)
);

create table USER (
    id int4 auto_increment,
    name varchar(255),
    primary key(id)
)