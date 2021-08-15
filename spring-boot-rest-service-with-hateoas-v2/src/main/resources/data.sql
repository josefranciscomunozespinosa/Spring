
insert into student values(10001,'Jose Francisco', 'E1234567');
insert into student values(10002,'Maria Angeles', 'A1234568');

insert into course values(1001,'Programación', 'Spring');
insert into course values(1002,'Inglés I', 'Inglés básico');
insert into course values(1003,'Inglés II', 'Inglés medio');
insert into course values(1004,'Inglés II', 'Inglés avanzado');

insert into student_course values (10001, 1001);
insert into student_course values (10001, 1003);
insert into student_course values (10002, 1001);
insert into student_course values (10002, 1004);