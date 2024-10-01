create table uncle (
    id serial primary key,
    status integer,
    nap_id integer null
);

create table nap (
    id serial primary key,
    uncle_id integer references uncle(id),
    status integer
);

alter table uncle add constraint fk_uncle_nap_id FOREIGN KEY (nap_id) REFERENCES nap (id);
