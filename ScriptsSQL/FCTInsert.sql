INSERT INTO FCTALUMNOS VALUES ('70064578K','Juan','Gomez Lopez','dadad','Pinto','28341','Madrid','657843921','juangolo@gmail.com');
INSERT INTO FCTALUMNOS VALUES ('65437821L','Maria','Jimeno Rodriguez','Avenida Palomares 27 4ºE',' Valdemoro','28340','Madrid','654287659','maria_jimeno94@hotmail.com');
INSERT INTO FCTALUMNOS VALUES ('54782345Z','Fernando','Milan Arriba','c/ Plasencia 8',' Valdemoro','28342','Madrid','698574155','fermilan@gmail.com');
INSERT INTO FCTALUMNOS VALUES ('25864814P','Adrián','Ramirez Gomez',' c/ Tampico 87',' Madrid','28027', 'Madrid','612045878','adrian.ramirez.gomez@gmail.com');
INSERT INTO FCTALUMNOS VALUES ('45743837Y', 'Elena','Parado Linde', 'c/ Minerva 67 8ºA','Madrid', '28026', 'Madrid','658745874' , 'elenapl@orange.com');
INSERT INTO FCTALUMNOS VALUES ('53736958G', 'Marina ', 'Mendoza Canal', 'c/ Rio Tinto 56', 'Pinto', '28327', 'Madrid ', '658756432', ' marinusky@hotmail.com');
INSERT INTO FCTALUMNOS VALUES ('27854899R', 'Jose Luis', 'Garcia Gil', 'c/ Pepitoria 23 1ºA', 'Ciempozuelos', '28415', 'Madrid', '621556712', 'j_l.garcia@gmail.com');
INSERT INTO FCTALUMNOS VALUES ('78452598K', 'Paloma', 'Gil Lopez', 'c/ Arrieritos 34', 'Valdemoro', '28342' , 'Madrid', '647887569', 'palomitagl@gmail.com');
INSERT INTO FCTALUMNOS VALUES ('45879568H', ' Miguel', 'Roma Jimenez', 'c/ Amazonas 4', 'Valdemoro', '28342', ' Madrid', '615262689' , '   miguelr97@gmail.com');

INSERT INTO FCTCICLOS VALUES ('27578W','DAW','Informatica','00029372K ','Capacidades Terminales DAW ','Actividad Formativo-Productivas DAW,','Criterios de evaluación DAW');
INSERT INTO FCTCICLOS VALUES ('27578M','DAM','Informatica','00029372K ','Capacidades Terminales DAM ','Actividad Formativo-Productivas DAM,','Criterios de evaluación DAM');
INSERT INTO FCTCICLOS VALUES ('47632E','Educacion Infantil','Servicios Socioculturales y a la Comunidad','00002843S','Capacidades Terminales EI ','Actividad Formativo-Productivas EI,','Criterios de evaluación EI');

INSERT INTO FCTEMPRESAS VALUES ('1','Alberto Salzán Gallego','78459885X','J98675422','Equipamientos S.A','Glorieta del mar 34','28457','Madrid','Madrid','España','916584529','916584785','04/27/2012');
INSERT INTO FCTEMPRESAS VALUES ('2','Maite Justo Felto','58648511S','B85763491','Media Toledo S.L','c/ Pepita Oria 13 4ºD','04587','Illescas','Toledo','España','912569894','912569891',' 08/03/2016');
INSERT INTO FCTEMPRESAS VALUES ('3','Ines Collado Aspas','58745981L','B47859858','Pezqueñines S.A','c/Pimenton 56','28001','Madrid','Madrid','España','918745985','918745625','09/15/2018');
INSERT INTO FCTEMPRESAS VALUES ('4','Jaime Justo Fernandez','12548547X','B89563257','Calidad Mas S.A ','Calle de la Vega 7','28584','Madrid','Madrid','España','914587486','914587488','03/19/2010');

INSERT INTO FCTCENTROS VALUES ('28067847','COLEGIO VALLE DEL MIRO (ESPACIOS EDUCATIVOS S.C.M)','CLARA CAMPOAMOR Nº 2','VALDEMORO','28342','MADRID','918658070','918658164','F-83993279','OSCAR VEGA RODRÍGUEZ','52128282-R','DAT-SUR');

INSERT INTO FCTTUTORES_CENTRO VALUES ('45872695P','Jose Jimenez Mantero', 687458225, 'josejimenez@mariasalas.es','28067847');
INSERT INTO FCTTUTORES_CENTRO VALUES ('25685495N', 'Elena Redondo Guarda',614587455, 'elenaredondo@mariasalas.es','28067847');
INSERT INTO FCTTUTORES_CENTRO VALUES ('14752369P', 'Gema Liz Esteban',685423697, 'gemaliz@mariasalas.es','28067847');



INSERT INTO FCTTUTORES_EMPRESA VALUES ('25486985O','Galindo Gomez Gomez','668547515','albertoslazan@equipamientossa.com','1');
INSERT INTO FCTTUTORES_EMPRESA VALUES ('02358745X','Francisco Ruiz Cubo','678458741','f.ruiz.cubo@mediatoledo.es ','2');
INSERT INTO FCTTUTORES_EMPRESA VALUES ('58988863W','Luis Entero Mile','674152222','luis.entero@pezqueñines.es','3');
INSERT INTO FCTTUTORES_EMPRESA VALUES ('47857699J','David Robles Caravallo','625874574','davidrobles@calidadmas.com','4');

INSERT INTO FCTCURSAN VALUES ('2018/2019','70064578K','27578W');
INSERT INTO FCTCURSAN VALUES ('2018/2019','65437821L','27578M');
INSERT INTO FCTCURSAN VALUES ('2018/2019','54782345Z','47632E');
INSERT INTO FCTCURSAN VALUES ('2016/2017','25864814P','27578M');
INSERT INTO FCTCURSAN VALUES ('2018/2019','45743837Y','27578W');
INSERT INTO FCTCURSAN VALUES ('2018/2019','53736958G','47632E');
INSERT INTO FCTCURSAN VALUES ('2018/2019','27854899R','27578M');
INSERT INTO FCTCURSAN VALUES ('2018/2019','78452598K','27578W');

INSERT INTO FCTSUPERVISAN VALUES ('03/11/2019','06/06/2019','8','135','09:00','14:00','15:00','18:00','70064578K','45872695P','25486985O','1');
INSERT INTO FCTSUPERVISAN VALUES ('03/07/2019','06/02/2019','8','135','09:30','14:30','15:30','18:00','65437821L','25685495N','02358745X','2');
INSERT INTO FCTSUPERVISAN VALUES ('03/27/2019','06/22/2019','8','135','07:00','13:00','16:00','18:00','54782345Z','14752369P','58988863W','1');
INSERT INTO FCTSUPERVISAN VALUES ('11/10/2017','07/01/2017','8','250','09:00','14:00','15:00','18:00','25864814P','25685495N','47857699J','4');
INSERT INTO FCTSUPERVISAN VALUES ('11/10/2017','07/01/2017','8','250','09:00','14:00','15:00','18:00','45743837Y','25685495N','47857699J','4');
INSERT INTO FCTSUPERVISAN VALUES ('03/27/2019','06/22/2019','8','135','07:00','13:00','16:00','18:00','53736958G','14752369P','58988863W','3');
INSERT INTO FCTSUPERVISAN VALUES ('11/10/2017','07/01/2017','8','250','09:00','14:00','15:00','18:00','78452598K','25685495N','47857699J','4');
