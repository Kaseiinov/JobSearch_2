-- Сначала создаём таблицы без внешних ключей
CREATE TABLE IF NOT EXISTS users (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
    surname VARCHAR(50),
    age INTEGER,
    email VARCHAR(250),
    password VARCHAR(255),
    phone_number VARCHAR(50),
    avatar VARCHAR(255),
    account_type VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS contact_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    parent_id BIGINT
    );

CREATE TABLE IF NOT EXISTS resumes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    applicant_id BIGINT,
    name VARCHAR(100),
    category_id BIGINT,
    salary DOUBLE,
    is_active BOOLEAN,
    created_date TIMESTAMP,
    update_time TIMESTAMP,
    FOREIGN KEY (applicant_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
    );

CREATE TABLE IF NOT EXISTS vacancies (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(55),
    description VARCHAR(1000),
    category_id BIGINT,
    salary DOUBLE,
    exp_from INTEGER,
    exp_to INTEGER,
    is_active BOOLEAN,
    author_id BIGINT,
    created_date TIMESTAMP,
    update_time TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS responded_applicants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resume_id BIGINT,
    vacancy_id BIGINT,
    confirmation BOOLEAN,
    FOREIGN KEY (resume_id) REFERENCES resumes(id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id)
    );

CREATE TABLE IF NOT EXISTS contacts_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_id BIGINT,
    resume_id BIGINT,
    info_value TEXT,
    FOREIGN KEY (type_id) REFERENCES contact_types(id),
    FOREIGN KEY (resume_id) REFERENCES resumes(id)
    );

CREATE TABLE IF NOT EXISTS education_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resume_id BIGINT,
    institution VARCHAR(255),
    program VARCHAR(255),
    start_date DATE,
    end_date DATE,
    degree VARCHAR(255),
    FOREIGN KEY (resume_id) REFERENCES resumes(id)
    );

CREATE TABLE IF NOT EXISTS work_experience_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resume_id BIGINT,
    years INTEGER,
    company_name VARCHAR(255),
    position VARCHAR(255),
    responsibilities VARCHAR(1000),
    FOREIGN KEY (resume_id) REFERENCES resumes(id)
    );

CREATE TABLE IF NOT EXISTS messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    responded_applicants_id BIGINT,
    content VARCHAR(1000),
    timestamp TIMESTAMP,
    FOREIGN KEY (responded_applicants_id) REFERENCES responded_applicants(id)
    );

insert into users(name, surname, age, email, password, phone_number, avatar, account_type)
values ('John', 'Doe', 28, 'john.doe@example.com', 'qwe', '+1234567890', null, 'applicant'),
       ('Alice', 'Smith', 32, 'alice.smith@example.com', 'qwe', '+1987654321', null, 'applicant'),
       ('TechCorp', 'Ltd', NULL, 'hr@techcorp.com', 'qwe', '+1122334455', null, 'employer'),
       ('DataSystems', 'Inc', NULL, 'careers@datasys.io', 'qwe', '+5566778899', null, 'employer');

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

INSERT INTO messages (responded_applicants_id, content, timestamp)
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


