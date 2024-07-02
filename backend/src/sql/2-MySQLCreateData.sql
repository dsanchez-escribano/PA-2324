-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-------------------------------------------------------------------------------
INSERT INTO Province (provinceName) VALUES ('A Coruña');
INSERT INTO Province (provinceName) VALUES ('Pontevedra');

INSERT INTO SportEventType (typeName) VALUES ('Running');
INSERT INTO SportEventType (typeName) VALUES ('Ciclismo');


INSERT INTO SportEvent
(sportEventName, description, date, price, participants, location, spots, sumValoraciones, valoraciones, sportEventTypeId, provinceId)
VALUES ('Prueba 1', 'breve descripcion','2023-07-01T00:00:00+00:00', 10.99, 0, 'A Coruña', 500, 0, 0, 1, 1);

INSERT INTO SportEvent
(sportEventName, description, date, price, participants, location, spots, sumValoraciones, valoraciones, sportEventTypeId, provinceId)
VALUES ('Prueba 2', 'breve descripcion','2025-01-01T00:00:00+00:00', 10.99, 0, 'Pontevedra', 100, 0, 0, 2, 1);

INSERT INTO SportEvent
(sportEventName, description, date, price, participants, location, spots, sumValoraciones, valoraciones, sportEventTypeId, provinceId)
VALUES ('Prueba 3', 'breve descripcion','2025-02-01T00:00:00+00:00', 10.99, 0, 'A Coruña', 300, 0, 0, 1, 2);

INSERT INTO SportEvent
(sportEventName, description, date, price, participants, location, spots, sumValoraciones, valoraciones, sportEventTypeId, provinceId)
VALUES ('Prueba 4', 'breve descripcion','2025-03-01T00:00:00+00:00', 10.99, 0, 'Pontevedra', 2, 0, 0, 2, 2);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('participant1', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Roberto', 'Piñon', 'ejemplo@email.com', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('participant2', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Mario', 'Freire', 'ejemplo@email.com', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('participant3', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Diego', 'elEscribas', 'ejemplo@email.com', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('employee', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Fernando', 'Bellas', 'ejemplo@email.com', 1);

--e2e test
--testemployee
INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('testemployee', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Fernando', 'Bellas', 'ejemplo@email.com', 1);
--testparticipant
INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('testparticipant1', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Diego', 'elEscribas', 'ejemplo@email.com', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('testparticipant2', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Roberto', 'Piñon', 'ejemplo@email.com', 0);

--testSportevent
INSERT INTO SportEvent
(sportEventName, description, date, price, participants, location, spots, sumValoraciones, valoraciones, sportEventTypeId, provinceId)
VALUES ('Sporting Event Test', 'breve descripcion','2030-12-12T00:00:00+00:00', 10.99, 0, 'A Coruña', 500, 0, 0, 1, 1);

INSERT INTO Inscription (id ,creditCard ,sportEventId ,userId ,dorsal ,inscriptionDate,picked ,valoration ,rated )
VALUES (1,'1111222233334444',5,7,1,'2029-12-12T00:00:00+00:00',false,-1,false)