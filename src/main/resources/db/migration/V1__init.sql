create table rate
(
    id          serial,
    name        varchar                             not null,
    price       numeric                             not null,
    create_time timestamp without time zone DEFAULT now()
);

create unique index rate_id_uindex
    on rate (id);

alter table rate
    add constraint rate_pk
        primary key (id);

