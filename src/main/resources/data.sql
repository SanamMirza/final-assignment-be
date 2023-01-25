INSERT INTO users (username, password, api_key, email)
VALUES ('henk', 'password', '12345678', 'henk@mail.com');
INSERT INTO authorities (username, authority)
VALUES ('henk', 'AUTHORITY_USER');

INSERT INTO users (username, password, api_key, email)
VALUES ('ans', 'peer', '789456123', 'ans@mail.com');
INSERT INTO authorities (username, authority)
VALUES ('ans', 'AUTHORITY_ADMIN');

INSERT INTO appointment (subject, appointment_date, appointment_time)
VALUES ('paspoort aanvragen', '01-01-2023', '13:00');

INSERT INTO account (first_name, last_name, address, email_address, telephone_number)
VALUES ('henk', 'hendriks', 'henklaan 1 1111 hh utrecht', 'henk@mail.com', '06123456789');
