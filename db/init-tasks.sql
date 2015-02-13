CREATE TABLE IF NOT EXISTS users.tasks (
  user_ttid VARCHAR(255),
  task_ttid VARCHAR(255),
  task_state INT,
  task_description TEXT,
  task_metadata TEXT
);
CREATE INDEX idx_users_tasks_user_ttid ON users.tasks(user_ttid);
CREATE UNIQUE INDEX idx_users_tasks_task_ttid ON users.tasks(task_ttid);
CREATE UNIQUE INDEX idx_users_tasks_user_ttid_task_ttid ON users.tasks(user_ttid, task_ttid);

INSERT INTO users.tasks (user_ttid, task_ttid, task_state, task_description) VALUES ('mock-pat-thomas', 'mock-pat-thomas-task-1', 0, "Write some code.");
INSERT INTO users.tasks (user_ttid, task_ttid, task_state, task_description) VALUES ('mock-pat-thomas', 'mock-pat-thomas-task-2', 1, "Take a shower.");
INSERT INTO users.tasks (user_ttid, task_ttid, task_state, task_description) VALUES ('mock-pat-thomas', 'mock-pat-thomas-task-3', 1, "Feed the cats.");
INSERT INTO users.tasks (user_ttid, task_ttid, task_state, task_description) VALUES ('mock-pat-thomas', 'mock-pat-thomas-task-4', 2, "Eat a sandwich.");
INSERT INTO users.tasks (user_ttid, task_ttid, task_state, task_description) VALUES ('mock-pat-thomas', 'mock-pat-thomas-task-5', 0, "Buy brussels sprouts.");

CREATE TABLE IF NOT EXISTS users.taskboard_configuration (
  user_ttid VARCHAR(255),
  name VARCHAR(255),
  value VARCHAR(255)
);

INSERT INTO users.taskboard_configuration (user_ttid, name, value) VALUES ('mock-pat-thomas', 'states', '["To Do", "In Progress", "Done"]');
