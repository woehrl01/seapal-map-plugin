# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table maps (
  id                        varchar(255) not null,
  rev                       varchar(255),
  menu_visible              boolean,
  menu_position_state       integer,
  position_state            integer,
  position                  varchar(255),
  type                      integer,
  constraint ck_maps_menu_position_state check (menu_position_state in (0,1)),
  constraint ck_maps_position_state check (position_state in (0,1)),
  constraint ck_maps_type check (type in (0,1)),
  constraint pk_maps primary key (id))
;

create sequence maps_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists maps;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists maps_seq;

