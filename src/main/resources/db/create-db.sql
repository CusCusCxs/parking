-- Limpando a tabela AVAILABLE_SLOT
DELETE FROM AVAILABLE_SLOT;
-- Inserindo dados de teste na tabela AVAILABLE_SLOT
INSERT INTO AVAILABLE_SLOT (AVAILABLE_SLOT_ID, AVAILABLE_DATE, SLOT_ID, STATUS, VIP_ID, BOOKED_EMP_ID) VALUES
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 1, 'Available', 101, 201),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 2, 'Available', 102, 202),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 3, 'Available', 103, 203),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 4, 'Available', 104, 204),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 5, 'Available', 105, 205),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 6, 'Available', 106, 206),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 7, 'Available', 107, 207),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 8, 'Available', 108, 208),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 9, 'Available', 109, 209),
(NEXT VALUE FOR hibernate_sequence, CURRENT_DATE, 10, 'Available', 110, 210);