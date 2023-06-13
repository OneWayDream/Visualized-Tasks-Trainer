drop table if exists account;
create table account (
     id serial primary key,
     mail varchar(255) unique not null,
     login varchar(255) unique not null,
     hash_password varchar(255) not null,
     state varchar(255) not null,
     role varchar(255) not null,
     registration_date date not null
);