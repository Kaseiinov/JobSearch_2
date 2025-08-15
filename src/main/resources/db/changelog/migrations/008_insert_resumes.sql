INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'), 'Backend Developer (Java)', (SELECT id FROM categories WHERE name = 'Web Development'), 82000, TRUE, '2023-09-10', '2024-01-20'),
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'), 'Cloud Solutions Architect', (SELECT id FROM categories WHERE name = 'Programming'), 115000, TRUE, '2024-02-15', '2024-05-15'),
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'), 'Android Developer', (SELECT id FROM categories WHERE name = 'Mobile Development'), 88000, FALSE, '2022-11-01', '2023-06-01');

INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    ((SELECT id FROM users WHERE email = 'alice.smith@example.com'), 'Senior Data Scientist', (SELECT id FROM categories WHERE name = 'Data Science'), 105000, TRUE, '2024-01-20', '2024-04-20'),
    ((SELECT id FROM users WHERE email = 'alice.smith@example.com'), 'BI Analyst', (SELECT id FROM categories WHERE name = 'Data Science'), 78000, TRUE, '2023-08-15', '2024-02-15'),
    ((SELECT id FROM users WHERE email = 'alice.smith@example.com'), 'Python Developer', (SELECT id FROM categories WHERE name = 'Programming'), 92000, FALSE, '2022-05-10', '2023-05-10');

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, role_id) VALUES
                                                                                                ('Robert', 'Johnson', 30, 'robert.j@example.com', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+1122334455', null, 'applicant', (select id from roles where id = 2)),
                                                                                                ('Emily', 'Wilson', 29, 'emily.w@example.com', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+1555666777', null, 'applicant' , (select id from roles where id = 2)),
                                                                                                ('Michael', 'Brown', 35, 'michael.b@example.com', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+1444333222', null, 'applicant' , (select id from roles where id = 2));

INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    ((SELECT id FROM users WHERE email = 'robert.j@example.com'), 'Frontend Developer (React)', (SELECT id FROM categories WHERE name = 'Web Development'), 85000, TRUE, '2024-03-01', '2024-06-01'),
    ((SELECT id FROM users WHERE email = 'robert.j@example.com'), 'UI/UX Engineer', (SELECT id FROM categories WHERE name = 'Web Development'), 79000, TRUE, '2023-12-15', '2024-05-15'),
    ((SELECT id FROM users WHERE email = 'emily.w@example.com'), 'iOS Developer', (SELECT id FROM categories WHERE name = 'Mobile Development'), 95000, TRUE, '2024-01-10', '2024-04-10'),
    ((SELECT id FROM users WHERE email = 'emily.w@example.com'), 'Product Manager (Tech)', (SELECT id FROM categories WHERE name = 'Programming'), 105000, FALSE, '2023-07-22', '2024-01-22'),
    ((SELECT id FROM users WHERE email = 'michael.b@example.com'), 'Systems Analyst', (SELECT id FROM categories WHERE name = 'IT'), 98000, TRUE, '2024-02-28', '2024-05-28');