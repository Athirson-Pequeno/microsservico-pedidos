INSERT INTO TB_USER (name, email, password) VALUES ('Athirson Pequeno', 'athirson@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO TB_USER (name, email, password) VALUES ('Karol Pequeno', 'karol@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');

INSERT INTO TB_ROLE (role_name) VALUES ('ROLE_USER');
INSERT INTO TB_ROLE (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES (2, 1);
INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES (2, 2);