DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT  PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    email VARCHAR(250) DEFAULT NULL,
    department VARCHAR(250) DEFAULT NULL,
    designation VARCHAR(250) DEFAULT NULL,
    salary DECIMAL(10,2) DEFAULT NULL
);


INSERT INTO employee (first_name, last_name, email, department, designation, salary)
VALUES ('Aarav', 'Sharma', 'aarav.sharma@example.com', 'IT', 'Software Engineer', 75000.0),
       ('Ishita', 'Verma', 'ishita.verma@example.com', 'Finance', 'Accountant', 65000.0),
       ('Aditya', 'Mehta', 'aditya.mehta@example.com', 'HR', 'HR Manager', 80000.0),
       ('Priya', 'Nair', 'priya.nair@example.com', 'IT', 'Data Analyst', 70000.0),
       ('Karan', 'Patel', 'karan.patel@example.com', 'Marketing', 'Marketing Executive', 60000.0),
       ('Nisha', 'Reddy', 'nisha.reddy@example.com', 'Operations', 'Operations Manager', 85000.0),
       ('Raj', 'Gupta', 'raj.gupta@example.com', 'IT', 'Full Stack Developer', 90000.0),
       ('Sneha', 'Jain', 'sneha.jain@example.com', 'Sales', 'Sales Manager', 72000.0),
       ('Ananya', 'Kumar', 'ananya.kumar@example.com', 'Finance', 'Financial Analyst', 68000.0),
       ('Rohan', 'Das', 'rohan.das@example.com', 'IT', 'DevOps Engineer', 78000.0),
       ('Simran', 'Singh', 'simran.singh@example.com', 'HR', 'Recruiter', 55000.0),
       ('Aman', 'Chopra', 'aman.chopra@example.com', 'IT', 'Backend Developer', 73000.0),
       ('Meera', 'Pandey', 'meera.pandey@example.com', 'Finance', 'Auditor', 66000.0),
       ('Siddharth', 'Joshi', 'siddharth.joshi@example.com', 'Operations', 'Supply Chain Analyst', 62000.0),
       ('Pooja', 'Agarwal', 'pooja.agarwal@example.com', 'Marketing', 'Content Strategist', 61000.0),
       ('Arjun', 'Mishra', 'arjun.mishra@example.com', 'IT', 'Frontend Developer', 71000.0),
       ('Tanya', 'Desai', 'tanya.desai@example.com', 'HR', 'HR Coordinator', 52000.0),
       ('Vikram', 'Singh', 'vikram.singh@example.com', 'Operations', 'Logistics Manager', 83000.0),
       ('Sanya', 'Kapoor', 'sanya.kapoor@example.com', 'Finance', 'Tax Consultant', 67000.0),
       ('Harsh', 'Bhatia', 'harsh.bhatia@example.com', 'Sales', 'Business Development Manager', 74000.0);
