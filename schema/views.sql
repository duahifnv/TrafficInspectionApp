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
select registration_code, description,
fine_amount, date_of_violation, date_of_payment
from traffic_violations v
join traffic_fine as f on f.fine_id = v.fine_id;

create view vehicles_info as
select registration_code, vin, m.model_name, m.brand_name,
p.manufacture_year, p.body_color, c.category_code, d.full_name, date_of_registration
from vehicle_registration_certificate vrc
join vehicle_passport as p on p.passport_id = vrc.passport_id
join models_brands as m on m.model_id = p.model_id
join vehicle_categories as c on c.category_id = vrc.category_id
join driver as d on d.driver_id = vrc.driver_id;

create view licenses_info as
select full_name, date_of_birth, registration_address,
department_type, date_of_issue, date_of_expiry
from driver_license l
join driver as dr on dr.driver_id = l.driver_id
join department as dp on dp.department_id = l.department_id;

create view users_info as
select phone, email, name
from users_roles ur
join users u on u.user_id = ur.user_id
join roles r on r.role_id = ur.role_id;