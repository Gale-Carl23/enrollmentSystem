# Project State

## Current Stage

Stage 1 — Project Foundation

## Completed Stages

None

## Current Architecture

Layered architecture:

JavaFX / FXML
    ↓
Controller
    ↓
Service
    ↓
Repository / DAO
    ↓
PostgreSQL

## Implemented Features

- Maven project foundation
- JavaFX application shell
- FXML-based UI
- CSS styling
- Basic navigation shell
- Application configuration foundation
- Logging foundation

## Database Status

PostgreSQL integration dependencies are prepared.

Database schema has not yet been implemented.

## Known Issues

None currently known.

## Tests

Stage 1 build and test verification pending.

## Git Checkpoint

Initial Git repository created.

Stage 1 checkpoint pending verification.

## Next Stage

Stage 2 — Database Foundation

## Important Architectural Decisions

- JavaFX instead of Swing
- FXML for UI structure
- CSS for presentation
- Layered architecture
- PostgreSQL as the database
- JDBC / DAO abstraction for initial persistence
- Flyway for database migrations
- Business logic belongs in services
- SQL belongs in repositories / DAOs
- Project is developed incrementally