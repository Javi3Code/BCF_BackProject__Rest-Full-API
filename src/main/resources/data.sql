
insert into Rules (rules_id,result_points,sign_Points,goalsbcf_points) 
values (NEXTVAL('hibernate_sequence'),6,1,3);

insert into team (team_name) 
values('Burgos C.F.'),
('C.D. Mirandés' ),
('C.D.Numancia'),
('Palencia'),
('Torrelodones'),
('Real Burgos'),
('C.D.Murcia'),
('U.D.Salamanca'),
('Tenerife'),
('Sestao River'),
('Cultural Leonesa'),
('C.D.Osasuna'),
('Ávila'),
('C.D.Logroñés');

insert into Player (player_id,player_nick,player_mail,player_totalpoints) 
values
(NEXTVAL('hibernate_sequence'),'Javi_0','j.code.initialize@gmail.com',18),
(NEXTVAL('hibernate_sequence'),'PePe_0','asdad@gmail.com',12),
(NEXTVAL('hibernate_sequence'),'erre_0','dsad@gmail.com',10),
(NEXTVAL('hibernate_sequence'),'Jose_0','asd@gmail.com',16),
(NEXTVAL('hibernate_sequence'),'Héctor_0','sda@gmail.com',17),
(NEXTVAL('hibernate_sequence'),'Rocío_0','asd@gmail.com',24),
(NEXTVAL('hibernate_sequence'),'David_0','a@gmail.com',22),
(NEXTVAL('hibernate_sequence'),'Alberto_0','a@gmail.com',10),
(NEXTVAL('hibernate_sequence'),'Andrés_0','f@gmail.com',21),
(NEXTVAL('hibernate_sequence'),'Diego_0','s@gmail.com',18),
(NEXTVAL('hibernate_sequence'),'Ángel_0','f@gmail.com',24),
(NEXTVAL('hibernate_sequence'),'Sergio_0','a@gmail.com',16),
(NEXTVAL('hibernate_sequence'),'Rigoberto_0','f@gmail.com',13),
(NEXTVAL('hibernate_sequence'),'Recaredo_0','f@gmail.com',11);

insert into Concrete_Match (concretematch_id,local,visitor,result) 
values
(NEXTVAL('hibernate_sequence'),'Burgos C.F.','Sestao River','2-2'),
(NEXTVAL('hibernate_sequence'),'Cultural Leonesa','Burgos C.F.','3-2'),
(NEXTVAL('hibernate_sequence'),'C.D.Logroñés','Burgos C.F.','3-2'),
(NEXTVAL('hibernate_sequence'),'Burgos C.F.','Tenerife','0-0'),
(NEXTVAL('hibernate_sequence'),'Ávila','Burgos C.F.','0-2'),
(NEXTVAL('hibernate_sequence'),'Burgos C.F.','Ávila','1-2'),
(NEXTVAL('hibernate_sequence'),'Burgos C.F.','Palencia',null);

insert into Player_Football_Match (pfm_id,pfm_result,pfm_sign,pfm_bcfgoals,player,match,pfm_matchpoints) 
values
(NEXTVAL('hibernate_sequence'),'2-1',1,2,2,22,0),
(NEXTVAL('hibernate_sequence'),'2-2',0,2,3,22,0),
(NEXTVAL('hibernate_sequence'),'2-3',2,2,4,22,0),
(NEXTVAL('hibernate_sequence'),'3-1',1,3,5,22,0),
(NEXTVAL('hibernate_sequence'),'1-1',0,1,6,22,0),
(NEXTVAL('hibernate_sequence'),'4-1',1,4,7,22,0),
(NEXTVAL('hibernate_sequence'),'0-1',2,0,8,22,0),
(NEXTVAL('hibernate_sequence'),'2-0',1,2,9,22,0),
(NEXTVAL('hibernate_sequence'),'0-1',2,0,10,22,0),
(NEXTVAL('hibernate_sequence'),'2-0',1,2,11,22,0),
(NEXTVAL('hibernate_sequence'),'3-4',2,3,12,22,0)
;




