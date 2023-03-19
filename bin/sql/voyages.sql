DROP DATABASE IF EXISTS bddvoyages;
CREATE DATABASE bddvoyages;
USE bddvoyages;

CREATE TABLE client(
    idc int(5) not null auto_increment,
    nomc varchar (20) not null,
    prenomc varchar (20) not null,
    rue varchar(30) not null,
    cp varchar(10) not null,
    villec varchar(30) not null,
    pays_c varchar(30) not null,
    mail_c varchar(50) not null,
    datenaiss_c date not null,
    mdp_c varchar (30) not null,
    primary key (idc)
);

CREATE TABLE reservation(
    idr int(5) not null,
    date_resa date not null,
    nb_personnes int(10) not null,
    primary key (idr)
);

CREATE TABLE voyages(
    idv int(5) not null, 
    datedeb_voyage date not null,
    datefin_voyage date not null,
    lieu_voyage  varchar (30) not null,
    primary key(idv)
);

CREATE TABLE transport(
    idt int(5) not null,
    type_transport varchar(30) not null,
    tarif_transport float (30) not null,
    idv int (5) not null,
    primary key(idt),
    foreign key(idv)  references voyages(idv)
);


CREATE TABLE logement(
    idl int(5) not null auto_increment,
    lieu_logement varchar(30) not null,
    idv int (5) not null,
    capacite_accueil int(30) not null,
    tarif_logement int(30) not null,
    primary key (idl),
    foreign key(idv)  references voyages(idv)
);

CREATE TABLE activites(
    ida int(5) not null auto_increment,
    nom_activite varchar(30) not null,
    type_activite varchar(30) not null,
    primary key (ida)
    
    );

CREATE TABLE details(
    detail_activites varchar(255),
    tarification_activite float(30),
    ida int(5) not null,
    idv int(5) not null,
    foreign key(ida) references activites(ida),
    foreign key(idv) references voyages(idv)
);