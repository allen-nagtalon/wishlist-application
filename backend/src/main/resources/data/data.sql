BEGIN;

INSERT INTO users (user_id, username, email, first_name, last_name, image_url, account_non_locked, enabled, login_attempts) VALUES
(gen_random_uuid(), 'xg_jurin', 'asayajurin07@gmail.com' ,'Jurin', 'Asaya', 'https://kpopping.com/documents/c0/0/800/240219-XG-Twitter-Update-Jurin-documents-2.jpeg?v=482bf', true, true, 0),
(gen_random_uuid(), 'xg_maya', 'kawachimaya08@gmail.com' ,'Maya', 'Kawachi', 'https://kpopping.com/documents/ba/0/2744/221012-XG-Twitter-Update-Maya-documents-1.jpeg?v=bbab5', true, true, 0),
(gen_random_uuid(), 'xg_cocona', 'akiyamakokona09@gmail.com' ,'Cocona', 'Akiyama', 'https://kpopping.com/documents/34/1/1366/230211-XG-Cocona-Twitter-Update-documents-2.jpeg?v=73859', true, true, 0),
(gen_random_uuid(), 'xg_harvey', 'harveyamy10@gmail.com' ,'Amy', 'Harvey', 'https://pbs.twimg.com/media/Fc8fBvGagAIyMWg?format=jpg&name=large', true, true, 0),
(gen_random_uuid(), 'xg_juria', 'uedajuria11@gmail.com' ,'Juria', 'Ueda', 'https://kpopping.com/documents/7c/3/1080/240813-XG-Instagram-Update-with-JURIA-documents-3.jpeg?v=50274', true, true, 0),
(gen_random_uuid(), 'xg_chisa', 'kondouchisa12@gmail.com' ,'Chisa', 'Kondou', 'https://kpopping.com/documents/a2/4/800/230215-XG-Chisa-Twitter-Update-documents-1.jpeg?v=593d1', true, true, 0),
(gen_random_uuid(), 'xg_hinata', 'sorahahinata13@gmail.com' ,'Hinata', 'Soraha', 'https://kpopping.com/documents/98/1/800/220721-XG-Hinata-Twitter-Update-documents-1.jpeg?v=482bf', true, true, 0)
ON CONFLICT DO NOTHING;

INSERT INTO credentials (password, user_id) VALUES
('$2a$12$lGSIONvmB1gvKEyaO.BhYOYQbHJ4vU1UjyJ0dwbS2R1r8a.z0wibO', 1),
('$2a$09$HSsXVYdGRXnZ/CH/Zn5O3e2I6Sfr0xm22nS/5EC3NB1vAOwZLixQK', 2),
('$2a$10$C.nd0z3JC.TeGHjt6b5c3.mJasIulpe0Ez1UrOvRdXkPGNj7W/Ml.', 3),
('$2a$14$1SWToTUJMHyyEy8sRcCVweJnwOfly4kJ4LbVOwtiKHDjsZlyROI.C', 4),
('$2a$06$UiqONAW9G221Js9TWo4xY.IqWj7A4JE6xnye1wdnhHeBY/daJcChi', 5),
('$2a$06$dFDu1w9bZ5stteIYCeWU6uwglT89HbFBfWF92UC8bCVM3ECjznEAW', 6),
('$2a$06$0oYE/1Q42eVF7T1H7HWCeurJZJuzVtpam3Md3qW4NUVBDRx7U3iwe', 7)
ON CONFLICT DO NOTHING;

END;