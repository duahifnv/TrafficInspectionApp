create view categories_info as
select category_code, permitted_vehicle_name, minimal_age
from vehicle_categories v join categories_vehicles as c
on v.category_id = c.category_id;

create view employees_info as
SELECT e.employee_id,
       e.full_name,
       e.job_title,
       d.department_type,
       d.department_location
FROM employee e
JOIN department d on d.department_id = e.department_id;

create view violations_info as
SELECT v.violation_id,
       v.registration_code,
       f.description,
       f.fine_amount,
       v.date_of_violation,
       v.date_of_payment
FROM traffic_violations v
         JOIN traffic_fine f ON f.fine_id = v.fine_id;

create view vehicles_info as
select registration_code, vin, m.model_name, m.brand_name,
p.manufacture_year, p.body_color, c.category_code, d.full_name, date_of_registration
from vehicle_registration_certificate vrc
join vehicle_passport as p on p.passport_id = vrc.passport_id
join models_brands as m on m.model_id = p.model_id
join vehicle_categories as c on c.category_id = vrc.category_id
join driver as d on d.driver_id = vrc.driver_id;

create view licenses_info as
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