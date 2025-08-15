INSERT INTO authorities (authority)
VALUES ('full'),
       ('read_posts'),
       ('write_posts'),
       ('read_comments'),
       ('write_comments'),
       ('edit_comments'),
       ('delete_comments');

INSERT INTO roles (role_name)
VALUES
    ('employer'),
    ('applicant'),
    ('admin'),
    ('guest');