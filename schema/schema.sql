-- TABLES
create table employee(
	employee_id serial primary key,
	full_name varchar(255) not null,
	date_of_birth date not null,
	date_of_hire date not null,
	job_title varchar(100) not null,
	department_id int not null,
	access_key varchar(500) not null
);

create table department(
	department_id serial primary key,
	department_location varchar(255) not null,
	department_type varchar(100) not null
);

create table driver(
	driver_id serial primary key,
	full_name varchar(255) not null,
	date_of_birth date not null,
	registration_address varchar(255) not null
);

create table driver_license(
	license_id serial primary key,
	driver_id int not null unique,
	department_id int not null,
	date_of_issue date not null,
	date_of_expiry date not null
);

create table driver_categories(
	link_id serial primary key,
	driver_id int not null,
	category_id int not null
);

create table vehicle_categories(
	category_id serial primary key,
	category_code varchar(3) not null,
	minimal_age int not null
);

create table categories_vehicles(
	link_id serial primary key,
	category_id int not null,
	permitted_vehicle_name varchar(100) not null
);

create table vehicle_passport(
	passport_id serial primary key,
	vin varchar(50) not null unique,
	model_id int not null,
	manufacture_year int not null,
	body_color varchar(30) not null,
	date_of_issue date not null
);

create table models_brands(
	model_id serial primary key,
	model_name varchar(100) not null,
	brand_name varchar(100) not null
);

create table vehicle_registration_certificate(
	certificate_id serial primary key,
	registration_code varchar(15) not null unique,
	passport_id int not null unique,
	driver_id int not null,
	category_id int not null,
	department_id int not null,
	date_of_registration date not null
);

create table traffic_fine(
	fine_id serial primary key,
	description text not null,
	fine_amount numeric(8, 2) not null
);

create table traffic_violations(
	violation_id serial primary key,
	registration_code varchar(15) not null,
	fine_id int not null,
	date_of_violation date not null,
	date_of_payment date
);

-- AUTHORIZATION SCHEMA
create table roles
(
    role_id serial primary key,
    name    varchar(50) not null
);

create table users
(
    user_id  bigserial primary key,
    username    varchar(30) unique not null,
    email    varchar(50) unique not null,
    password varchar(500) not null,
    license_id integer
);

create table users_roles
(
    user_id bigint not null references users,
    role_id integer not null references roles,
    primary key (user_id, role_id)
);

-- FOREIGN KEYS
alter table employee
add constraint fk_department_employee
foreign key (department_id)
references department(department_id);

alter table driver_categories
add constraint fk_driver_driver_categories
foreign key (driver_id)
references driver(driver_id);

alter table driver_categories
add constraint fk_vehicle_categories_driver_categories
foreign key (category_id)
references vehicle_categories(category_id);

alter table driver_license
add constraint fk_driver_driver_license
foreign key (driver_id)
references driver(driver_id);

alter table driver_license
add constraint fk_department_driver_license
foreign key (department_id)
references department(department_id);

alter table vehicle_passport
add constraint fk_model_vehicle_passport
foreign key (model_id)
references models_brands(model_id);

alter table vehicle_registration_certificate
add constraint fk_vehicle_passport_vehicle_registration_certificate
foreign key (passport_id)
references vehicle_passport(passport_id);

alter table vehicle_registration_certificate
add constraint fk_vehicle_categories_vehicle_registration_certificate
foreign key (category_id)
references vehicle_categories(category_id);

alter table vehicle_registration_certificate
add constraint fk_department_vehicle_registration_certificate
foreign key (department_id)
references department(department_id);

alter table vehicle_registration_certificate
add constraint fk_driver_vehicle_registration_certificate
foreign key (driver_id)
references driver(driver_id);

alter table traffic_violations
add constraint fk_traffic_fine_traffic_violations
foreign key (fine_id)
references traffic_fine(fine_id);

alter table traffic_violations
add constraint fk_registration_code_traffic_violations
foreign key (registration_code)
references vehicle_registration_certificate(registration_code);

-- CONSTRAINTS

alter table driver_license
add constraint date_check
check (date_of_issue < date_of_expiry);

alter table traffic_violations
add constraint date_check
check(date_of_violation <= date_of_payment);

alter table vehicle_passport
add constraint date_check
check(manufacture_year <= extract(year from date_of_issue));

alter table employee
add constraint date_check
check(date_of_birth < date_of_hire);

alter table employee
add constraint age_check
check(extract(year from date_of_birth) + 18 < extract(year from date_of_hire));

alter table traffic_fine
add constraint fine_amount_check
check(fine_amount >= 100.00 and fine_amount <= 30000.00);