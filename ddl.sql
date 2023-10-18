CREATE TABLE IF NOT EXISTS role
(
    id   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "user"
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_id  INTEGER REFERENCES role (id),
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE IF NOT EXISTS subject
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    short_name VARCHAR(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS faculty
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    short_name VARCHAR(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS classroom
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    number   INTEGER NOT NULL CHECK ( number >= 1 AND number <= 10000 ),
    building INTEGER NOT NULL CHECK ( building >= 1 AND building <= 10)
);

CREATE TABLE IF NOT EXISTS class_type
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type       VARCHAR(255) NOT NULL,
    short_type VARCHAR(3)   NOT NULL
);

CREATE TABLE IF NOT EXISTS teacher
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id    INTEGER REFERENCES "user" (id),
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    patronymic VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS teacher_subject
(
    teacher_id INTEGER REFERENCES teacher (id),
    subject_id INTEGER REFERENCES subject (id),
    PRIMARY KEY (teacher_id, subject_id)
);

CREATE TABLE IF NOT EXISTS class
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    classroom_id INTEGER REFERENCES classroom (id),
    teacher_id   INTEGER REFERENCES teacher (id),
    subject_id   INTEGER REFERENCES subject (id),
    type_id      INTEGER REFERENCES class_type (id),
    start_time   TIME(0) WITHOUT TIME ZONE NOT NULL,
    end_time     TIME(0) WITHOUT TIME ZONE NOT NULL,
    comment      VARCHAR(255)              NULL,
    week_numbers INTEGER[] CHECK (array_length(week_numbers, 1) <= 4),
    day_of_week  SMALLINT CHECK (day_of_week >= 1 AND day_of_week <= 7)
);

CREATE TABLE IF NOT EXISTS class_group
(
    class_id INTEGER REFERENCES class (id),
    group_id INTEGER REFERENCES class (id),
    PRIMARY KEY (class_id, group_id)
);

CREATE TABLE IF NOT EXISTS "group"
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    number     INTEGER NOT NULL,
    course     SMALLINT CHECK (course >= 1 AND course <= 6),
    faculty_id INTEGER REFERENCES faculty (id)
);

CREATE TABLE IF NOT EXISTS schedule
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    group_id INTEGER REFERENCES "group" (id),
    start    DATE NOT NULL,
    "end"    DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS logging
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     INTEGER REFERENCES "user" (id),
    action_time TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    action      VARCHAR(255)                   NOT NULL
);
