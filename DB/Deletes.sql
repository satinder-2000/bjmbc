use `bjmbc`;

delete from REVENUE_ACCOUNT;
delete from REVENUE_PARTY;
delete from EXPENSE_ACCOUNT;
delete from EXPENSE_PARTY;
delete from USER;

alter table REVENUE_ACCOUNT auto_increment =1;
alter table REVENUE_PARTY auto_increment =1;
alter table EXPENSE_ACCOUNT auto_increment =1;
alter table EXPENSE_PARTY auto_increment =1;
alter table USER auto_increment =1;