CREATE TABLE job (
    id SERIAL PRIMARY KEY,
    job_title VARCHAR(255),
    location VARCHAR(255),
    description TEXT,
    experience INT,
    salary DOUBLE PRECISION,
    notice_period INT,
    contact_email VARCHAR(255),
    status VARCHAR(50),
    employer_id BIGINT REFERENCES employer(id)
);