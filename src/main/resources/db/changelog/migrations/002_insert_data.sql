insert into users(name, surname, age, email, password, phone_number, avatar, account_type)
values ('John', 'Doe', 28, 'john.doe@example.com', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+1234567890', null, 'applicant'), // password -> qwe
       ('Alice', 'Smith', 32, 'alice.smith@example.com', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+1987654321', null, 'applicant'), // password -> qwe
       ('TechCorp', 'Ltd', NULL, 'hr@techcorp.com', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+1122334455', null, 'employer'), // password -> qwe
       ('DataSystems', 'Inc', NULL, 'careers@datasys.io', '$2a$10$6asNSFuS0OsD4Pnk5httR.vB0qXFScIauXb/DAv.hK6eVZiwgeuFe', '+5566778899', null, 'employer'); // password -> qwe

insert into contact_types(type)
values ('Email'),
       ('Phone'),
       ('LinkedIn'),
       ('Portfolio');


INSERT INTO categories (name, parent_id)
VALUES
    ('IT', NULL),
    ('Programming', (SELECT id FROM categories WHERE name = 'IT')),
    ('Data Science', (SELECT id FROM categories WHERE name = 'IT')),
    ('Web Development', (SELECT id FROM categories WHERE name = 'Programming')),
    ('Mobile Development', (SELECT id FROM categories WHERE name = 'Programming'));

INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'),
     'Senior Java Developer',
     (SELECT id FROM categories WHERE name = 'Web Development'),
     85000,
     TRUE,
     '2023-11-15',
     '2024-11-15'),

    ((SELECT id FROM users WHERE email = 'john.doe@example.com'),
     'Full-stack Developer',
     (SELECT id FROM categories WHERE name = 'Web Development'),
     90000,
     TRUE,
     '2021-11-15',
     '2022-11-15'),

    ((SELECT id FROM users WHERE email = 'alice.smith@example.com'),
     'Data Analyst',
     (SELECT id FROM categories WHERE name = 'Data Science'),
     72000,
     TRUE,
     '2024-11-15',
     '2025-02-15'),

    ((SELECT id FROM users WHERE email = 'alice.smith@example.com'),
     'Machine Learning Engineer',
     (SELECT id FROM categories WHERE name = 'Data Science'),
     95000,
     FALSE,
     '2023-11-15',
     '2024-10-15');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Java Developer','Backend development using Spring Boot',(SELECT id FROM categories WHERE name = 'Web Development'),80000,3,5,TRUE,(SELECT id FROM users WHERE email = 'hr@techcorp.com'),'2023-11-15','2024-11-15'),

    ('Frontend Developer',
     'React.js development for SaaS platform',
     (SELECT id FROM categories WHERE name = 'Web Development'),
     85000,
     2,
     4,
     TRUE,
     (SELECT id FROM users WHERE email = 'hr@techcorp.com'),
     '2020-11-15',
     '2024-11-15'),

    ('Data Scientist',
     'Building machine learning models',
     (SELECT id FROM categories WHERE name = 'Data Science'),
     95000,
     3,
     6,
     TRUE,
     (SELECT id FROM users WHERE email = 'careers@datasys.io'),
     '2022-11-15',
     '2024-11-15'),

    ('DevOps Engineer',
     'Cloud infrastructure management',
     (SELECT id FROM categories WHERE name = 'Programming'),
     110000,
     4,
     7,
     TRUE,
     (SELECT id FROM users WHERE email = 'careers@datasys.io'),
     '2023-11-15',
     '2025-11-15'),

    ('Python Developer',
     'Backend services development',
     (SELECT id FROM categories WHERE name = 'Programming'),
     92000,
     2,
     5,
     FALSE,
     (SELECT id FROM users WHERE email = 'hr@techcorp.com'),
     '2023-11-15',
     '2024-11-15');

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES
    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     (SELECT v.id FROM vacancies v JOIN users u ON v.author_id = u.id WHERE u.email = 'hr@techcorp.com' AND v.name = 'Java Developer'),
     TRUE),

    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Full-stack Developer'),
     (SELECT v.id FROM vacancies v JOIN users u ON v.author_id = u.id WHERE u.email = 'hr@techcorp.com' AND v.name = 'Frontend Developer'),
     FALSE),

    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'alice.smith@example.com' AND r.name = 'Data Analyst'),
     (SELECT v.id FROM vacancies v JOIN users u ON v.author_id = u.id WHERE u.email = 'careers@datasys.io' AND v.name = 'Data Scientist'),
     TRUE),

    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     (SELECT v.id FROM vacancies v JOIN users u ON v.author_id = u.id WHERE u.email = 'careers@datasys.io' AND v.name = 'Data Scientist'),
     TRUE);

INSERT INTO contacts_info (type_id, resume_id, info_value)
VALUES
    ((SELECT id FROM contact_types WHERE type = 'Email'),
     (SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     'john.doe@example.com'),

    ((SELECT id FROM contact_types WHERE type = 'Phone'),
     (SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     '+1234567890'),

    ((SELECT id FROM contact_types WHERE type = 'LinkedIn'),
     (SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     'linkedin.com/in/johndoe'),

    ((SELECT id FROM contact_types WHERE type = 'Email'),
     (SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'alice.smith@example.com' AND r.name = 'Data Analyst'),
     'alice.smith@example.com');

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES
    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     'MIT',
     'Computer Science',
     '2014-09-01',
     '2018-06-30',
     'Bachelor'),

    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'alice.smith@example.com' AND r.name = 'Data Analyst'),
     'Stanford University',
     'Data Science',
     '2015-09-01',
     '2019-06-30',
     'Master');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES
    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer'),
     5,
     'Google',
     'Senior Developer',
     'Microservices architecture development'),

    ((SELECT r.id FROM resumes r JOIN users u ON r.applicant_id = u.id WHERE u.email = 'alice.smith@example.com' AND r.name = 'Data Analyst'),
     4,
     'Amazon',
     'Data Analyst',
     'Business metrics analysis and reporting');

INSERT INTO messages (responded_applicants_id, content, CREATED_DATE)
VALUES
    ((SELECT ra.id FROM responded_applicants ra
                            JOIN resumes r ON ra.resume_id = r.id
                            JOIN users u ON r.applicant_id = u.id
                            JOIN vacancies v ON ra.vacancy_id = v.id
      WHERE u.email = 'john.doe@example.com' AND r.name = 'Senior Java Developer' AND v.name = 'Java Developer'),
     'We are impressed with your background. When can you do an interview?',
     '2025-05-15'),

    ((SELECT ra.id FROM responded_applicants ra
                            JOIN resumes r ON ra.resume_id = r.id
                            JOIN users u ON r.applicant_id = u.id
                            JOIN vacancies v ON ra.vacancy_id = v.id
      WHERE u.email = 'alice.smith@example.com' AND r.name = 'Data Analyst' AND v.name = 'Data Scientist'),
     'Thank you for your application. We will review it shortly.',
     '2025-05-16');