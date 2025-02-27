CREATE TABLE IF NOT EXISTS USERS (
    userid INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT AUTO_INCREMENT PRIMARY KEY,
    fileName VARCHAR(255),
    contentType VARCHAR(255),
    fileSize VARCHAR(255),
    userId INT,
    fileData BLOB,
    FOREIGN KEY (userId) REFERENCES USERS(userid)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteId INT AUTO_INCREMENT PRIMARY KEY,
    noteTitle VARCHAR(255),
    noteDescription VARCHAR(255),
    userId INT,
    FOREIGN KEY (userId) REFERENCES USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialId INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255),
    username VARCHAR(255),
    encryption_key VARCHAR(255),
    password VARCHAR(255),
    userId INT,
    FOREIGN KEY (userId) REFERENCES USERS(userid)
);