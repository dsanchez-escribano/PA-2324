DROP TABLE Inscription;
DROP TABLE SportEvent;
DROP TABLE SportEventType;
DROP TABLE Province;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Province (
    id BIGINT NOT NULL AUTO_INCREMENT,
    provinceName VARCHAR(60) NOT NULL,
    CONSTRAINT ProvincePK PRIMARY KEY (id),
    CONSTRAINT ProvinceNameUniqueKey UNIQUE (provinceName)
) ENGINE = InnoDB;

CREATE TABLE SportEventType (
    id BIGINT NOT NULL AUTO_INCREMENT,
    typeName VARCHAR(60) NOT NULL,
    CONSTRAINT SportEventTypePK PRIMARY KEY (id),
    CONSTRAINT SportEventTypeNameUniqueKey UNIQUE (typeName)
) ENGINE = InnoDB;

CREATE TABLE SportEvent (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sportEventName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    description VARCHAR(250) COLLATE latin1_bin NOT NULL,
    date DATETIME NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    participants INT NOT NULL,
    location VARCHAR(60) NOT NULL,
    spots INT NOT NULL,
    sumValoraciones INT NOT NULL,
    valoraciones INT NOT NULL,
    version BIGINT DEFAULT 0 NOT NULL,
    sportEventTypeId BIGINT NOT NULL,
    provinceID BIGINT NOT NULL,
    CONSTRAINT SportEventPK PRIMARY KEY (id),
    CONSTRAINT ProvinceFK FOREIGN KEY (provinceID) REFERENCES Province (id),
    CONSTRAINT SportEventTypeFK FOREIGN KEY (sportEventTypeId) REFERENCES SportEventType (id)
) ENGINE = InnoDB;


CREATE TABLE Inscription(
    id BIGINT NOT NULL AUTO_INCREMENT,
    creditCard VARCHAR(16) NOT NULL,
    sportEventId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    dorsal TINYINT NOT NULL,
    inscriptionDate DATETIME NOT NULL,
    picked BOOLEAN NOT NULL,
    valoration INT NOT NULL,
    rated BOOLEAN NOT NULL,
    CONSTRAINT InscriptionPK PRIMARY KEY (id),
    CONSTRAINT UserFK FOREIGN KEY (userId) REFERENCES User (id),
    CONSTRAINT SportEventFK FOREIGN KEY (sportEventId) REFERENCES SportEvent (id)
) ENGINE = InnoDB;