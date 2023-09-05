CREATE TABLE IF NOT EXISTS Department(
    department_id INT GENERATED ALWAYS AS IDENTITY,
    department_name VARCHAR(255) not null,
    active BOOLEAN not null
);

ALTER TABLE 
    Department
ADD CONSTRAINT
    department_pk PRIMARY KEY (department_id);