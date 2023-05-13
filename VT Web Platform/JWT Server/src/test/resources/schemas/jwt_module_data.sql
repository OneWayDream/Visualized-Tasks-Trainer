insert into jwt_module(login, hash_password, state, role, is_deleted, redis_id)
values ('first_login', 'first_hash_password', 'ACTIVE', 'USER', false, null);
insert into jwt_module(login, hash_password, state, role, is_deleted, redis_id)
values ('second_login', 'second_hash_password', 'ACTIVE', 'ADMIN', false, 'redis-id');
insert into jwt_module(login, hash_password, state, role, is_deleted, redis_id)
values ('to_delete_login', 'to_delete_hash_password', 'ACTIVE', 'USER', false, null);