-- edit FILENAME in create database to run script

create database PTCM
on primary (
	NAME='PTCM_DATA',
	FILENAME='E:\2014\JAV\PTCM\database\PTCM_DATA.MDF'
)
log on(
	NAME='PTCM_DATA_LOG',
	FILENAME='E:\2014\JAV\PTCM\database\PTCM_DATA.LDF'
);
use PTCM;
create table ptcm_staff(
	id int not null primary key,
	name nvarchar(50) not null,
	birthday date,
	address nvarchar(100),
	phone nvarchar(12)
);
create table ptcm_driver(
	id int not null primary key,
	name nvarchar(50) not null,
	birthday datetime,
	address nvarchar(100),
	phone nvarchar(12),
	license nvarchar(50)
);
-- alter table ptcm_driver alter column birthday datetime
create table ptcm_carowner(
	id int not null primary key,
	name nvarchar(50) not null,
	address nvarchar(100)
);
create table ptcm_cartype(
	id int not null primary key,
	name nvarchar(50)
);

create table ptcm_car(
	id int not null primary key,
	carowner int not null,
	load int,
	cartype int not null,
	carlicense nvarchar(50) not null
	constraint fk_car_owner foreign key(carowner) references ptcm_carowner(id),
	constraint fk_car_type foreign key(cartype) references ptcm_cartype(id)
);
create table ptcm_place(
	id int not null primary key,
	name nvarchar(100) not null,
	
);
create table ptcm_bus(
	id int not null primary key,
	dest int not null,
	source int not null,
	distance int,
	constraint F_K_BUS_PLACE_DST foreign key(dest) references ptcm_place(id),
	constraint F_K_BUS_PLACE_SRC foreign key(source) references ptcm_place(id),
);
create table ptcm_schedule(
	id int not null primary key,
	name nvarchar(100),
	car int not null,
	bus int not null,
	driver int not null,
	staff int not null,
	start datetime not null,
	finish datetime,
	constraint fk_schedule_car foreign key(car) references ptcm_car(id) ,
	constraint fk_schedule_driver foreign key(driver) references ptcm_driver(id) ,
	constraint fk_schedule_staff foreign key(staff) references ptcm_staff(id),
	constraint fk_schedule_bus foreign key(bus) references ptcm_bus(id),
	
);
go
create table ptcm_stationtype(
	id int not null primary key,
	name nvarchar(100)
);
go
create table ptcm_station(
	id int not null primary key,
	name nvarchar(50),
	type int,
	place int not null,
	constraint F_K_S_T foreign key(type) references ptcm_stationtype(id),
	constraint F_K_ST_PL foreign key(place) references ptcm_place(id)
);

go
create table ptcm_scheduleinstation(
	schedule int not null,
	station int not null,
	starttime datetime not null,
	stoptime datetime,
	constraint P_K primary key(schedule,station),
	constraint F_K_SI_SC foreign key(schedule) references ptcm_schedule(id),
	constraint F_K_SI_ST foreign key(station) references ptcm_station(id),
);
go;
create table ptcm_user(
	id int not null primary key,
	username varchar(64) not null,
	password varchar(100) not null
);

insert into ptcm_user values(1,'hieu','1234')
