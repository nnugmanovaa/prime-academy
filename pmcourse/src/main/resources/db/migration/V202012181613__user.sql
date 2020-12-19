create table users(
    id BIGSERIAL PRIMARY KEY ,
    login varchar(65) unique not null ,
    password varchar (65) not null
);
