set names utf8mb4;
set charset utf8mb4;
drop database if exists personeel;
create database personeel charset utf8mb4;
use personeel;

CREATE TABLE jobtitels (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  naam varchar(50) NOT NULL unique,
  versie int unsigned NOT NULL DEFAULT 0
);

INSERT INTO jobtitels(naam) VALUES
('President'),
('Sale Manager (EMEA)'),
('Sales Manager (APAC)'),
('Sales Manager (NA)'),
('Sales Rep'),
('VP Marketing'),
('VP Sales');

CREATE TABLE werknemers (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  familienaam varchar(50) NOT NULL,
  voornaam varchar(50) NOT NULL,
  email varchar(100) NOT NULL unique,
  chefid int unsigned,
  jobtitelid int unsigned NOT NULL,
  salaris decimal(10,2) NOT NULL,
  paswoord varchar(100) NOT NULL,
  geboorte date NOT NULL,
  rijksregisternr bigint unsigned NOT NULL,
  versie int unsigned NOT NULL DEFAULT 0,
  CONSTRAINT werknemersJobtitels FOREIGN KEY (jobtitelid) REFERENCES jobtitels(id),
  CONSTRAINT werknemersWerknemers FOREIGN KEY (chefid) REFERENCES werknemers(id)
);

INSERT INTO werknemers(familienaam,voornaam,email,chefid,jobtitelid,salaris,paswoord,geboorte,rijksregisternr) VALUES
 ('Murphy','Diane','Diane.Murphy@toysforboys.com',NULL,1,3020,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1966-08-01',66080100153),
 ('Patterson','Mary','Mary.Patterson@toysforboys.com',1,7,2000,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1950-01-31',50013100129),
 ('Firrelli','Jeff','Jeff.Firrelli@toysforboys.com',1,6,2100,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1961-08-01',61080100145),
 ('Patterson','William','William.Patterson@toysforboys.com',2,3,1950,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1952-12-31',52123100151),
 ('Bondur','Gerard','Gerard.Bondur@toysforboys.com',2,2,1970,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1953-03-03',53030300173),
 ('Bow','Anthony','Anthony.Bow@toysforboys.com',2,4,1920,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1955-08-29',55082900149),
 ('Jennings','Leslie','Leslie.Jennings@toysforboys.com',6,5,1910,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1960-01-01',60010100172),
 ('Thompson','Leslie','Leslie.Thompson@toysforboys.com',6,5,1905,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1960-06-13',60061300138),
 ('Firrelli','Julie','Julie.Firrelli@toysforboys.com',6,5,1900,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1966-01-08',66010800185),
 ('Patterson','Steve','Steve.Patterson@toysforboys.com',6,5,1910,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1969-12-31',69123100120),
 ('Tseng','Foon Yue','Foon.Yue.Tseng@toysforboys.com',6,5,1904,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1970-06-14',70061400124),
 ('Vanauf','George','George.Vanauf@toysforboys.com',6,5,1905,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1955-02-02',55020200141),
 ('Bondur','Loui','Loui.Bondur@toysforboys.com',5,5,1909,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1954-08-08',54080800176),
 ('Hernandez','Gerard','Gerard.Hernandez@toysforboys.com',5,5,1908,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1952-03-14',52031400113),
 ('Castillo','Pamela','Pamela.Castillo@toysforboys.com',5,5,1903,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1966-03-03',66030300155),
 ('Bott','Larry','Larry.Bott@toysforboys.com',5,5,1902,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1963-08-08',63080800171),
 ('Jones','Barry','Barry.Jones@toysforboys.com',5,5,1903,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1964-11-11',64111100123),
 ('Fixter','Andy','Andy.Fixter@toysforboys.com',4,5,1907,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1972-05-07',72050700175),
 ('Marsh','Peter','Peter.Marsh@toysforboys.com',4,5,1905,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1967-11-29',67112900131),
 ('King','Tom','Tom.King@toysforboys.com',4,5,1904,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1973-09-01',73090100113),
 ('Nishi','Mami','Mami.Nishi@toysforboys.com',2,5,1900,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1974-01-31',74013100148),
 ('Kato','Yoshimi','Yoshimi.Kato@toysforboys.com',19,5,1901,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1975-02-15',75021500171),
 ('Gerard','Martin','Martin.Gerard@toysforboys.com',5,5,1902,'{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy','1962-09-17',62091700178);

create user if not exists cursist identified by 'cursist';
grant select on jobtitels to cursist;
grant select,update on werknemers to cursist;