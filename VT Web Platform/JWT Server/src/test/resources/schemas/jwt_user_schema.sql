drop table if exists account;
drop table if exists jwt_user;
create table account (
     id serial primary key,
     mail varchar(255) unique not null,
     login varchar(255) unique not null,
     hash_password varchar(255) not null,
     state varchar(255) not null,
     role varchar(255) not null,
     registration_date date not null
);
create table jwt_user (
    id serial primary key,
    account_id serial,
    constraint fk_author foreign key (account_id) references account(id),
    login varchar(255) unique not null,
    mail varchar(255) unique not null,
    hash_password varchar(255) not null,
    state varchar(255) not null,
    role varchar(255) not null,
    is_deleted boolean not null,
    redis_id varchar(255)
);