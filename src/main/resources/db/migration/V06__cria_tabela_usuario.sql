create table IF NOT EXISTS usuario (id  bigserial not null, email varchar(80), nome varchar(120), senha varchar(255), primary key (id))