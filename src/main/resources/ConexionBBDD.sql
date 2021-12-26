alter session set "_ORACLE_SCRIPT"=TRUE;

create user pDual IDENTIFIED by pDual
default tablespace users
temporary tablespace temp
quota unlimited on users;

grant create session to pDual;
grant create table to pDual;
grant create sequence to pDual;