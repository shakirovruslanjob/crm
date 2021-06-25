drop table if exists business_profiles;
drop table if exists bank_accounts;
drop sequence if exists hibernate_seq;

create sequence hibernate_seq;

create table business_profiles
(
    id            bigint default hibernate_seq.nextval primary key,
    created_at    timestamp default now(),
    updated_at    timestamp default now(),
    name          varchar(255) not null,
    accountant    varchar(255),
    bin           varchar(255) not null unique,
    email         varchar(255),
    legal_address varchar(255) not null,
    manager       varchar(255),
    phone         varchar(255),
    real_address  varchar(255),
    site          varchar(255),
    subsidiary    varchar(255)
);

create table bank_accounts
(
    id                  bigint default hibernate_seq.nextval primary key,
    created_at          timestamp          default now(),
    updated_at          timestamp          default now(),
    bank                varchar(255) not null,
    iban                varchar(255) not null,
    swift               varchar(255) not null unique,
    business_profile_id bigint references business_profiles (id)
);
