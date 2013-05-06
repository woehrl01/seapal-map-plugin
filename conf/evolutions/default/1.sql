# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create sequence maps_seq;

create sequence trip_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists maps;

drop table if exists trip;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists maps_seq;

drop sequence if exists trip_seq;

