INSERT INTO users (username, password, email)
VALUES ('henk', '$2a$12$jms71oq3ubBr0ud6tRCcb.Ny6PbA3duiyRLi0pDO/lAVfEjcTB1tq', 'henk@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('henk', 'USER');
INSERT INTO users (username, password, email)                                                                                          VALUES ('ans', '$2a$12$jx9SHj7xINWKVSpERMLZ8uqo0RAU5uH9s8B4LmNhXT9Bylfc5b/.S', 'ans@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('ans', 'ADMIN');

INSERT INTO accounts (username, id, first_name, last_name, address, email, telephone_number)
VALUES ('henk', '100', 'henk', 'hendriks', 'henklaan 1 1111 hh utrecht', 'henk@mail.nl', '06123456789');
INSERT INTO accounts (username, id, first_name, last_name, address, email, telephone_number)
VALUES ('ans', '200', 'ans', 'appelman', 'ansstraat 1 1111 aa amsterdam', 'ans@mail.nl', '06123456789');

UPDATE users SET account_id = '200'
WHERE username='ans';
UPDATE users SET account_id = '100'
WHERE username='henk';

INSERT INTO products (id, title) VALUES ('1001', 'paspoort id');
INSERT INTO products (id, title) VALUES ('1002', 'parkeervergunning');
INSERT INTO products (id, title) VALUES ('1003', 'subsidie');
INSERT INTO products (id, title) VALUES ('1004', 'toeslagen');
INSERT INTO products (id, title) VALUES ('1005', 'verhuizing');

INSERT INTO appointments (id, subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('1', 'paspoort', '10-02-2023', '13:00', '1001', '100');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('2', 'parkeervergunning', '20-02-2023', '12:00', '1002', '100');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('3', 'subsidie', '04-03-2023', '10:00', '1003', '100');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('4', 'toeslagen', '15-03-2023', '13:00', '1004', '100');



INSERT INTO contact_forms (email, first_name, last_name, message)
VALUES ('henk@mail.nl', 'henk', 'hendriks', 'ik ben heel tevreden over de code  van de website');



