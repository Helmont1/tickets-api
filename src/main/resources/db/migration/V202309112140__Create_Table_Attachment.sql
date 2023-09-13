CREATE TABLE IF NOT EXISTS Attachment(
    attachment_id INT GENERATED ALWAYS AS IDENTITY,
    path_to_file VARCHAR(1000) NOT NULL,    
    ticket_id INT NOT NULL
);

ALTER TABLE 
    Attachment
ADD CONSTRAINT PK_Attachment PRIMARY KEY (attachment_id);

ALTER TABLE 
    Attachment
ADD CONSTRAINT FK_Attachment_Ticket FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id);
