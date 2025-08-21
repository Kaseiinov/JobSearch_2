update users
set ROLE_ID = (select id from roles where ROLE_NAME = 'employer')
where ACCOUNT_TYPE = 'employer';

update USERS
set role_id = (select id from roles where role_name = 'applicant')
where ACCOUNT_TYPE = 'applicant';