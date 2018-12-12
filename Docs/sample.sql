CREATE TABLE Invoice (inv_number INT, inv_date STRING, inv_client STRING, Inv_total INT)
INSERT INTO Invoice (inv_number, inv_date, inv_client, Inv_total) VALUES ( 1, '10/10/98', 'John', 500)
INSERT INTO Invoice (inv_number, inv_date, inv_client, Inv_total) VALUES ( 2, '10/11/98', 'Paul', 700)
INSERT INTO Invoice (inv_number, inv_date, inv_client, Inv_total) VALUES ( 3, '10/12/98', 'Mary', 900)
SELECT inv_number, inv_date, inv_client, Inv_total FROM Invoice
DROP TABLE Invoice
