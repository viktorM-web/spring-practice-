--liquibase formatted sql

--changeset viktor:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset viktor:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);