insert into `CurrencyType` (`name`,`symbol`,`createdBy`,`dateCreated`,`enabled`) values ('Soles','S/','admin',CURRENT_TIMESTAMP(),1);
insert into `CurrencyType` (`name`,`symbol`,`createdBy`,`dateCreated`,`enabled`) values ('Dolares','$','admin',CURRENT_TIMESTAMP(),1);
insert into `CurrencyType` (`name`,`symbol`,`createdBy`,`dateCreated`,`enabled`) values ('Euros','€','admin',CURRENT_TIMESTAMP(),1);

insert into `ExchangeRate`(`date`,`currencyTypeId`,`currencyTypeTargetId`,`amount`,`active`,`createdBy`,`dateCreated`,`enabled`) values (CURRENT_TIMESTAMP(),1,2,3.45,1,'admin',CURRENT_TIMESTAMP(),1);
insert into `ExchangeRate`(`date`,`currencyTypeId`,`currencyTypeTargetId`,`amount`,`active`,`createdBy`,`dateCreated`,`enabled`) values (CURRENT_TIMESTAMP(),1,3,4,1,'admin',CURRENT_TIMESTAMP(),1);


insert into `User` (`name`,`userName`,`key`,`createdBy`,`dateCreated`,`enabled`) values ('Daniel Pena','dpena','123456','admin',CURRENT_TIMESTAMP(),1);
insert into `User` (`name`,`userName`,`key`,`createdBy`,`dateCreated`,`enabled`) values ('Luis Cueva','lcueva','654321','admin',CURRENT_TIMESTAMP(),1);
