INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Senior Java Developer', 'Lead backend development team', (SELECT id FROM categories WHERE name = 'Web Development'), 120000, 5, 8, TRUE, (SELECT id FROM users WHERE email = 'hr@techcorp.com'), '2024-03-15', '2024-06-15'),
    ('React Native Developer', 'Cross-platform mobile development', (SELECT id FROM categories WHERE name = 'Mobile Development'), 95000, 3, 6, TRUE, (SELECT id FROM users WHERE email = 'hr@techcorp.com'), '2024-02-01', '2024-05-01'),
    ('Cloud Engineer', 'AWS infrastructure management', (SELECT id FROM categories WHERE name = 'Programming'), 110000, 4, 7, TRUE, (SELECT id FROM users WHERE email = 'hr@techcorp.com'), '2023-12-10', '2024-06-10'),
    ('QA Automation Engineer', 'Develop testing frameworks', (SELECT id FROM categories WHERE name = 'Programming'), 85000, 2, 5, FALSE, (SELECT id FROM users WHERE email = 'hr@techcorp.com'), '2024-01-20', '2024-04-20');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('MLOps Engineer', 'Deploy machine learning models', (SELECT id FROM categories WHERE name = 'Data Science'), 125000, 4, 7, TRUE, (SELECT id FROM users WHERE email = 'careers@datasys.io'), '2024-04-01', '2024-07-01'),
    ('Data Engineer', 'Build data pipelines', (SELECT id FROM categories WHERE name = 'Data Science'), 105000, 3, 6, TRUE, (SELECT id FROM users WHERE email = 'careers@datasys.io'), '2024-03-22', '2024-06-22'),
    ('Swift Developer', 'iOS app development', (SELECT id FROM categories WHERE name = 'Mobile Development'), 98000, 2, 5, TRUE, (SELECT id FROM users WHERE email = 'careers@datasys.io'), '2023-11-30', '2024-05-30'),
    ('DevSecOps Specialist', 'Security-focused CI/CD', (SELECT id FROM categories WHERE name = 'Programming'), 135000, 5, 8, TRUE, (SELECT id FROM users WHERE email = 'careers@datasys.io'), '2024-02-15', '2024-06-15'),
    ('Big Data Architect', 'Design Hadoop/Spark solutions', (SELECT id FROM categories WHERE name = 'Data Science'), 145000, 7, 10, FALSE, (SELECT id FROM users WHERE email = 'careers@datasys.io'), '2024-01-05', '2024-04-05'),
    ('Junior Data Analyst', 'Business intelligence reporting', (SELECT id FROM categories WHERE name = 'Data Science'), 65000, 0, 2, TRUE, (SELECT id FROM users WHERE email = 'careers@datasys.io'), '2024-05-10', '2024-06-10');