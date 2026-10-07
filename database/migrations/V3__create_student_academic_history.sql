-- ============================================================
-- V3 - Student & Academic History
-- ============================================================


-- ============================================================
-- 1. STUDENTS
-- ============================================================

CREATE TABLE students (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    student_number VARCHAR(50) NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    suffix VARCHAR(20),

    date_of_birth DATE,

    program_id BIGINT NOT NULL,

    admission_date DATE NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_student_program
        FOREIGN KEY (program_id)
        REFERENCES programs(id)
);

CREATE INDEX idx_students_program_id
    ON students(program_id);

CREATE INDEX idx_students_status
    ON students(status);

    -- ============================================================
-- 2. STUDENT ACADEMIC HISTORY
-- ============================================================

CREATE TABLE student_academic_history (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    academic_period_id BIGINT NOT NULL,

    attempt_number INTEGER NOT NULL DEFAULT 1,

    final_grade NUMERIC(5, 2),

    result_status VARCHAR(30) NOT NULL,

    remarks TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_history_student
        FOREIGN KEY (student_id)
        REFERENCES students(id),

    CONSTRAINT fk_history_subject
        FOREIGN KEY (subject_id)
        REFERENCES subjects(id),

    CONSTRAINT fk_history_academic_period
        FOREIGN KEY (academic_period_id)
        REFERENCES academic_periods(id),

    CONSTRAINT chk_history_attempt_number
        CHECK (attempt_number > 0),

    CONSTRAINT chk_history_grade
        CHECK (
            final_grade IS NULL
            OR final_grade >= 0
        )
);

CREATE INDEX idx_history_student_id
    ON student_academic_history(student_id);

CREATE INDEX idx_history_subject_id
    ON student_academic_history(subject_id);

CREATE INDEX idx_history_academic_period_id
    ON student_academic_history(academic_period_id);

CREATE INDEX idx_history_student_subject
    ON student_academic_history(student_id, subject_id);