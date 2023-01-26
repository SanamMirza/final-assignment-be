INSERT INTO users (username, password, email) VALUES ('henk', 'password', 'henk@mail.nl');
INSERT INTO authorities (username, authority) VALUES ('henk', 'USER');

INSERT INTO users (username, password, email) VALUES ('ans', 'peer', 'ans@mail.nl');
INSERT INTO authorities (username, authority) VALUES ('ans', 'ADMIN');

-- INSERT INTO product (title) VALUES ('passpoort aanvragen');
-- INSERT INTO product (title) VALUES ('parkeervergunning aanvragen');
-- INSERT INTO product (title) VALUES ('subsidie en of toeslagen aanvragen')


INSERT INTO appointment (subject, appointment_date, appointment_time)
VALUES ('paspoort aanvragen', '01-01-2023', '13:00');

INSERT INTO account (username, first_name, last_name, address, email_address, telephone_number)
VALUES ('henk', 'henk', 'hendriks', 'henklaan 1 1111 hh utrecht', 'henk@mail.com', '06123456789');
INSERT INTO account (username, first_name, last_name, address, email_address, telephone_number)
VALUES ('ans', 'ans', 'appelman', 'ansstraat 1 1111 aa amsterdam', 'ans@mail.nl', '06123456789');




