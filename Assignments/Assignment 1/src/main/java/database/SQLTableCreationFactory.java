package database;

import static database.Constants.Tables.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table) {
        switch (table) {
            case TYPE:
                return "CREATE TABLE `type` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));\n";
            case HISTORY:
                return "CREATE TABLE `history` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `userId` INT NULL,\n" +
                        "  `action` VARCHAR(250) NULL,\n" +
                        "  `date` DATETIME NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  INDEX `user_action_idx` (`userId` ASC),\n" +
                        "  CONSTRAINT `user_action`\n" +
                        "    FOREIGN KEY (`userId`)\n" +
                        "    REFERENCES `user` (`id`)\n" +
                        "    ON DELETE NO ACTION\n" +
                        "    ON UPDATE NO ACTION);\n";
            case ACCOUNT:
                return "CREATE TABLE `account` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `typeId` INT NULL,\n" +
                        "  `ammount` DOUBLE NULL,\n" +
                        "  `created` DATETIME NULL,\n" +
                        "  `clientId` INT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  INDEX `account_type_idx` (`typeId` ASC),\n" +
                        "  INDEX `account_client_idx` (`clientId` ASC),\n" +
                        "  CONSTRAINT `account_type`\n" +
                        "    FOREIGN KEY (`typeId`)\n" +
                        "    REFERENCES `type` (`id`)\n" +
                        "    ON DELETE NO ACTION\n" +
                        "    ON UPDATE NO ACTION,\n" +
                        "  CONSTRAINT `account_client`\n" +
                        "    FOREIGN KEY (`clientId`)\n" +
                        "    REFERENCES `client` (`id`)\n" +
                        "    ON DELETE NO ACTION\n" +
                        "    ON UPDATE NO ACTION);\n";

            case CLIENT:
                return "CREATE TABLE `client` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NULL,\n" +
                        "  `card_number` INT(13) NULL,\n" +
                        "  `pnc` INT(13) NULL,\n" +
                        "  `address` VARCHAR(250) NULL,\n" +
                        "  `email` VARCHAR(45) NULL,\n" +
                        "  PRIMARY KEY (`id`));\n";
            case BILL:
                return "CREATE TABLE IF NOT EXISTS bill(\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `accountId` INT NULL,\n" +
                        "  `name` VARCHAR(45) NULL,\n" +
                        "  `ammount` DOUBLE NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  INDEX `bill_account_idx` (`accountId` ASC),\n" +
                        "  CONSTRAINT `bill_account`\n" +
                        "    FOREIGN KEY (`accountId`)\n" +
                        "    REFERENCES account (`id`)\n" +
                        "    ON DELETE NO ACTION\n" +
                        "    ON UPDATE NO ACTION);\n";
            case USER:
                return "CREATE TABLE IF NOT EXISTS user (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(200) NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX username_UNIQUE (username ASC));";

            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX role_UNIQUE (role ASC));";

            case RIGHT:
                return "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `right_UNIQUE` (`right` ASC));";

            case ROLE_RIGHT:
                return "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case USER_ROLE:
                return "\tCREATE TABLE IF NOT EXISTS user_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  user_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (user_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT user_fkid" +
                        "    FOREIGN KEY (user_id)" +
                        "    REFERENCES user (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            default:
                return "";

        }
    }

}
