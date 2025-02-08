create table department
(
    department_id       serial
        primary key,
    department_location varchar(255) not null,
    department_type     varchar(100) not null
);

alter table department
    owner to postgres;

create table driver
(
    driver_id            serial
        primary key,
    full_name            varchar(255) not null,
    date_of_birth        date         not null,
    registration_address varchar(255) not null
);

alter table driver
    owner to postgres;

create table driver_license
(
    license_id     serial
        primary key,
    driver_id      integer not null
        unique
        constraint fk_driver_driver_license
            references driver,
    department_id  integer not null
        constraint fk_department_driver_license
            references department,
    date_of_issue  date    not null,
    date_of_expiry date    not null,
    constraint date_check
        check (date_of_issue < date_of_expiry)
);

alter table driver_license
    owner to postgres;

create table vehicle_categories
(
    category_id   serial
        primary key,
    category_code varchar(3) not null,
    minimal_age   integer    not null
);

alter table vehicle_categories
    owner to postgres;

create table driver_categories
(
    link_id     serial
        primary key,
    driver_id   integer not null
        constraint fk_driver_driver_categories
            references driver,
    category_id integer not null
        constraint fk_vehicle_categories_driver_categories
            references vehicle_categories
);

alter table driver_categories
    owner to postgres;

create table categories_vehicles
(
    link_id                serial
        primary key,
    category_id            integer      not null,
    permitted_vehicle_name varchar(100) not null
);

alter table categories_vehicles
    owner to postgres;

create table models_brands
(
    model_id   serial
        primary key,
    model_name varchar(100) not null,
    brand_name varchar(100) not null
);

alter table models_brands
    owner to postgres;

create table vehicle_passport
(
    passport_id      serial
        primary key,
    vin              varchar(50) not null
        unique,
    model_id         integer     not null
        constraint fk_model_vehicle_passport
            references models_brands,
    manufacture_year integer     not null,
    body_color       varchar(30) not null,
    date_of_issue    date        not null,
    constraint date_check
        check ((manufacture_year)::numeric <= EXTRACT(year FROM date_of_issue))
);

alter table vehicle_passport
    owner to postgres;

create table vehicle_registration_certificate
(
    certificate_id       serial
        primary key,
    registration_code    varchar(15) not null
        unique,
    passport_id          integer     not null
        unique
        constraint fk_vehicle_passport_vehicle_registration_certificate
            references vehicle_passport,
    driver_id            integer     not null
        constraint fk_driver_vehicle_registration_certificate
            references driver,
    category_id          integer     not null
        constraint fk_vehicle_categories_vehicle_registration_certificate
            references vehicle_categories,
    department_id        integer     not null
        constraint fk_department_vehicle_registration_certificate
            references department,
    date_of_registration date        not null
);

alter table vehicle_registration_certificate
    owner to postgres;

create table traffic_fine
(
    fine_id     serial
        primary key,
    description text          not null,
    fine_amount numeric(8, 2) not null
        constraint fine_amount_check
            check ((fine_amount >= 100.00) AND (fine_amount <= 30000.00))
);

alter table traffic_fine
    owner to postgres;

create table traffic_violations
(
    violation_id      serial
        primary key,
    registration_code varchar(15) not null
        constraint fk_registration_code_traffic_violations
            references vehicle_registration_certificate (registration_code),
    fine_id           integer     not null
        constraint fk_traffic_fine_traffic_violations
            references traffic_fine,
    date_of_violation date        not null,
    date_of_payment   date,
    constraint date_check
        check (date_of_violation <= date_of_payment)
);

alter table traffic_violations
    owner to postgres;

create table roles
(
    role_id serial
        primary key,
    name    varchar(50) not null
);

alter table roles
    owner to postgres;

create table users
(
    user_id    bigserial
        primary key,
    username   varchar(30)  not null
        constraint users_phone_key
            unique,
    email      varchar(50)  not null
        unique,
    password   varchar(500) not null,
    license_id integer
);

alter table users
    owner to postgres;

create table users_roles
(
    user_id bigint  not null
        references users,
    role_id integer not null
        references roles,
    primary key (user_id, role_id)
);

alter table users_roles
    owner to postgres;

create table employee
(
    employee_id   serial
        primary key,
    full_name     varchar(255) not null,
    date_of_birth date         not null,
    date_of_hire  date         not null,
    job_title     varchar(100) not null,
    department_id integer      not null,
    access_key    varchar(500) not null
);

alter table employee
    owner to postgres;

create view categories_info(category_code, permitted_vehicle_name, minimal_age) as
SELECT v.category_code,
       c.permitted_vehicle_name,
       v.minimal_age
FROM vehicle_categories v
         JOIN categories_vehicles c ON v.category_id = c.category_id;

alter table categories_info
    owner to postgres;

create view vehicles_info
            (registration_code, vin, model_name, brand_name, manufacture_year, body_color, category_code, full_name,
             date_of_registration)
as
SELECT vrc.registration_code,
       p.vin,
       m.model_name,
       m.brand_name,
       p.manufacture_year,
       p.body_color,
       c.category_code,
       d.full_name,
       vrc.date_of_registration
FROM vehicle_registration_certificate vrc
         JOIN vehicle_passport p ON p.passport_id = vrc.passport_id
         JOIN models_brands m ON m.model_id = p.model_id
         JOIN vehicle_categories c ON c.category_id = vrc.category_id
         JOIN driver d ON d.driver_id = vrc.driver_id;

alter table vehicles_info
    owner to postgres;

create view users_info(username, email, name) as
SELECT u.username,
       u.email,
       r.name
FROM users_roles ur
         JOIN users u ON u.user_id = ur.user_id
         JOIN roles r ON r.role_id = ur.role_id;

alter table users_info
    owner to postgres;

create view violations_info
            (violation_id, registration_code, description, fine_amount, date_of_violation, date_of_payment) as
SELECT v.violation_id,
       v.registration_code,
       f.description,
       f.fine_amount,
       v.date_of_violation,
       v.date_of_payment
FROM traffic_violations v
         JOIN traffic_fine f ON f.fine_id = v.fine_id;

alter table violations_info
    owner to postgres;

create view licenses_info
            (license_id, full_name, date_of_birth, registration_address, department_type, date_of_issue,
             date_of_expiry) as
SELECT l.license_id,
       dr.full_name,
       dr.date_of_birth,
       dr.registration_address,
       dp.department_type,
       l.date_of_issue,
       l.date_of_expiry
FROM driver_license l
         JOIN driver dr ON dr.driver_id = l.driver_id
         JOIN department dp ON dp.department_id = l.department_id;

alter table licenses_info
    owner to postgres;

create view users_licenses_info(registration_code, username) as
SELECT c.registration_code,
       u.username
FROM vehicle_registration_certificate c
         JOIN driver_license l ON c.driver_id = l.driver_id
         LEFT JOIN users u ON l.license_id = u.license_id;

alter table users_licenses_info
    owner to postgres;

create view employees_info (employee_id, full_name, job_title, department_type, department_location) as
SELECT e.employee_id,
       e.full_name,
       e.job_title,
       d.department_type,
       d.department_location
FROM employee e
         JOIN department d ON d.department_id = e.department_id;

alter table employees_info
    owner to postgres;

create view certificate_info as
select certificate_id, registration_code, date_of_registration, full_name, vin,
       category_code, department_type, department_location
from vehicle_registration_certificate as vrc
         join vehicle_passport vp on vrc.passport_id = vp.passport_id
         join driver d on vrc.driver_id = d.driver_id
         join vehicle_categories dc on vrc.category_id = dc.category_id
         join department dp on vrc.department_id = dp.department_id;

alter table certificate_info
    owner to postgres;