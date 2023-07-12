create table comment(
                        id bigserial PRIMARY KEY,
                        news_id bigserial,
                        person_id bigserial,
                        body varchar(2000) not null,
                        created_date date,
                        constraint fk_news
                            foreign key (news_id)
                                references news (id),
                        constraint fk_person
                            foreign key (person_id)
                                references person (id)
);

insert into comment(news_id, person_id, body, created_date)
values (1, 4,'News1 comment 1', '2022-11-25');

insert into comment(news_id, person_id, body, created_date)
values (1, 5,'News1 comment 2', '2022-11-26');

insert into comment(news_id, person_id, body, created_date)
values (2, 4,'News2 comment 1', '2022-12-31');