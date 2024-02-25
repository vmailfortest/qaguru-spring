create extension if not exists "uuid-ossp";

create table if not exists "country"
(
    id            UUID unique        not null default uuid_generate_v1() primary key,
    country_name      varchar(255) unique not null,
    country_code varchar(2)              not null
);

alter table "country"
    owner to postgres;

delete from "country";

insert into "country" (country_name, country_code) values ('Peru', 'pr');
insert into "country" (country_name, country_code) values ('Mexico', 'mx');
