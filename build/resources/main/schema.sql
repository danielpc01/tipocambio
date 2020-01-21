CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `createdBy` VARCHAR(50) DEFAULT NULL,
  `modifiedBy` VARCHAR(50) DEFAULT NULL,
  `dateCreated` timestamp NULL DEFAULT NULL,
  `lastDateModified` timestamp NULL DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `UserSession` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `sessionDate` timestamp NOT NULL,
  `maxAge` int NOT NULL,
  `expired` bit(1) DEFAULT NULL,
  `createdBy` VARCHAR(50) DEFAULT NULL,
  `modifiedBy` VARCHAR(50) DEFAULT NULL,
  `dateCreated` timestamp NULL DEFAULT NULL,
  `lastDateModified` timestamp NULL DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_USER_SESSION_01`
    FOREIGN KEY (`userId`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `CurrencyType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `symbol` varchar(100) DEFAULT NULL,
    `createdBy` VARCHAR(50) DEFAULT NULL,
  `modifiedBy` VARCHAR(50) DEFAULT NULL,
  `dateCreated` timestamp NULL DEFAULT NULL,
  `lastDateModified` timestamp NULL DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ExchangeRate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` timestamp ,
  `currencyTypeId` bigint(20) NOT NULL,
  `currencyTypeTargetId` bigint(20) NOT NULL,

  `amount` decimal(10,4) NOT NULL,
   `active` bit(1),
  `createdBy` VARCHAR(50) DEFAULT NULL,
  `modifiedBy` VARCHAR(50) DEFAULT NULL,
  `dateCreated` timestamp NULL DEFAULT NULL,
  `lastDateModified` timestamp NULL DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EXCHANGE_RATE_01`
    FOREIGN KEY (`currencyTypeId`)
    REFERENCES `CurrencyType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
      CONSTRAINT `FK_EXCHANGE_RATE_02`
    FOREIGN KEY (`currencyTypeTargetId`)
    REFERENCES `CurrencyType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `ExchangeRateUsed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exchangeRateId` bigint(20) NOT NULL,
  `sessionId` bigint(20) NOT NULL,
  `createdBy` VARCHAR(50) DEFAULT NULL,
  `modifiedBy` VARCHAR(50) DEFAULT NULL,
  `dateCreated` timestamp NULL DEFAULT NULL,
  `lastDateModified` timestamp NULL DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EXCHANGE_RATE_USED_01`
    FOREIGN KEY (`exchangeRateId`)
    REFERENCES `ExchangeRate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);