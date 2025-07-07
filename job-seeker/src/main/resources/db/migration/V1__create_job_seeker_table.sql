CREATE TABLE job_seeker (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    contact_no VARCHAR(50),
    email VARCHAR(255),
    skill_set VARCHAR(255),
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);