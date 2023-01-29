INSERT INTO users (username, password, email) VALUES ('henk', 'password', 'henk@mail.nl');
INSERT INTO authorities (username, authority) VALUES ('henk', 'USER');

INSERT INTO users (username, password, email) VALUES ('ans', 'peer', 'ans@mail.nl');
INSERT INTO authorities (username, authority) VALUES ('ans', 'ADMIN');

INSERT INTO product (id, title) VALUES ('1001', 'passpoort aanvragen');
INSERT INTO product (id, title) VALUES ('1002', 'parkeervergunning aanvragen');
INSERT INTO product (id, title) VALUES ('1003', 'subsidie en of toeslagen aanvragen');


INSERT INTO appointment (subject, appointment_date, appointment_time, product_id, id)
VALUES ('paspoort aanvragen', '01-02-2023', '13:00', '1001', '100');

INSERT INTO account (username, id, first_name, last_name, address, email_address, telephone_number)
VALUES ('henk', '100', 'henk', 'hendriks', 'henklaan 1 1111 hh utrecht', 'henk@mail.com', '06123456789');
INSERT INTO account (username, id, first_name, last_name, address, email_address, telephone_number)
VALUES ('ans', '200', 'ans', 'appelman', 'ansstraat 1 1111 aa amsterdam', 'ans@mail.nl', '06123456789');




