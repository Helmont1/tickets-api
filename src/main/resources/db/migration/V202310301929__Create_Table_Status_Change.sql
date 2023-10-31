CREATE TABLE IF NOT EXISTS Status_Change (
    status_change_id INT GENERATED ALWAYS AS IDENTITY,
    ticket_id INT NOT NULL,
    status_id INT NOT NULL,
    change_date TIMESTAMP NOT NULL
);

ALTER TABLE
    Status_Change
ADD
    CONSTRAINT Status_Change_pk PRIMARY KEY (status_change_id);


ALTER TABLE
    Status_Change
ADD
    CONSTRAINT
        FK_Status_Change_Ticket FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id);

ALTER TABLE
    Status_Change
ADD
    CONSTRAINT
        FK_Status_Change_Status FOREIGN KEY (status_id) REFERENCES Status(status_id);
