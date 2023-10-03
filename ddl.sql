CREATE TABLE teacher
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    patronymic VARCHAR(50)
);

CREATE TABLE subject
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255) NOT NULL,
    short_name VARCHAR(10)  NOT NULL
);

CREATE TABLE faculty
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255) NOT NULL,
    short_name VARCHAR(10)  NOT NULL
);

CREATE TABLE classroom
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY,
    number   INTEGER NOT NULL CHECK ( number >= 1 AND number <= 10000 ),
    building INTEGER NOT NULL CHECK ( number >= 1 AND number <= 10)
);

CREATE TABLE class_type
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    type       VARCHAR(255) NOT NULL,
    short_type VARCHAR(3)   NOT NULL
);

CREATE TABLE teacher_subject
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    teacher_id INTEGER REFERENCES teacher (id),
    subject_id INTEGER REFERENCES subject (id)
);

CREATE TABLE role
(
    id   INTEGER GENERATED ALWAYS AS IDENTITY,
    role VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE class_group
(
    class_id INTEGER REFERENCES class (id),
    group_id INTEGER REFERENCES class (id),
    CONSTRAINT class_group_pk PRIMARY KEY (class_id, group_id)
);

CREATE TABLE class
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY,
    classroom_id INTEGER REFERENCES classroom (id),
    teacher_id   INTEGER REFERENCES teacher (id),
    subject_id   INTEGER REFERENCES subject (id),
    type_id      INTEGER REFERENCES class_type (id),
    start_time   TIME(0) WITHOUT TIME ZONE NOT NULL,
    end_time     TIME(0) WITHOUT TIME ZONE NOT NULL,
    comment      VARCHAR(255) NULL,
    week_numbers INTEGER[] CHECK (array_length(week_numbers, 1) <= 4),
    days_of_week INTEGER[] CHECK (array_length(week_numbers, 1) <= 7)
);

CREATE TABLE group
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    number     INTEGER NOT NULL,
    course     SMALLINT CHECK (course >= 1 AND course <= 6),
    faculty_id INTEGER REFERENCES faculty (id)
);

CREATE TABLE schedule
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY,
    group_id INTEGER REFERENCES group (id),
    start    DATE NOT NULL,
    end      DATE NOT NULL
);

CREATE TABLE user
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY,
    role_id    INTEGER REFERENCES role (id),
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    teacher_id INTEGER REFERENCES teacher (id)
);

ALTER TABLE user
    ADD CONSTRAINT CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$');

CREATE TABLE logging
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY,
    user_id     INTEGER REFERENCES user (id),
    action_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    action      VARCHAR(255) NOT NULL
);

