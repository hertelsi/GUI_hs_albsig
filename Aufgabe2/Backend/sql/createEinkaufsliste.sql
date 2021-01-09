drop table einkaufsliste cascade constraints purge;
drop table artikel cascade constraints purge;
drop table benutzer cascade constraints purge;
drop table liste2benutzer cascade constraints purge;
drop table shop cascade constraints purge;

drop sequence shopSequence;
drop sequence einkaufslisteSequence;
drop sequence benutzerSequence;
drop sequence artikelSequence;
drop sequence liste2benutzerSequence;

create sequence shopSequence start with 1 increment by 1;
create sequence einkaufslisteSequence start with 1 increment by 1;
create sequence benutzerSequence start with 1 increment by 1;
create sequence artikelSequence start with 1 increment by 1;
create sequence liste2benutzerSequence start with 1 increment by 1;

create table shop(
    id smallint primary key,
    name varchar2(30),
    ort varchar2(30)
);

create table einkaufsliste (
    id smallint primary key,
    name varchar2(30),
    erstellungsDatum date,
    einkaufsDatum date,
    shopId references shop
);

create table artikel (
    id smallint primary key,
    name varchar2(30),
    mengeEinheit varchar2(30),
    menge int,
    einkaufslisteId references einkaufsliste
);
   
create table benutzer(
    id smallint primary key,
    name varchar2(30),
    passwort varchar(30)
);

create table liste2benutzer(
  id smallint primary key,
    benutzerId references benutzer,
    einkaufslisteId references einkaufsliste
);

/*
insert into shop values (1,'Bauhaus','Lange Str. 9, 72336 Balingen');
insert into shop values (2,'Edeka','Im Grund 1, 72469 Meﬂstetten');

insert into einkaufsliste values(1,'Supermarkt',CURRENT_DATE, trunc(sysdate+1),2);
insert into einkaufsliste values(2,'Baumarkt',CURRENT_DATE, trunc(sysdate+1),1);

insert into artikel values(1,'Apfel','Stk',10,1);
insert into artikel values(2,'Schokolade','kg',2,1);
insert into artikel values(3,'Bier','Kisten',3,1);

insert into artikel values(4,'Nagel','Stk',100,2);
insert into artikel values(5,'Hammer','Stk',2,2);
insert into artikel values(6,'S‰ge','Stk',3,2);

insert into benutzer values(1,'Julian','123');
insert into benutzer values(2,'Simon','123');

insert into liste2benutzer values(1,1);
insert into liste2benutzer values(2,1);
insert into liste2benutzer values(1,2);
insert into liste2benutzer values(2,2);

*/

select * from einkaufsliste;
select * from benutzer_einkaufsliste;
select * from artikel;
select * from benutzer;
select * from shop;

commit;