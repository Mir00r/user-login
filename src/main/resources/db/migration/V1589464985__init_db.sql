-- New Migration
DROP TABLE IF EXISTS `m_users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_users`
(
    `id`                      bigint(20)                              NOT NULL AUTO_INCREMENT,
    `created`                 datetime                                DEFAULT NULL,
    `deleted`                 bit(1)                                  NOT NULL,
    `last_updated`            datetime                                DEFAULT NULL,
    `uuid_str`                varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `account_non_expired`     bit(1)                                  NOT NULL,
    `account_non_locked`      bit(1)                                  NOT NULL,
    `credentials_non_expired` bit(1)                                  NOT NULL,
    `email`                   varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `enabled`                 bit(1)                                  NOT NULL,
    `password`                varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL,
    `phone`                   varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `username`                varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `active`                  int(11)                                 DEFAULT NULL,
    `created_by_id`           bigint(20)                              DEFAULT NULL,
    `updated_by_id`           bigint(20)                              DEFAULT NULL,
    `first_name`              varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `last_name`               varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKrauvhfwxl9xfjhvc2wc2foj9f` (`created_by_id`),
    KEY `FKluks1h3sxcrnepqqqa4s9orjf` (`updated_by_id`),
    CONSTRAINT `FKluks1h3sxcrnepqqqa4s9orjf` FOREIGN KEY (`updated_by_id`) REFERENCES `m_users` (`id`),
    CONSTRAINT `FKrauvhfwxl9xfjhvc2wc2foj9f` FOREIGN KEY (`created_by_id`) REFERENCES `m_users` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `m_users`
VALUES (1, '2020-02-02 16:36:40', _binary '\0', '2020-02-02 16:36:40', '13d8bb13-2f61-4ca6-8312-24d0a865bb04',
        _binary '', _binary '', _binary '', 'demo@example.com', _binary '',
        '$2a$10$rmS1EaoIG0mvSxJdT9JWQ.ADLgukO4QqLK3tJBHTdY1RCmtg0MWgW', '01710000000', 'admin', 1, NULL, NULL, 'Admin',
        NULL);


--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles`
(
    `id`            bigint(20)                              NOT NULL AUTO_INCREMENT,
    `created`       datetime                                DEFAULT NULL,
    `deleted`       bit(1)                                  NOT NULL,
    `last_updated`  datetime                                DEFAULT NULL,
    `uuid_str`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `name`          varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `description`   varchar(255)                            DEFAULT NULL,
    `restricted`    bit(1)                                  NOT NULL,
    `created_by_id` bigint(20)                              DEFAULT NULL,
    `updated_by_id` bigint(20)                              DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKolo7v6bxrho3bgjfh10i4y4lb` (`created_by_id`),
    KEY `FK95jx57baw7c39ybblooejidye` (`updated_by_id`),
    CONSTRAINT `FK95jx57baw7c39ybblooejidye` FOREIGN KEY (`updated_by_id`) REFERENCES `m_users` (`id`),
    CONSTRAINT `FKolo7v6bxrho3bgjfh10i4y4lb` FOREIGN KEY (`created_by_id`) REFERENCES `m_users` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `roles`
--

INSERT INTO `roles`
VALUES (1, '2020-02-02 16:36:39', _binary '\0', '2020-02-02 16:36:39', '23c44a59-5f40-48ff-a9a8-dcc1e7e5b244', 'ADMIN',
        'Admin role',
        _binary '', NULL, NULL),

       (2, '2020-02-02 16:36:40', _binary '\0', '2020-02-02 16:36:40', '31bc038e-2e99-4106-856f-da82b9fd5a33', 'USER',
        'User role',
        _binary '\0', NULL, NULL);


DROP TABLE IF EXISTS `m_users_roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_users_roles`
(
    `user_id`  bigint(20) NOT NULL,
    `roles_id` bigint(20) NOT NULL,
    KEY `FK5hwrg9mw7hmdq8cn63mh7sx0j` (`roles_id`),
    KEY `FK143dfv8wd8b2w93gbvd84ps5k` (`user_id`),
    CONSTRAINT `FK143dfv8wd8b2w93gbvd84ps5k` FOREIGN KEY (`user_id`) REFERENCES `m_users` (`id`),
    CONSTRAINT `FK5hwrg9mw7hmdq8cn63mh7sx0j` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_users_roles`
--

INSERT INTO `m_users_roles`
VALUES (1, 1);

--
-- Table structure for table `privileges`
--

DROP TABLE IF EXISTS `privileges`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privileges`
(
    `id`            bigint(20)                              NOT NULL AUTO_INCREMENT,
    `created`       datetime                                DEFAULT NULL,
    `deleted`       bit(1)                                  NOT NULL,
    `last_updated`  datetime                                DEFAULT NULL,
    `uuid_str`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `label`         varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `name`          varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `description`   varchar(255)                            DEFAULT NULL,
    `created_by_id` bigint(20)                              DEFAULT NULL,
    `updated_by_id` bigint(20)                              DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKewlbnm1g24vnjeuxlsk681k7j` (`created_by_id`),
    KEY `FKj8t6kkmks6qe3dluky108cku` (`updated_by_id`),
    CONSTRAINT `FKewlbnm1g24vnjeuxlsk681k7j` FOREIGN KEY (`created_by_id`) REFERENCES `m_users` (`id`),
    CONSTRAINT `FKj8t6kkmks6qe3dluky108cku` FOREIGN KEY (`updated_by_id`) REFERENCES `m_users` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privileges`
--

INSERT INTO `privileges`
VALUES (1, '2020-02-02 16:36:39', _binary '\0', '2020-02-02 16:36:39', '2283968a-94c3-48d2-8148-64964fa8c33d',
        'Administration', 'ADMINISTRATION', 'Administration work', NULL, NULL),
       (2, '2020-02-02 16:36:39', _binary '\0', '2020-02-02 16:36:39', 'ddeaf964-67a0-4187-8fc7-2ed745416e55',
        'Access User Resources', 'ACCESS_USER_RESOURCES', 'User work', NULL, NULL);


--
-- Table structure for table `roles_privileges`
--

DROP TABLE IF EXISTS `roles_privileges`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_privileges`
(
    `role_id`      bigint(20) NOT NULL,
    `privilege_id` bigint(20) NOT NULL,
    KEY `FK5duhoc7rwt8h06avv41o41cfy` (`privilege_id`),
    KEY `FK629oqwrudgp5u7tewl07ayugj` (`role_id`),
    CONSTRAINT `FK5duhoc7rwt8h06avv41o41cfy` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`),
    CONSTRAINT `FK629oqwrudgp5u7tewl07ayugj` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_privileges`
--

INSERT INTO `roles_privileges`
VALUES (1, 1),
       (1, 2),
       (2, 2);
--
-- Table structure for table `privileges_access_urls`
--

DROP TABLE IF EXISTS `privileges_access_urls`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privileges_access_urls`
(
    `privilege_id` bigint(20) NOT NULL,
    `access_urls`  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    KEY `FKp123f0u3yvp9ygtxbc1kmff5d` (`privilege_id`),
    CONSTRAINT `FKp123f0u3yvp9ygtxbc1kmff5d` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

