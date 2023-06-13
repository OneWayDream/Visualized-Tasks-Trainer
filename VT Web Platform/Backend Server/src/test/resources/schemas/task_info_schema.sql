drop table if exists account;
drop table if exists task_info;
create table account (
  id serial primary key,
  mail varchar(255) unique not null,
  login varchar(255) unique not null,
  hash_password varchar(255) not null,
  state varchar(255) not null,
  role varchar(255) not null,
  registration_date date not null
);
create table task_info (
  id serial primary key,
  author_id serial,
  constraint fk_author foreign key (author_id) references account(id),
  task_name varchar(255) not null,
  author_name varchar(255) not null,
  language varchar(255) not null,
  addition_date timestamp not null
);
