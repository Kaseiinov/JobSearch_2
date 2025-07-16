create table if not exists users
(
    id long auto_increment primary key,
    name varchar(45),
    password varchar(255)
);

insert into users(name, password)
values ('Islam', 'passwordEnc')