CREATE TABLE `silverspin`.`order` (
  `id` VARCHAR(36) NOT NULL,
  `created_on` DATETIME(6) NOT NULL,
  `last_updated_on` DATETIME(6) NULL,
  `shipment` JSON NULL,
  `account_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (account_id) REFERENCES account(id)
);
