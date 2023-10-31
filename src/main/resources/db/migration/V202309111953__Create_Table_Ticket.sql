CREATE TABLE IF NOT EXISTS Ticket (
    ticket_id INT GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(255) NOT NULL,
    requester_id INT NOT NULL,
    content VARCHAR(10000) NOT NULL,
    category_id INT NOT NULL,
    priority_id INT NOT NULL,
    status_id INT NOT NULL,
    opening_date TIMESTAMP NOT NULL,
    modification_date TIMESTAMP NOT NULL,
    department_id INT NOT NULL,
    team_user_id INT NOT NULL
);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT Ticket_pk PRIMARY KEY (ticket_id);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT
        FK_Ticket_User FOREIGN KEY (requester_id) REFERENCES Users(user_id);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT
        FK_Ticket_Priority FOREIGN KEY (priority_id) REFERENCES Priority(priority_id);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT
        FK_Ticket_Status FOREIGN KEY (status_id) REFERENCES Status(status_id);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT
        FK_Ticket_Department FOREIGN KEY (department_id) REFERENCES Department(department_id);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT
        FK_Ticket_Team_User FOREIGN KEY (team_user_id) REFERENCES Team_User(team_user_id);

ALTER TABLE
    Ticket
ADD
    CONSTRAINT FK_Ticket_Category FOREIGN KEY (category_id) REFERENCES Category(category_id);