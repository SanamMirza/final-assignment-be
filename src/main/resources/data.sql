INSERT INTO users (username, password, email) VALUES ('henk', 'password', 'henk@mail.nl');
INSERT INTO authorities (username, authority) VALUES ('henk', 'USER');

INSERT INTO users (username, password, email) VALUES ('ans', 'peer', 'ans@mail.nl');
INSERT INTO authorities (username, authority) VALUES ('ans', 'ADMIN');

INSERT INTO account (username, id, first_name, last_name, address, email, telephone_number)
VALUES ('henk', '100', 'henk', 'hendriks', 'henklaan 1 1111 hh utrecht', 'henk@mail.nl', '06123456789');
INSERT INTO account (username, id, first_name, last_name, address, email, telephone_number)
VALUES ('ans', '200', 'ans', 'appelman', 'ansstraat 1 1111 aa amsterdam', 'ans@mail.nl', '06123456789');

INSERT INTO product (id, title) VALUES ('1001', 'paspoort id');
INSERT INTO product (id, title) VALUES ('1002', 'parkeervergunning');
INSERT INTO product (id, title) VALUES ('1003', 'subsidie');
INSERT INTO product (id, title) VALUES ('1004', 'toeslagen');
INSERT INTO product (id, title) VALUES ('1005', 'verhuizing');

INSERT INTO appointment (subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('paspoort', '10-02-2023', '13:00', '1001', '100');
INSERT INTO appointment (subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('parkeervergunning', '20-02-2023', '12:00', '1002', '100');
INSERT INTO appointment (subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('subsidie', '04-03-2023', '10:00', '1003', '100');
INSERT INTO appointment (subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('toeslagen', '15-03-2023', '13:00', '1004', '100');
INSERT INTO appointment (subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('verhuizing', '20-02-2023', '11:00', '1005', '100');






