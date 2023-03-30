INSERT INTO users (username, password, email)
VALUES ('henk', '$2a$12$jms71oq3ubBr0ud6tRCcb.Ny6PbA3duiyRLi0pDO/lAVfEjcTB1tq', 'henk@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('henk', 'USER');
INSERT INTO users (username, password, email)
VALUES ('bart', '$2a$12$l7rTl2TJhGIKQuONtHEBvuYQY3VB9ZYxyBkUO9/Cft1vd5fTe5KrO', 'bart@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('bart', 'USER');
INSERT INTO users (username, password, email)
VALUES ('koen', '$2a$12$l7rTl2TJhGIKQuONtHEBvuYQY3VB9ZYxyBkUO9/Cft1vd5fTe5KrO', 'koen@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('koen', 'USER');
INSERT INTO users (username, password, email)
VALUES ('sam', '$2a$12$l7rTl2TJhGIKQuONtHEBvuYQY3VB9ZYxyBkUO9/Cft1vd5fTe5KrO', 'sam@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('sam', 'USER');
INSERT INTO users (username, password, email)
VALUES ('mark', '$2a$12$l7rTl2TJhGIKQuONtHEBvuYQY3VB9ZYxyBkUO9/Cft1vd5fTe5KrO', 'mark@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('mark', 'USER');

INSERT INTO users (username, password, email)
VALUES ('ans', '$2a$12$jx9SHj7xINWKVSpERMLZ8uqo0RAU5uH9s8B4LmNhXT9Bylfc5b/.S', 'ans@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('ans', 'ADMIN');
INSERT INTO users (username, password, email)
VALUES ('robert', '$2a$12$MxIOa9kyHn2tkZetYjfq9et9CBFsEZxGAqU4EdTKFNT5R.H9fM5OS', 'robert@mail.nl');
INSERT INTO authorities (username, authority)
VALUES ('robert', 'ADMIN');

INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('henk', '100', 'henk', 'hendriks', 'henklaan 1', '1211HH', 'henk@mail.nl', '06123456789');
INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('bart', '101', 'bart', 'bartman', 'bartlaan 10', '5124BB', 'bart@mail.nl', '06123456789');
INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('koen', '110', 'koen', 'mensink', 'koenlaan 11', '1121KK', 'koen@mail.nl', '06123456789');
INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('sam', '111', 'sam', 'barnhoorn', 'samstraat 15', '1151SS', 'sam@mail.nl', '06123456789');
INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('mark', '112', 'mark', 'rensen', 'markstraat 16', '1621MM', 'mark@mail.nl', '06123456789');

INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('ans', '200', 'ans', 'appelman', 'ansstraat 1', '2135AA', 'ans@mail.nl', '06123456789');
INSERT INTO accounts (username, id, first_name, last_name, address, zip_code, email, telephone_number)
VALUES ('robert', '201', 'robert', 'elias', 'robertstraat 1', '2458RR', 'robert@mail.nl', '06123456789');

UPDATE users SET account_id = '100'
WHERE username='henk';
UPDATE users SET account_id = '101'
WHERE username='bart';
UPDATE users SET account_id = '110'
WHERE username='koen';
UPDATE users SET account_id = '111'
WHERE username='sam';
UPDATE users SET account_id = '112'
WHERE username='mark';
UPDATE users SET account_id = '200'
WHERE username='ans';
UPDATE users SET account_id = '201'
WHERE username='robert';

INSERT INTO products (id, title) VALUES ('1001', 'paspoort id');
INSERT INTO products (id, title) VALUES ('1002', 'parkeervergunning');
INSERT INTO products (id, title) VALUES ('1003', 'subsidie');
INSERT INTO products (id, title) VALUES ('1004', 'toeslagen');
INSERT INTO products (id, title) VALUES ('1005', 'verhuizing');

INSERT INTO appointments (id, subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('10', 'paspoort', '2023-03-27', '13:00', '1001', '100');
INSERT INTO appointments (id, subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('11', 'verhuizing', '2023-03-28', '14:00', '1005', '100');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('12', 'parkeervergunning', '2023-03-29', '12:00', '1002', '101');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('13', 'subsidie', '2023-03-30', '10:00', '1003', '110');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('14', 'toeslagen', '2023-03-31', '13:00', '1004', '111');
INSERT INTO appointments (id,subject, appointment_date, appointment_time, product_id, account_id)
VALUES ('15', 'paspoort', '2023-05-01', '13:00', '1005', '112');

INSERT INTO contact_forms (id, email, first_name, last_name, message)
VALUES ('11', 'henk@mail.nl', 'henk', 'hendriks', 'ik ben heel tevreden over de code van de website');
INSERT INTO contact_forms (id, email, first_name, last_name, message)
VALUES ('12', 'bart@mail.nl', 'bart', 'bartman', 'de website is super overzichtelijk');



