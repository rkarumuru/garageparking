CREATE DATABASE garageparking;


USE garageparking;



CREATE TABLE `PARKED_VEHICLES` (
  `sessionId` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` varchar(100) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,`gateId` varchar(100) NOT NULL,
  `garageId` varchar(100) NOT NULL,
  `startTime` timestamp NULL DEFAULT NULL,
  `endTime` timestamp NULL DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
   `totalcharge` double NOT NULL,
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;



INSERT INTO PARKED_VEHICLES (tagId,
gateId,userId,
garageId,
startTime,
endTime,
status,
totalcharge
) VALUES('11111', '11111', 'kirantala','11111',NOW(), NOW(),'Open',6.00);



INSERT INTO PARKED_VEHICLES (tagId,
gateId,
userId,garageId,
startTime,
endTime,
status,
totalcharge
) VALUES('11111', '11111', 'naveen','11111',NOW(), NOW(),'Open',6.00);



CREATE TABLE BEACON_DETAILS
(
beaconId VARCHAR(100) NOT NULL PRIMARY KEY,
  
vehicleId VARCHAR(100),

 beanonType VARCHAR(10), userId VARCHAR(100), username VARCHAR(100),  
address VARCHAR(200));

INSERT INTO BEACON_DETAILS
 VALUES('11111', '11A2121', 'kirantala', 'U','Kiran Talasila','111 QUARTZ CRYSTAL PL, CARY NC 27519');

INSERT INTO BEACON_DETAILS
 VALUES('11112', '11A2212', 'naveen','U','Naveen Polavarapu','121 QUARTZ CRYSTAL PL, CARY NC 27513');
INSERT INTO BEACON_DETAILS
 VALUES('11113', '11A2121', 'kirantala','U','Kiran Talasila', '111 QUARTZ CRYSTAL PL, CARY NC 27519');
INSERT INTO BEACON_DETAILS
 VALUES('11114', '11A2121', 'naveen','U', 'Naveen Polavarapu','121 QUARTZ CRYSTAL PL, CARY NC 27513');
INSERT INTO BEACON_DETAILS
 VALUES('11115', '11A2121', 'garage_one','G', '','121 QUARTZ CRYSTAL PL, CARY NC 27513');
INSERT INTO BEACON_DETAILS
 VALUES('11116', '11A2121', 'garage_two','G', '','121 QUARTZ CRYSTAL PL, CARY NC 27513');

CREATE TABLE RATE_DETAILS
(gateBeaconId VARCHAR(100) NOT NULL PRIMARY KEY, garageId VARCHAR(100), gateId VARCHAR(100) NOT NULL,address1 VARCHAR(100),address2 VARCHAR(100), 
charge double NOT NULL,

 maxCharge double NOT NULL,  closingTime TIME);


INSERT INTO RATE_DETAILS
 VALUES('11115','11111','Gate 1', '230 N St.NW', 'Washington DC', 11.12, 30, '00:11:00');


INSERT INTO RATE_DETAILS
 VALUES('11116','11111','Gate 2', '240 N St.NW', 'Washington DC', 11.12, 30, '00:11:00');


