CREATE SEQUENCE IF NOT EXISTS employee_seq;

CREATE TABLE IF NOT EXISTS employee (

    employee_id BIGINT NOT NULL DEFAULT nextval('employee_seq') PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(12) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    image_data bytea,
    image_type VARCHAR(10),
    user_id BIGINT UNIQUE DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS user_account_seq;

CREATE TABLE IF NOT EXISTS user_account (
	user_id BIGINT NOT NULL DEFAULT nextval('user_account_seq') PRIMARY KEY,
	username varchar(255) UNIQUE NOT NULL,
	password varchar(255) NOT NULL,
	role varchar(255) NOT NULL DEFAULT 'ROLE_EMPLOYEE',
	enabled boolean NOT NULL DEFAULT TRUE,
    employee_id BIGINT UNIQUE DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS project_seq;

CREATE TABLE IF NOT EXISTS project (

    project_id BIGINT NOT NULL DEFAULT nextval('project_seq') PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stage VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL

);


CREATE TABLE IF NOT EXISTS project_employee (

    project_id BIGINT REFERENCES project, 
    employee_id BIGINT REFERENCES employee

);

ALTER TABLE user_account ADD CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE employee ADD CONSTRAINT fk_user_account FOREIGN KEY (user_id) REFERENCES user_account(user_id) DEFERRABLE INITIALLY DEFERRED;