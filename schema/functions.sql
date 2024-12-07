create or replace
function vehicles_report(start_date date, end_date date)
returns table(registration_code varchar(15),
		vin varchar(50), model_name varchar(100), brand_name varchar(100),
		manufacture_year int, body_color varchar(30), category_code varchar(3),
		full_name varchar(255), date_of_registration date) as
$$
	select * from vehicles_info
	where date_of_registration >= start_date and
	date_of_registration <= end_date
	order by date_of_registration desc;
$$ language sql;

create or replace
function vehicles_report(driver_registration_code varchar(15))
returns table(registration_code varchar(15),
		vin varchar(50), model_name varchar(100), brand_name varchar(100),
		manufacture_year int, body_color varchar(30), category_code varchar(3),
		full_name varchar(255), date_of_registration date) as
$$
	select * from vehicles_info
	where registration_code = driver_registration_code;
$$ language sql;

create or replace
function violations_report(start_date date, end_date date)
returns table(registration_code varchar(15),
		description text,
		fine_amount numeric(8,2),
		date_of_violation date,
		date_of_payment date) as
$$
	select * from violations_info
	where date_of_violation >= start_date and
	date_of_payment <= end_date
    order by date_of_violation desc;
$$ language sql;

create or replace
function violations_report(driver_registration_code varchar(15))
	returns table(registration_code varchar(15),
		description text,
		fine_amount numeric(8,2),
		date_of_violation date,
		date_of_payment date) as
$$
	select * from violations_info
	where registration_code = driver_registration_code;
$$ language sql;

create or replace
function categories_report()
returns table(category_code varchar(3),
permitted_vehicle_name varchar(100),
minimal_age int) as
$$
	select * from categories_info
	order by category_code desc;
$$ language sql;

create or replace
function driver_licenses_report(start_date date, end_date date)
returns table(full_name varchar(255),
date_of_birth date,
registration_address varchar(255),
department_type varchar(100),
date_of_issue date,
date_of_expiry date) as
$$
	select * from licenses_info
	where date_of_issue >= start_date
	and date_of_expiry <= end_date
$$ language sql;