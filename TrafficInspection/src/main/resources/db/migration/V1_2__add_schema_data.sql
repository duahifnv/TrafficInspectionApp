-- STATIC DATA
insert into vehicle_categories
(category_code, minimal_age)
values
('А', 18),
('А1', 16),
('В', 18),
('ВЕ', 19),
('В1', 18),
('С', 18),
('СЕ', 19),
('С1', 18),
('С1Е', 18),
('D', 21),
('DE', 22),
('D1', 21),
('D1E', 21),
('M', 16),
('Tb', 21),
('Tm', 21);

insert into categories_vehicles
(category_id, permitted_vehicle_name)
values
(1, 'Мотоциклы'),
(2, 'Легкие мотоциклы'),
(3, 'Легковые авто и небольшие грузовые автомобили (до 3,5 тонн)'),
(4, 'Легковые авто с прицепом'),
(5, 'Трициклы, квадроциклы'),
(6, 'Грузовые автомобили (от 3,5 тонн)'),
(7, 'Грузовые автомобили с прицепом'),
(8, 'Средние грузовые автомобили (до 7,5 тонн)'),
(9, 'Средние грузовые автомобили с прицепом'),
(10, 'Автобусы'),
(11, 'Автобусы с прицепом'),
(12, 'Микроавтобусы'),
(13, 'Микроавтобусы c прицепом'),
(14, 'Мопеды'),
(15, 'Троллейбусы'),
(16, 'Трамваи');

insert into roles (name)
values('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_EMPLOYEE');

insert into vehicle_categories
(category_code, minimal_age)
values
('А', 18),
('А1', 16),
('В', 18),
('ВЕ', 19),
('В1', 18),
('С', 18),
('СЕ', 19),
('С1', 18),
('С1Е', 18),
('D', 21),
('DE', 22),
('D1', 21),
('D1E', 21),
('M', 16),
('Tb', 21),
('Tm', 21);

insert into categories_vehicles
(category_id, permitted_vehicle_name)
values
(1, 'Мотоциклы'),
(2, 'Легкие мотоциклы'),
(3, 'Легковые авто и небольшие грузовые автомобили (до 3,5 тонн)'),
(4, 'Легковые авто с прицепом'),
(5, 'Трициклы, квадроциклы'),
(6, 'Грузовые автомобили (от 3,5 тонн)'),
(7, 'Грузовые автомобили с прицепом'),
(8, 'Средние грузовые автомобили (до 7,5 тонн)'),
(9, 'Средние грузовые автомобили с прицепом'),
(10, 'Автобусы'),
(11, 'Автобусы с прицепом'),
(12, 'Микроавтобусы'),
(13, 'Микроавтобусы c прицепом'),
(14, 'Мопеды'),
(15, 'Троллейбусы'),
(16, 'Трамваи');

INSERT INTO models_brands (model_name, brand_name) VALUES
('Civic', 'Honda'),
('Accord', 'Honda'),
('Camry', 'Toyota'),
('Corolla', 'Toyota'),
('Model S', 'Tesla'),
('Model 3', 'Tesla'),
('Mustang', 'Ford'),
('F-150', 'Ford'),
('Impala', 'Chevrolet'),
('Malibu', 'Chevrolet'),
('Altima', 'Nissan'),
('Sentra', 'Nissan'),
('Elantra', 'Hyundai'),
('Sonata', 'Hyundai'),
('3 Series', 'BMW'),
('5 Series', 'BMW'),
('A4', 'Audi'),
('A6', 'Audi'),
('911', 'Porsche'),
('Cayenne', 'Porsche'),
('Model X', 'Tesla'),
('Wrangler', 'Jeep'),
('Cherokee', 'Jeep'),
('Kona', 'Hyundai'),
('RAV4', 'Toyota'),
('Q3', 'Audi'),
('C-Class', 'Mercedes-Benz'),
('E-Class', 'Mercedes-Benz'),
('X5', 'BMW'),
('G-Class', 'Mercedes-Benz'),
('Explorer', 'Ford'),
('Forte', 'Kia'),
('Optima', 'Kia'),
('Tiguan', 'Volkswagen'),
('Jetta', 'Volkswagen'),
('Sienna', 'Toyota'),
('Tundra', 'Toyota'),
('Ranger', 'Ford'),
('Charger', 'Dodge'),
('Challenger', 'Dodge'),
('Trailblazer', 'Chevrolet'),
('Blazer', 'Chevrolet'),
('Ascent', 'Subaru'),
('Outback', 'Subaru'),
('Civic Si', 'Honda'),
('Accord Hybrid', 'Honda'),
('Camaro', 'Chevrolet'),
('Ridgeline', 'Honda'),
('Q5', 'Audi'),
('XC90', 'Volvo'),
('C-HR', 'Toyota');

INSERT INTO department (department_location, department_type) VALUES
('Ростов-на-Дону, ул. Советская, д. 12', 'Дорожно-патрульная служба'),
('Таганрог, ул. Ленина, д. 25', 'Служба дорожной инспекции'),
('Шахты, ул. Гагарина, д. 5', 'Подразделение розыска автотранспорта'),
('Батайск, ул. Красная, д. 42', 'Дорожно-патрульная служба'),
('Азов, ул. Пушкина, д. 18', 'Служба дорожной инспекции'),
('Новошахтинск, ул. Победы, д. 8', 'Подразделение розыска автотранспорта'),
('Миллерово, ул. Вокзальная, д. 20', 'Подразделение розыска автотранспорта');

INSERT INTO driver (full_name, date_of_birth, registration_address) VALUES
('Иванов Иван Иванович', '1985-04-12', 'Ростов-на-Дону, ул. Пушкина, д. 10, кв. 5'),
('Петров Пётр Петрович', '1990-07-22', 'Таганрог, ул. Ленина, д. 45'),
('Сидорова Сидор Сидоровна', '1980-12-30', 'Шахты, ул. Гагарина, д. 15, кв. 2'),
('Кузнецов Алексей Вячеславович', '1995-01-05', 'Батайск, ул. Красная, д. 20'),
('Семенова Анна Александровна', '1988-10-20', 'Ростов-на-Дону, ул. Чехова, д. 25, кв. 3');

INSERT INTO employee (full_name, date_of_birth, date_of_hire, job_title, department_id, access_key) VALUES
('Иванов Иван Иванович', '1985-05-10', '2020-01-15', 'Начальник контрольного поста милиции', 1,
 '$2a$12$zaRKsXDsaoOz2ig13V5A4exlcF8HWDSDYNYspDZOSVSffyTKCt6y2'),
('Петрова Анна Сергеевна', '1990-08-22', '2019-04-12', 'Начальник смены', 2,
'$2a$12$jy.fJt2QR8qGr7uoJM3Ynu2vEel/nZpoehmVJx4ohZoxoobdoICla'),
('Сидоров Алексей Викторович', '1978-12-30', '2017-07-19', 'Старший инспектор по розыску', 3,
'$2a$12$lIgzonYEADFI8bshUQoGGuk6xsbvgTvmbt7H5f8iS9CgBIim3ZLem'),
('Федорова Мария Владимировна', '1982-02-15', '2021-03-09', 'Государственный инспектор дорожного надзора', 4,
'$2a$12$ZmhQu84BWc3g/80auFHC3Of5gC19DzJvFr84/khrbopmTCS3HloGS'),
('Кузнецов Сергей Александрович', '1995-09-05', '2022-06-25', 'Инспектор (ДПС)', 5,
'$2a$12$Dg6EawKzcQDQjNrznYEvhOwGDTR9tCPudjiYaF4tSCrsJKeMwu0r2'),
('Смирнова Елена Николаевна', '1980-03-29', '2015-12-01', 'Начальник смены', 6,
'$2a$12$.bJ2FPCnvxkKYuToTAckW.yH5n.ay6fPmLmDldXWdL29uE.XSWiNm'),
('Белов Максим Олегович', '1992-11-11', '2020-08-14', 'Государственный инспектор дорожного надзора', 7,
'$2a$12$IaDh1P8hs5EKykF/DeZBtuZz1lOyPC40snEYHAEHT13oSEKX72qRW'),
('Михайлова Дарья Андреевна', '1984-07-03', '2016-09-09', 'Инспектор (ДПС)', 1,
'$2a$12$njADOeD.z2NvadeRCSYcJ.hvxLEUTMQ8PJMK81f3/YAf57ZF30F4a'),
('Тихонов Степан Анатольевич', '1975-10-20', '2018-05-21', 'Старший инспектор по розыску', 6,
'$2a$12$oLPVVsqwv6klarQxmRnVaOm5XXFY7j3zV.0x5kggt1XG2vMz7C0d2'),
('Ковалёв Артем Валерьевич', '1994-06-15', '2019-07-10', 'Старший инспектор по розыску', 7,
'$2a$12$DLjK2RNrjySg6V3i4Sv1lutDP4pC5d4Pkb5LJOnI9zJNdOYALo2/2');
-- Номер сотрудника - ключ доступа
-- 1 - 0001
-- 2 - 0002
-- 3 - 0003
-- 4 - 0004
-- 5 - 0005
-- 6 - 0006
-- 7 - 0007
-- 8 - 0008
-- 9 - 0009
-- 10 - 0010

INSERT INTO driver_license (driver_id, department_id, date_of_issue, date_of_expiry) values
(1, 2, '2016-09-30', '2026-09-30'),
(2, 1, '2017-08-11', '2027-02-12'),
(4, 6, '2017-02-15', '2027-02-15'),
(5, 1, '2017-01-26', '2027-01-26'),
(3, 4, '2024-12-20', '2034-12-20');

insert into driver_categories (driver_id, category_id) values
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3);



INSERT INTO traffic_fine (description, fine_amount) VALUES
('Превышение скорости более чем на 20 км/ч', 1500.00),
('Проезд на красный свет', 3000.00),
('Нарушение правил парковки', 2000.00),
('Управление транспортным средством без прав', 5000.00),
('Нарушение правил перевозки детей', 3500.00),
('Неиспользование ремня безопасности', 1000.00),
('Выезд на встречную полосу', 4000.00),
('Нарушение правил обгона', 2500.00),
('Управление ТС в состоянии алкогольного опьянения', 15000.00),
('Управление ТС без страховки', 7000.00),
('Нарушение требований дорожных знаков', 1200.00),
('Несоблюдение дистанции', 1800.00),
('Пользование мобильным телефоном во время вождения', 2500.00),
('Организация дорожного движения (неправильное поведение на дороге)', 2000.00),
('Неправильное использование сигнала поворота', 1000.00);

-- NON STATIC DATA (TEMPLATE)
INSERT INTO vehicle_passport (vin, model_id, manufacture_year, body_color, date_of_issue) VALUES
('1HGCM82633A123456', 1, 2015, 'Красный', '2019-01-15'),
('1HGCM82633A654321', 2, 2018, 'Синий', '2020-02-18'),
('1HGCM82633B123456', 3, 2017, 'Белый', '2018-03-22'),
('1HGCM82633B654321', 15, 2016, 'Черный', '2019-04-10'),
('1HGCM82633C123456', 20, 2021, 'Черный', '2022-05-05'),
('1HGCM82633C654321', 10, 2020, 'Белый', '2023-06-15'),
('1HGCM82633D123456', 23, 2023, 'Серый', '2023-07-20');

insert into vehicle_registration_certificate
(registration_code, passport_id, driver_id, category_id, department_id, date_of_registration) values
('A358BCRUS161', 1, 2, 3, 1, '2020-01-18'),
('A123BCRUS061', 2, 1, 3, 1, '2023-06-17'),
('B456TXRUS161', 3, 2, 1, 7, '2021-11-20'),
('E789BARUS761', 4, 3, 6, 3, '2020-04-01'),
('C321BORUS061', 5, 1, 3, 1, '2024-01-15'),
('M654MMRUS161', 6, 4, 1, 6, '2024-02-12'),
('B987BCRUS761', 7, 5, 3, 7, '2024-05-28');

insert into traffic_violations(registration_code, fine_id, date_of_violation, date_of_payment) values
('A358BCRUS161', 9, '2023-06-10', '2023-07-17'),
('A358BCRUS161', 3, '2023-03-14', '2023-03-20'),
('B456TXRUS161', 5, '2021-02-24', '2021-03-01');

insert into traffic_violations(registration_code, fine_id, date_of_violation) values
('E789BARUS761', 10, '2022-10-13'),
('E789BARUS761', 12, '2023-11-16');

insert into users(username, email, password, license_id)
values ('+79012345678',
        'ivan@mail.ru',
        '$2a$12$2Xwwa3PnA0kiQ86OrqIEUuktHO/ji867x79.EeUCiYqK20LTragku',
        1),
       ('+79123456789',
        'petya@mail.ru',
        '$2a$12$Z0zuRmQm/Skjj99Wbm4WKu75HXgrpKlFDqftqjg3Y6XyTXZhfUH8y',
        2),
       ('+79234567890',
        'sidor@mail.ru',
        '$2a$12$5Zb2w8KOQOwgZGkjhSfMsuJjeI9zdWCuhivTvv7KuLE60E3LJjphS',
        3),
       ('+79345678901',
        'alex@mail.ru',
        '$2a$12$C1cBSyZXRNd/uwiyEM3VVOxWboMlZxe5fxSYLJXDkI.n/byCuCDmK',
        4),
       ('+79456789012',
        'anna@mail.ru',
        '$2a$12$s3cyrY4UUi4iIOUCa.6pX.vhh6Ys4JrRlsMberwGLMWHPeK2EvKY2',
        5),
        ('+79887040132',
        'max135603@gmail.com',
        '$2a$10$tRBUgrogWzR0XF.AeJIoy.MdlsiHxJMxfnkwsBbLd1JCM2kBW9mu.',
         null);

INSERT INTO users (username, email, password) VALUES
('0001', '0001@mail.ru', '$2a$12$zaRKsXDsaoOz2ig13V5A4exlcF8HWDSDYNYspDZOSVSffyTKCt6y2'),
('0002', '0002@mail.ru', '$2a$12$jy.fJt2QR8qGr7uoJM3Ynu2vEel/nZpoehmVJx4ohZoxoobdoICla'),
('0003', '0003@mail.ru', '$2a$12$lIgzonYEADFI8bshUQoGGuk6xsbvgTvmbt7H5f8iS9CgBIim3ZLem'),
('0004', '0004@mail.ru', '$2a$12$ZmhQu84BWc3g/80auFHC3Of5gC19DzJvFr84/khrbopmTCS3HloGS'),
('0005', '0005@mail.ru', '$2a$12$Dg6EawKzcQDQjNrznYEvhOwGDTR9tCPudjiYaF4tSCrsJKeMwu0r2'),
('0006', '0006@mail.ru', '$2a$12$.bJ2FPCnvxkKYuToTAckW.yH5n.ay6fPmLmDldXWdL29uE.XSWiNm'),
('0007', '0007@mail.ru', '$2a$12$IaDh1P8hs5EKykF/DeZBtuZz1lOyPC40snEYHAEHT13oSEKX72qRW'),
('0008', '0008@mail.ru', '$2a$12$njADOeD.z2NvadeRCSYcJ.hvxLEUTMQ8PJMK81f3/YAf57ZF30F4a'),
('0009', '0009@mail.ru', '$2a$12$oLPVVsqwv6klarQxmRnVaOm5XXFY7j3zV.0x5kggt1XG2vMz7C0d2'),
('0010', '0010@mail.ru', '$2a$12$DLjK2RNrjySg6V3i4Sv1lutDP4pC5d4Pkb5LJOnI9zJNdOYALo2/2');

insert into users_roles(user_id, role_id)
values (1, 1);

insert into users_roles(user_id, role_id)
values (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2);

INSERT INTO users_roles (user_id, role_id) VALUES
(7, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3);