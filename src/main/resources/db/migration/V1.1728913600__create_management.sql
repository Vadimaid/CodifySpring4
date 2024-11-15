create table if not exists management (
    id bigserial primary key,
    full_name varchar(255) not null,
    position varchar(255) not null
);

insert into management (full_name, position)
values
    ('Head Manager', 'Director'),
    ('Head Accountant', 'Accountant'),
    ('Head Teacher', 'Main Teacher');