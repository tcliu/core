-- create user table --
CREATE TABLE USERS (
	ID INT NOT NULL,
	USERNAME VARCHAR(100) NOT NULL,
	LOGINNAME VARCHAR(50) NOT NULL
);

-- create sample users --
INSERT INTO USERS (ID, USERNAME, LOGINNAME) VALUES (1, 'Tom', 'tom');
INSERT INTO USERS (ID, USERNAME, LOGINNAME) VALUES (2, 'Peter', 'peter');