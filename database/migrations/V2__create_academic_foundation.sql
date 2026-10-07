-- ============================================================
-- V2 - Academic Foundation
-- School Enrollment Management System
-- ============================================================


-- ============================================================
-- 1. DEPARTMENTS
-- ============================================================

CREATE TABLE departments (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    description TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ============================================================
-- 2. PROGRAMS
-- ============================================================

CREATE TABLE programs (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    department_id BIGINT NOT NULL,

    code VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    description TEXT,

    duration_years INTEGER NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_program_department
        FOREIGN KEY (department_id)
        REFERENCES departments(id),

    CONSTRAINT chk_program_duration
        CHECK (duration_years > 0)
);

CREATE INDEX idx_programs_department_id
    ON programs(department_id);


-- ============================================================
-- 3. SUBJECTS
-- ============================================================

CREATE TABLE subjects (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    code VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    description TEXT,

    units NUMERIC(4, 2) NOT NULL,
    lecture_hours NUMERIC(4, 2) NOT NULL DEFAULT 0,
    laboratory_hours NUMERIC(4, 2) NOT NULL DEFAULT 0,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_subject_units
        CHECK (units >= 0),

    CONSTRAINT chk_subject_lecture_hours
        CHECK (lecture_hours >= 0),

    CONSTRAINT chk_subject_laboratory_hours
        CHECK (laboratory_hours >= 0)
);


-- ============================================================
-- 4. CURRICULA
-- ============================================================

CREATE TABLE curricula (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    program_id BIGINT NOT NULL,

    code VARCHAR(50) NOT NULL,
    name VARCHAR(200) NOT NULL,
    version VARCHAR(50) NOT NULL,

    effective_from DATE NOT NULL,
    effective_to DATE,

    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curriculum_program
        FOREIGN KEY (program_id)
        REFERENCES programs(id),

    CONSTRAINT uq_curriculum_program_version
        UNIQUE (program_id, version),

    CONSTRAINT chk_curriculum_effective_dates
        CHECK (
            effective_to IS NULL
            OR effective_to >= effective_from
        )
);

CREATE INDEX idx_curricula_program_id
    ON curricula(program_id);


-- ============================================================
-- 5. CURRICULUM SUBJECTS
-- ============================================================

CREATE TABLE curriculum_subjects (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    curriculum_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,

    year_level INTEGER NOT NULL,
    semester INTEGER NOT NULL,

    subject_type VARCHAR(30) NOT NULL,
    is_required BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curriculum_subject_curriculum
        FOREIGN KEY (curriculum_id)
        REFERENCES curricula(id),

    CONSTRAINT fk_curriculum_subject_subject
        FOREIGN KEY (subject_id)
        REFERENCES subjects(id),

    CONSTRAINT uq_curriculum_subject
        UNIQUE (curriculum_id, subject_id),

    CONSTRAINT chk_curriculum_subject_year
        CHECK (year_level > 0),

    CONSTRAINT chk_curriculum_subject_semester
        CHECK (semester > 0)
);

CREATE INDEX idx_curriculum_subjects_curriculum_id
    ON curriculum_subjects(curriculum_id);

CREATE INDEX idx_curriculum_subjects_subject_id
    ON curriculum_subjects(subject_id);


-- ============================================================
-- 6. SUBJECT PREREQUISITES
-- ============================================================

CREATE TABLE subject_prerequisites (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    subject_id BIGINT NOT NULL,
    prerequisite_subject_id BIGINT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_prerequisite_subject
        FOREIGN KEY (subject_id)
        REFERENCES subjects(id),

    CONSTRAINT fk_prerequisite_required_subject
        FOREIGN KEY (prerequisite_subject_id)
        REFERENCES subjects(id),

    CONSTRAINT uq_subject_prerequisite
        UNIQUE (subject_id, prerequisite_subject_id),

    CONSTRAINT chk_subject_not_own_prerequisite
        CHECK (subject_id <> prerequisite_subject_id)
);

CREATE INDEX idx_subject_prerequisites_subject_id
    ON subject_prerequisites(subject_id);

CREATE INDEX idx_subject_prerequisites_required_subject_id
    ON subject_prerequisites(prerequisite_subject_id);


-- ============================================================
-- 7. ACADEMIC PERIODS
-- ============================================================

CREATE TABLE academic_periods (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    academic_year VARCHAR(20) NOT NULL,
    term VARCHAR(30) NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'UPCOMING',

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_academic_period
        UNIQUE (academic_year, term),

    CONSTRAINT chk_academic_period_dates
        CHECK (end_date >= start_date)
);

CREATE INDEX idx_academic_periods_status
    ON academic_periods(status);