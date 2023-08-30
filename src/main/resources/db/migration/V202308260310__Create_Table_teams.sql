IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'Teams') AND type in (N'U'))
BEGIN
    CREATE TABLE Teams (
        team_id INT GENERATED ALWAYS AS IDENTITY,
        team_name VARCHAR(255) NOT NULL,
        team_leader_id INT NOT NULL,
        team_active BOOLEAN NOT NULL
    );

    ALTER TABLE 
        Teams
    ADD CONSTRAINT
        PK_TEAM PRIMARY KEY (team_id);

    ALTER TABLE
        Teams
    ADD CONSTRAINT
        FK_TEAM_LEADER FOREIGN KEY (team_leader_id) REFERENCES Users(user_id);
END
  team_id INT GENERATED ALWAYS AS IDENTITY,
  team_name VARCHAR(255) NOT NULL,
  team_leader_id INT NOT NULL,
  team_active BOOLEAN NOT NULL
);

ALTER TABLE 
    Teams
ADD CONSTRAINT
    PK_TEAM PRIMARY KEY (team_id);

ALTER TABLE
    Teams
ADD CONSTRAINT
    FK_TEAM_LEADER FOREIGN KEY (team_leader_id) REFERENCES Users(user_id);