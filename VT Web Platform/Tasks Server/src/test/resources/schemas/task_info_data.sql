insert into account(mail, login, hash_password, state, role, registration_date)
values ('test@gmail.com', 'test', 'hash-password', 'ACTIVE', 'USER', '2023-04-16');
insert into task_info(author_id, task_name, author_name, language, addition_date)
values (1, 'First Test Task', 'test', 'JAVA', '2023-04-20 13:59:37.862496');
insert into task_info(author_id, task_name, author_name, language, addition_date)
values (1, 'Second Test Task', 'test', 'PYTHON', '2023-04-21 17:19:21.862496');
insert into task_info(author_id, task_name, author_name, language, addition_date)
values (1, 'Task to delete', 'test', 'PYTHON', '2023-04-22 07:10:01.862496');
