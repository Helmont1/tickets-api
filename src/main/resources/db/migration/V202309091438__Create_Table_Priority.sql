CREATE TABLE IF NOT EXISTS Priority (
    priority_id INT GENERATED ALWAYS AS IDENTITY,
    priority_name VARCHAR(25) NOT NULL
);

ALTER TABLE 
    Priority
ADD CONSTRAINT
    PK_Priority PRIMARY KEY (priority_id);
