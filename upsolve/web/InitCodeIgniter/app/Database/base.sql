create database initial;
use initial;
create table user (
    id int auto_increment primary key,
    username VARCHAR(100),
    email VARCHAR(150)
) ;

insert into user(username, email)  values
('Stephanie', 'stephanie@gmail.com');
create table livres(
    id int auto_increment primary key,
    titre varchar(50),
    auteur varchar(100),
    prix DOUBLE default 0,
    quantite DOUBLE default 0
);