use shop;

INSERT INTO category (name) values ('laptop');
INSERT INTO product (code, name, price, description, categoryCode) values (12345, 'acer', 1500,'8 gb RAM, core i-5, 4.0 GHz', 1);
INSERT INTO product (code, name, price, description, categoryCode) values (54321, 'MSI', 1700, '16 gb RAM, core i-5, 4.1 GHz', 1);
INSERT INTO cart (totalPrice, placedDate) values (null, '2021-01-13');
INSERT INTO customer (email, password, firstName, lastName, gender, birthdayDate, phoneNumber, cartCode) values
('test@gmail.com', 'Qwerty123', 'Vasya', 'Test', 'male', '2001-10-12', '+380631234555', 1);
INSERT INTO cartEntry (quantity, totalPrice, cartCode, productCode) values (2, 3000, 1, 12345);
INSERT INTO cartEntry (quantity, totalPrice, cartCode, productCode) values (1, 1700, 1, 54321);
UPDATE cart SET totalPrice = 4700 WHERE code = 1;
INSERT INTO address (street, town, region, country, customerId) VALUES ('Puskina41.st', 'Kharkiv', 'Downtown', 'Ukraine', 'test@gmail.com');


