DROP TABLE IF EXISTS QUESTION;

CREATE TABLE QUESTION
(
    ID               varchar(45) NOT NULL,
    QUESTION         longtext    NOT NULL,
    NUMBER_OF_OPTION int(6) DEFAULT '0',
    IS_COMPULSORY    tinyint(1) DEFAULT '0',
    CREATED_TIME     timestamp   NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS QUESTION_DATA_ENTRY_OPTION;

CREATE TABLE QUESTION_DATA_ENTRY_OPTION
(
    ID                        varchar(45)  NOT NULL,
    QUESTION_ID               varchar(45)  NOT NULL,
    NAME                      varchar(500) NOT NULL,
    HAS_TEXT_INPUT            tinyint(1) DEFAULT '0',
    TEXT_INPUT_TITLE          varchar(1000) DEFAULT NULL,
    TEXT_ANSWER_SETTING_TYPE  enum('Max words','Max characters','Unlimited') DEFAULT NULL,
    TEXT_ANSWER_SETTING_VALUE varchar(10)   DEFAULT NULL,
    HAS_CALENDAR_INPUT        tinyint(1) DEFAULT '0',
    CALENDAR_SETTING_TYPE     enum('Year only','Month only','Month and Year only','Date, Month and Year only') DEFAULT NULL,
    HAS_TIME_INPUT            tinyint(1) DEFAULT '0',
    PRIMARY KEY (ID)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS QUESTION_DATA_ENTRY_OPTION_ANSWER;

CREATE TABLE QUESTION_DATA_ENTRY_OPTION_ANSWER
(
    ID                            varchar(45) NOT NULL,
    QUESTION_ID                   varchar(45) NOT NULL,
    QUESTION_DATA_ENTRY_OPTION_ID varchar(45) NOT NULL,
    ANSWER                        longtext    NOT NULL,
    DATE                          varchar(10) DEFAULT NULL,
    TIME                          varchar(8)  DEFAULT NULL,
    CREATED_TIME                  timestamp   NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB;
