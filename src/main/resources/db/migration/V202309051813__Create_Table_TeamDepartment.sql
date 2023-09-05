CREATE TABLE IF NOT EXISTS Team_Department
(
    team_department_id INT GENERATED ALWAYS AS IDENTITY,
    team_id INT NOT NULL,
    department_id INT NOT NULL,
    active BOOLEAN NOT NULL
);

ALTER TABLE 
    Team_Department 
ADD CONSTRAINT PK_Team_Department PRIMARY KEY (team_department_id);

ALTER TABLE 
    Team_Department
ADD CONSTRAINT FK_Team_Department_Team FOREIGN KEY (team_id) REFERENCES Team(team_id);

ALTER TABLE 
    Team_Department
ADD CONSTRAINT FK_Team_Department_Department FOREIGN KEY (department_id) REFERENCES Department(department_id);