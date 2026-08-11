create database initial;
use initial;
create table user (
    id int auto_increment primary key,
    username VARCHAR(100),
    email VARCHAR(150)
) ;

insert into user(username, email)  values
('Stephanie', 'stephanie@gmail.com');