create table if not exists users (
    id bigserial not null primary key,
    username varchar(255) not null,
    fio varchar(1000) not null,
    UNIQUE (username)
);
create table if not exists Logins (
     id bigserial not null primary key,
     access_date timestamp not null ,
     user_id bigserial not null,
     application varchar(50) not null
);

alter table Logins add foreign key (user_id) references users(id);

