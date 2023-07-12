create table person(
                       id bigserial PRIMARY KEY,
                       role varchar(100) not null,
                       username varchar(100) not null,
                       password varchar(100) not null,
                       email varchar(100) not null UNIQUE,
                       enabled boolean not null
);

insert into person(role, username, password, email, enabled)
values ('ROLE_ADMIN', 'admin', '$2a$12$A4QwfujaATpAw/sftaDJJuQQGkcPmSW/xm7L1EiAJ7OKw/mB1ajPK', 'admin@gmail.com', true);

insert into person(role, username, password, email, enabled)
values ('ROLE_AUTHOR', 'author', '$2a$12$iWaPfCOmYtv2WvM1rLkrsOOiB28AfSY70/gkJ6pHmEXwFFQcCAS5K', 'author@gmail.com', true);

insert into person(role, username, password, email, enabled)
values ('ROLE_MODERATOR', 'moderator', '$2a$12$KlrGaia9L6bTVwXI5ErYe.cv2oUziqead0BTZZQ6ceZmCiR85SIqi', 'moderator@gmail.com', true);

insert into person(role, username, password, email, enabled)
values ('ROLE_USER', 'user1', '$2a$12$3ibSCkLFgL2WI.NxNqoCOurYLFZYCWxJr6aqjbmsrwaPzSF4FDn9O', 'user1@gmail.com', true);

insert into person(role, username, password, email, enabled)
values ('ROLE_USER', 'user2', '$2a$12$D1QBT4PrEWrl53GTnvdX5O0YrFTSv1vHhShzc96b/aS8KfhW7YjC2', 'user2@gmail.com', true);