CREATE TABLE IF NOT EXISTS
    SERVER_LOGS (uuid INTEGER IDENTITY PRIMARY KEY,id varchar(45), event_time timestamp, event_duration int,
     event_type varchar(40), event_host varchar (40), alert_flag BOOLEAN DEFAULT FALSE NOT NULL);