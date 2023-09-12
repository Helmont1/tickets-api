CREATE TABLE IF NOT EXISTS Interaction(
    interaction_id INT GENERATED ALWAYS AS IDENTITY,
    ticket_id INT NOT NULL,
    user_id INT NOT NULL,
    intern BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    content VARCHAR(10000) NOT NULL
);

ALTER TABLE 
    Interaction
ADD CONSTRAINT PK_Interaction PRIMARY KEY (interaction_id);

ALTER TABLE 
    Interaction
ADD CONSTRAINT FK_Interaction_Ticket FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id);

ALTER TABLE 
    Interaction
ADD CONSTRAINT FK_Interaction_User FOREIGN KEY (user_id) REFERENCES Users(user_id);
