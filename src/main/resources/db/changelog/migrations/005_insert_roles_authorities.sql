INSERT INTO authorities (authority)
VALUES ('full'),
       ('read_posts'),
       ('write_posts'),
       ('read_comments'),
       ('write_comments'),
       ('edit_comments'),
       ('delete_comments');

INSERT INTO roles (authority_id, role_name)
VALUES
    ((SELECT id FROM authorities WHERE authority = 'read_posts'), 'employer'),
    ((SELECT id FROM authorities WHERE authority = 'write_posts'), 'employer'),
    ((SELECT id FROM authorities WHERE authority = 'read_comments'), 'employer'),
    ((SELECT id FROM authorities WHERE authority = 'write_comments'), 'employer'),
    ((SELECT id FROM authorities WHERE authority = 'edit_comments'), 'employer'),
    ((SELECT id FROM authorities WHERE authority = 'read_posts'), 'applicant'),
    ((SELECT id FROM authorities WHERE authority = 'write_posts'), 'applicant'),
    ((SELECT id FROM authorities WHERE authority = 'read_comments'), 'applicant'),
    ((SELECT id FROM authorities WHERE authority = 'write_comments'), 'applicant'),
    ((SELECT id FROM authorities WHERE authority = 'edit_comments'), 'applicant'),
    ((SELECT id FROM authorities WHERE authority = 'full'), 'admin'),
    ((SELECT id FROM authorities WHERE authority = 'read_posts'), 'guest'),
    ((SELECT id FROM authorities WHERE authority = 'read_comments'), 'guest');