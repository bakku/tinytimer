CREATE TABLE timers (
  id INTEGER NOT NULL PRIMARY KEY,
  path TEXT NOT NULL UNIQUE,
  date TEXT NOT NULL,
  caption TEXT NOT NULL,
  created_at text NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at text NOT NULL DEFAULT CURRENT_TIMESTAMP
) ;
