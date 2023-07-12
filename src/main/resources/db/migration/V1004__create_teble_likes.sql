create table likes(
                      id bigserial PRIMARY KEY,
                      news_id bigserial,
                      person_id bigserial,
                      constraint fk_news
                          foreign key (news_id)
                              references news (id),
                      constraint fk_person
                          foreign key (person_id)
                              references person (id)
);

insert into likes(news_id, person_id)
values (1, 4);

insert into likes(news_id, person_id)
values (2, 5);

insert into likes(news_id, person_id)
values (3, 4);

insert into likes(news_id, person_id)
values (3, 5);