CREATE TABLE courses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    credits INT NOT NULL,
    active BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE semesters (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE course_offerings (
    id BIGINT NOT NULL AUTO_INCREMENT,
    section_code VARCHAR(255) NOT NULL,
    capacity INT,
    status VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE enrollments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    enrollment_date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    grade DECIMAL(10, 2),
    PRIMARY KEY (id)
);

CREATE TABLE payments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    transaction_reference VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE lessons (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    lesson_order INT,
    content_url VARCHAR(255),
    duration_minutes INT,
    PRIMARY KEY (id)
);

CREATE TABLE assignments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    due_date DATE NOT NULL,
    max_score DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE submissions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    submitted_at DATETIME(6) NOT NULL,
    content TEXT NOT NULL,
    score DECIMAL(10, 2),
    feedback TEXT,
    PRIMARY KEY (id)
);