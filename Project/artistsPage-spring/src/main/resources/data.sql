-- to insert only if table is empty

INSERT INTO role (name) SELECT 'ADMIN' WHERE NOT EXISTS (SELECT * FROM role WHERE role.name='ADMIN');
INSERT INTO role (name) SELECT 'ARTIST' WHERE NOT EXISTS (SELECT * FROM role WHERE role.name='ARTIST');
INSERT INTO role (name) SELECT 'VISITOR' WHERE NOT EXISTS (SELECT * FROM role WHERE role.name='VISITOR');


--INSERT INTO role (name) VALUES ('ROLE_ADMIN');
--INSERT INTO role (name) VALUES ('ROLE_USER');
