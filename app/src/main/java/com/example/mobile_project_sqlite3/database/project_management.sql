sqlite3 project_management.db
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT,
    email TEXT UNIQUE NOT NULL,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS projects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    start_date TEXT,
    end_date TEXT,
    status TEXT,
    user_id INTEGER
);

-- Create Tasks table
CREATE TABLE IF NOT EXISTS tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    start_date TEXT,
    end_date TEXT,
    is_completed INTEGER, -- SQLite uses 0/1 for boolean
    status TEXT,
    project_id INTEGER,
    FOREIGN KEY (project_id) REFERENCES projects(id)
);
INSERT INTO users (first_name, last_name, email, username, password) VALUES 
('John', 'Doe', 'john.doe@example.com', 'johndoe', 'password123'),
('Jane', 'Smith', 'jane.smith@example.com', 'janesmith', 'securepass'),
('Robert', 'Johnson', 'robert.j@example.com', 'robj', 'mysecret'),
('Emily', 'Williams', 'emily.w@example.com', 'emilyw', 'emilyspass'),
('Michael', 'Brown', 'michael.b@example.com', 'mikebrown', 'mikepass'),
('Sarah', 'Davis', 'sarah.d@example.com', 'sarahd', 'sarah123'),
('David', 'Miller', 'david.m@example.com', 'davidm', 'davidpass'),
('Jessica', 'Wilson', 'jessica.w@example.com', 'jessw', 'jesspass'),
('Thomas', 'Moore', 'thomas.m@example.com', 'tommoore', 'thomas123'),
('Jennifer', 'Taylor', 'jennifer.t@example.com', 'jennt', 'jennypass');
-- Insert 10 sample projects
INSERT INTO projects (name, description, start_date, end_date, status, user_id) VALUES
('Mobile App Development', 'Build a cross-platform mobile application', '2023-01-01', '2023-06-30', 'IN_PROGRESS', 1),
('Website Redesign', 'Modernize the company website', '2023-02-15', '2023-05-15', 'PLANNED', 1),
('Database Migration', 'Migrate from MySQL to PostgreSQL', '2023-03-01', '2023-04-30', 'COMPLETED', 1),
('Marketing Campaign', 'Q2 product marketing campaign', '2023-04-01', '2023-06-30', 'IN_PROGRESS',1),
('Employee Training', 'New hire onboarding program', '2023-01-15', '2023-02-28', 'COMPLETED', 1),
('Product Research', 'Market analysis for new product line', '2023-05-01', '2023-07-31', 'PLANNED',1),
('Security Audit', 'Annual system security review', '2023-06-01', '2023-06-30', 'PLANNED', 1),
('Customer Portal', 'Build self-service customer portal', '2023-03-15', '2023-09-30', 'IN_PROGRESS', 1),
('API Development', 'Create public REST API for partners', '2023-02-01', '2023-04-15', 'COMPLETED', 1),
('Mobile Game', 'Develop casual mobile game', '2023-07-01', '2023-12-31', 'PLANNED', 1);

-- Insert 10 sample tasks
INSERT INTO tasks (name, description, start_date, end_date, is_completed, status, project_id) VALUES
('UI Design', 'Design app screens and user flows', '2023-01-01', '2023-01-15', 1, 'COMPLETED', 1),
('Backend Setup', 'Configure server and database', '2023-01-10', '2023-01-20', 1, 'COMPLETED', 1),
('User Authentication', 'Implement login and registration', '2023-01-18', '2023-01-31', 0, 'IN_PROGRESS', 1),
('Wireframing', 'Create website wireframes', '2023-02-15', '2023-02-22', 0, 'PLANNED', 2),
('Content Migration', 'Move existing content to new CMS', '2023-03-01', '2023-03-10', 1, 'COMPLETED', 3),
('Schema Design', 'Design new database schema', '2023-03-05', '2023-03-15', 1, 'COMPLETED', 3),
('Social Media Ads', 'Create social media ad content', '2023-04-01', '2023-04-15', 0, 'IN_PROGRESS', 4),
('Training Materials', 'Prepare employee training docs', '2023-01-15', '2023-01-25', 1, 'COMPLETED', 5),
('Competitor Analysis', 'Research competitor products', '2023-05-01', '2023-05-15', 0, 'PLANNED', 6),
('Penetration Testing', 'Conduct security penetration tests', '2023-06-01', '2023-06-15', 0, 'PLANNED', 7);