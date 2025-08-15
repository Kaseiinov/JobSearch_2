insert into ROLES_AUTHORITIES(role_id, authority_id)
values ((select id from roles where id = 1), (select id from AUTHORITIES where id = 2)),
       ((select id from roles where id = 1), (select id from AUTHORITIES where id = 3)),
       ((select id from roles where id = 1), (select id from AUTHORITIES where id = 4)),
       ((select id from roles where id = 1), (select id from AUTHORITIES where id = 5)),
       ((select id from roles where id = 1), (select id from AUTHORITIES where id = 6)),

       ((select id from roles where id = 2), (select id from AUTHORITIES where id = 2)),
       ((select id from roles where id = 2), (select id from AUTHORITIES where id = 3)),
       ((select id from roles where id = 2), (select id from AUTHORITIES where id = 4)),
       ((select id from roles where id = 2), (select id from AUTHORITIES where id = 5)),
       ((select id from roles where id = 2), (select id from AUTHORITIES where id = 6)),

       ((select id from roles where id = 3), (select id from AUTHORITIES where id = 1)),

       ((select id from roles where id = 4), (select id from AUTHORITIES where id = 2)),
       ((select id from roles where id = 4), (select id from AUTHORITIES where id = 4));

insert into USR_ROLES(role_id, user_id)
values ( (select id from roles where id = 2), (select id from users where id = 1) ),
       ( (select id from roles where id = 2), (select id from users where id = 2) ),
       ( (select id from roles where id = 1), (select id from users where id = 3) ),
       ( (select id from roles where id = 1), (select id from users where id = 4) ),
       ( (select id from roles where id = 2), (select id from users where id = 5) ),
       ( (select id from roles where id = 2), (select id from users where id = 6) ),
       ( (select id from roles where id = 2), (select id from users where id = 7) );
