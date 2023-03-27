create table projects
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    updated datetime(6) null,
    project_name varchar(255) null,
    manager_name varchar(255)  null,
    location varchar(255)  null
);

create table employees
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    updated datetime(6) null,
    first_name varchar(255) null,
    last_name varchar(255) null,
    sex varchar(50) null,
    age int null,
    email varchar(255) null,
    job_title varchar(255) null
);

create table project_emp
(
    project_id bigint not null,
    emp_id bigint not null,
    primary key (project_id, emp_id),
    foreign key (project_id) references projects(id),
    foreign key (emp_id) references employees(id)
);