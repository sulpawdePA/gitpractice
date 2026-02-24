-- Example schema for `test_report` table (Postgres)
CREATE TABLE IF NOT EXISTS test_report (
  id SERIAL PRIMARY KEY,
  name TEXT,
  status TEXT,
  details TEXT,
  timestamp TIMESTAMP
);
