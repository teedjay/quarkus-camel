CREATE SCHEMA quarkus;

CREATE TABLE quarkus.message (
   msg_no serial PRIMARY KEY,
   name VARCHAR NOT NULL,
   url VARCHAR NOT NULL
);

INSERT INTO quarkus.message (name, url) VALUES
('Teedjay','https://teedjay.com'),
('Apple','https://www.apple.com'),
('Yahoo','https://www.yahoo.com'),
('Google','https://www.google.com');
