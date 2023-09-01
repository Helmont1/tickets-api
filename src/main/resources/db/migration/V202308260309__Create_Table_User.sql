CREATE TABLE IF NOT EXISTS Users (
  user_id INT GENERATED ALWAYS AS IDENTITY,
  user_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  keycloak_id VARCHAR(255),
  phone_number VARCHAR(11),
  birth_date DATE NOT NULL,
  cep VARCHAR(8),
  active BOOLEAN NOT NULL
);

ALTER TABLE 
    Users
ADD CONSTRAINT
    PK_USER PRIMARY KEY (user_id);