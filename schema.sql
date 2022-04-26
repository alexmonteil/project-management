CREATE SEQUENCE IF NOT EXISTS employee_seq;

CREATE TABLE IF NOT EXISTS employee (

    employee_id BIGINT NOT NULL DEFAULT nextval('employee_seq') PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(12) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    career_description VARCHAR(255) NOT NULL,
    image_data bytea,
    image_type VARCHAR(10),
    user_id BIGINT UNIQUE DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS user_account_seq;

CREATE TABLE IF NOT EXISTS user_account (
	user_id BIGINT NOT NULL DEFAULT nextval('user_account_seq') PRIMARY KEY,
	username varchar(255) UNIQUE NOT NULL,
	password varchar(255) NOT NULL,
	enabled boolean NOT NULL DEFAULT TRUE,
    employee_id BIGINT UNIQUE DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS role_seq;

CREATE TABLE IF NOT EXISTS role (
	role_id BIGINT NOT NULL DEFAULT nextval('role_seq') PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL DEFAULT 'ROLE_EMPLOYEE'
);


CREATE TABLE IF NOT EXISTS user_role (
    user_id BIGINT NOT NULL REFERENCES user_account (user_id),
    role_id BIGINT NOT NULL REFERENCES role (role_id)
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

    project_id BIGINT NOT NULL REFERENCES project (project_id), 
    employee_id BIGINT NOT NULL REFERENCES employee (employee_id)

);

CREATE SEQUENCE IF NOT EXISTS message_seq;

CREATE TABLE IF NOT EXISTS message (

    message_id BIGINT NOT NULL DEFAULT nextval('message_seq') PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(255) NOT NULL,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    user_id BIGINT NOT NULL REFERENCES user_account (user_id),
    project_id BIGINT NOT NULL REFERENCES project (project_id)
);

ALTER TABLE user_account ADD CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE employee ADD CONSTRAINT fk_user_account FOREIGN KEY (user_id) REFERENCES user_account(user_id) DEFERRABLE INITIALLY DEFERRED;