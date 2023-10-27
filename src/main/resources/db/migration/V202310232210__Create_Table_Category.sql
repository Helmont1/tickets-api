CREATE TABLE IF NOT EXISTS Category (
    category_id INT GENERATED ALWAYS AS IDENTITY,
    category_name VARCHAR(255) NOT NULL,
    department_id INT NOT NULL,
    priority_id INT NOT NULL,
    active BOOLEAN NOT NULL
);

ALTER TABLE
    Category
ADD
    CONSTRAINT Category_pk PRIMARY KEY (category_id);

ALTER TABLE
    Category
ADD
    CONSTRAINT
        FK_Category_Department FOREIGN KEY (department_id) REFERENCES Department(department_id);

ALTER TABLE
    Category
ADD
    CONSTRAINT
        FK_Category_Priority FOREIGN KEY (priority_id) REFERENCES Priority(priority_id);