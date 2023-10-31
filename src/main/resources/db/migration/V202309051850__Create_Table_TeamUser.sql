CREATE TABLE IF NOT EXISTS Team_User (
    team_user_id INT GENERATED ALWAYS AS IDENTITY,
    team_id INT NOT NULL,
    user_id INT NOT NULL,
    active BOOLEAN NOT NULL,
    avg_ticket_resolution_time BIGINT
);

ALTER TABLE 
    Team_User
ADD CONSTRAINT
    PK_TEAM_USER PRIMARY KEY (team_user_id);

ALTER TABLE
    Team_User
ADD CONSTRAINT
    FK_TEAM_USER_TEAM FOREIGN KEY (team_id) REFERENCES Team(team_id);

ALTER TABLE
    Team_User
ADD CONSTRAINT
    FK_TEAM_USER_USER FOREIGN KEY (user_id) REFERENCES Users(user_id);
