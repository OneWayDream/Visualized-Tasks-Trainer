drop table if exists jwt_module;
create table jwt_module (
   id serial primary key,
   login varchar(255) unique not null,
   hash_password varchar(255) not null,
   state varchar(255) not null,
   role varchar(255) not null,
   is_deleted boolean not null,
   redis_id varchar(255)
);
