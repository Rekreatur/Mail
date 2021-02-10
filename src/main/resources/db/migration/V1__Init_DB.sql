create sequence hibernate_sequence start 1 increment 1;

create table mail (
id int8 not null,
address varchar(255),
index varchar(255),
name varchar(255),
type int4, primary key (id)
);

create table post_office (
id int8 not null,
address varchar(255),
index varchar(255),
name varchar(255),
primary key (id)
);

create table traffic (
id int8 primary key not null,
date timestamp not null,
status int4 not null,
mail_id int8 references mail(id) not null,
post_office_id int8 references post_office(id) not null
);