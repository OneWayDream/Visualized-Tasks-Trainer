insert into account(mail, login, hash_password, state, role, registration_date)
values ('first_test@gmail.com', 'first_test', 'first-hash-password', 'ACTIVE', 'USER', '2023-04-16');
insert into account(mail, login, hash_password, state, role, registration_date)
values ('second_test@gmail.com', 'second_test', 'second-hash-password', 'ACTIVE', 'USER', '2023-04-16');
insert into account(mail, login, hash_password, state, role, registration_date)
values ('third_test@gmail.com', 'third_test', 'third-hash-password', 'ACTIVE', 'USER', '2023-04-16');
insert into account(mail, login, hash_password, state, role, registration_date)
values ('forth_test@gmail.com', 'forth_test', 'forth-hash-password', 'ACTIVE', 'USER', '2023-04-16');
insert into jwt_user(account_id, login, mail, hash_password, state, role, is_deleted, redis_id)
values (1, 'first_login', 'first_mail@gmail.com', 'first-hash-password', 'ACTIVE', 'USER', false, null);
insert into jwt_user(account_id, login, mail, hash_password, state, role, is_deleted, redis_id)
values (2, 'second_login', 'second_mail@gmail.com', 'second-hash-password', 'ACTIVE', 'ADMIN', false, 'redis-id');
insert into jwt_user(account_id, login, mail, hash_password, state, role, is_deleted, redis_id)
values (3, 'to_delete_login', 'to_delete_mail@gmail.com', 'to-delete-hash-password', 'ACTIVE', 'USER', false, null);
insert into jwt_user(account_id, login, mail, hash_password, state, role, is_deleted, redis_id)
values (4, 'another_to_delete_login', 'another_to_delete_mail@gmail.com', 'another-to-delete-hash-password', 'ACTIVE',
        'USER', false, null);