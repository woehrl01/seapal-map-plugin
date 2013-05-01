# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table boat_ebean (
  id                        varchar(255) not null,
  bootsname                 varchar(255),
  register_nr               varchar(255),
  segelzeichen              varchar(255),
  heimathafen               varchar(255),
  yachtclub                 varchar(255),
  eigner                    varchar(255),
  versicherung              varchar(255),
  rufzeichen                varchar(255),
  typ                       varchar(255),
  konstrukteur              varchar(255),
  laenge                    double,
  breite                    double,
  tiefgang                  double,
  masthoehe                 double,
  verdraengung              double,
  rigg_art                  varchar(255),
  baujahr                   integer,
  motor                     varchar(255),
  tank_groesse              double,
  wassertank_groesse        double,
  abwassertank_groesse      double,
  grosssegel_groesse        double,
  genua_groesse             double,
  spi_groesse               double,
  constraint pk_boat_ebean primary key (id))
;

create table id_container (
  id                        integer not null,
  constraint pk_id_container primary key (id))
;

create table maps (
  id                        bigint not null,
  is_menu_visible           boolean,
  menu_position_state       integer,
  position_state            integer,
  type                      integer,
  constraint ck_maps_menu_position_state check (menu_position_state in (0,1)),
  constraint ck_maps_position_state check (position_state in (0,1)),
  constraint ck_maps_type check (type in (0,1)),
  constraint pk_maps primary key (id))
;

create table person (
  id                        varchar(255) not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  birth                     timestamp,
  registration              timestamp,
  age                       integer,
  nationality               varchar(255),
  email                     varchar(255),
  telephone                 varchar(255),
  mobile                    varchar(255),
  street                    varchar(255),
  postcode                  integer,
  city                      varchar(255),
  country                   varchar(255),
  constraint pk_person primary key (id))
;

create table trip (
  id                        bigint not null,
  name                      varchar(255),
  start_location            varchar(255),
  end_location              varchar(255),
  skipper                   varchar(255),
  start_time                timestamp,
  end_time                  timestamp,
  duration                  bigint,
  motor                     integer,
  fuel                      double,
  notes                     varchar(255),
  constraint pk_trip primary key (id))
;

create sequence boat_ebean_seq;

create sequence id_container_seq;

create sequence maps_seq;

create sequence person_seq;

create sequence trip_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists boat_ebean;

drop table if exists id_container;

drop table if exists maps;

drop table if exists person;

drop table if exists trip;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists boat_ebean_seq;

drop sequence if exists id_container_seq;

drop sequence if exists maps_seq;

drop sequence if exists person_seq;

drop sequence if exists trip_seq;

