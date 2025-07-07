CREATE TABLE employer (
    id SERIAL PRIMARY KEY,
    organization_name VARCHAR(255),
    address TEXT,
    contact_no VARCHAR(20),
    email VARCHAR(255),
    username VARCHAR(100),
    password VARCHAR(100)
);