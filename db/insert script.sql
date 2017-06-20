--------------------------------------------------------
--  File created - Tuesday-June-20-2017   
--------------------------------------------------------
REM INSERTING into SHAREIT.T_ACTIVITY
SET DEFINE OFF;
Insert into SHAREIT.T_ACTIVITY (ID,ITEM,FROM_USER,TO_USER,MEETING_POINT,STATUS,TIME_FROM,TIME_TO,ACTIVITY_DELETED) values (42,33,13,61,'giza square',2,to_timestamp('20-JUN-17 03.27.03.538000000 PM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('08-DEC-29 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),0);
Insert into SHAREIT.T_ACTIVITY (ID,ITEM,FROM_USER,TO_USER,MEETING_POINT,STATUS,TIME_FROM,TIME_TO,ACTIVITY_DELETED) values (-7,61,64,61,'maadi , giza',3,to_timestamp('20-JUN-17 04.03.57.318000000 PM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('08-DEC-26 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),1);
Insert into SHAREIT.T_ACTIVITY (ID,ITEM,FROM_USER,TO_USER,MEETING_POINT,STATUS,TIME_FROM,TIME_TO,ACTIVITY_DELETED) values (-6,84,61,64,'masr el gdida - el t7awy',2,to_timestamp('20-JUN-17 04.47.55.642000000 PM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('08-DEC-29 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),0);
REM INSERTING into SHAREIT.T_ADDRESS
SET DEFINE OFF;
Insert into SHAREIT.T_ADDRESS (ID,COUNTRY,CITY,STATE) values (1,1,1,2);
Insert into SHAREIT.T_ADDRESS (ID,COUNTRY,CITY,STATE) values (2,1,2,2);
Insert into SHAREIT.T_ADDRESS (ID,COUNTRY,CITY,STATE) values (3,1,3,2);
Insert into SHAREIT.T_ADDRESS (ID,COUNTRY,CITY,STATE) values (4,2,4,1);
REM INSERTING into SHAREIT.T_BORROW_STATE
SET DEFINE OFF;
Insert into SHAREIT.T_BORROW_STATE (ID,ACTIVITY,IS_BACK) values (1,42,0);
Insert into SHAREIT.T_BORROW_STATE (ID,ACTIVITY,IS_BACK) values (2,-6,0);
REM INSERTING into SHAREIT.T_CATEGORY
SET DEFINE OFF;
Insert into SHAREIT.T_CATEGORY (ID,NAME,MAX_POINTS) values (63,'Kids & baby',200);
Insert into SHAREIT.T_CATEGORY (ID,NAME,MAX_POINTS) values (64,'Electronincs',1000);
Insert into SHAREIT.T_CATEGORY (ID,NAME,MAX_POINTS) values (62,'Home & Appliances',2000);
Insert into SHAREIT.T_CATEGORY (ID,NAME,MAX_POINTS) values (65,'Office & Education',100);
Insert into SHAREIT.T_CATEGORY (ID,NAME,MAX_POINTS) values (66,'Sports & Outdoors',300);
Insert into SHAREIT.T_CATEGORY (ID,NAME,MAX_POINTS) values (67,'Others',100000);
REM INSERTING into SHAREIT.T_CITY
SET DEFINE OFF;
Insert into SHAREIT.T_CITY (ID,CITY) values (1,'Cairo');
Insert into SHAREIT.T_CITY (ID,CITY) values (2,'Giza');
Insert into SHAREIT.T_CITY (ID,CITY) values (3,'Alex');
Insert into SHAREIT.T_CITY (ID,CITY) values (4,'Buffalo');
REM INSERTING into SHAREIT.T_COUNTRY
SET DEFINE OFF;
Insert into SHAREIT.T_COUNTRY (ID,COUNTRY) values (1,'Egypt');
Insert into SHAREIT.T_COUNTRY (ID,COUNTRY) values (2,'USA');
REM INSERTING into SHAREIT.T_GENDER
SET DEFINE OFF;
Insert into SHAREIT.T_GENDER (ID,GENDER) values (1,'male');
Insert into SHAREIT.T_GENDER (ID,GENDER) values (21,'female');
REM INSERTING into SHAREIT.T_ITEM
SET DEFINE OFF;
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (21,'Small decorative lamp',' Blown glass lamp from Ikea  *Only for decoration - not enough light for reading purposes.',62,1,to_timestamp('20-JUN-17 10.34.41.767000000 AM','DD-MON-RR HH.MI.SSXFF AM'),20,'C:\Users\Yousef\shareit\images\sharedItems\decorativeLamp.jpg',61,'home,decoration,good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (32,'Hamilton Beach Blender','Some product features pasted below:  HMB1275 Features: Blender Stainless steel material Sure rest jar base Extra large 56 ounce jar 10 Speed blender  Specifications: 400 watt motor  Dimensions: Overall dimensions: 10.25'''' H x 12.5'''' W x 7.8'''' D',62,1,to_timestamp('20-JUN-17 11.43.10.731000000 AM','DD-MON-RR HH.MI.SSXFF AM'),45,'C:\Users\Yousef\shareit\images\sharedItems\beachBlender.jpg',61,'home,good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (22,'folding table & chairs ','5 chairs and one 6 foot folding table are at your disposal. Great if your dinner party is getting more guests than you expected.',62,1,to_timestamp('20-JUN-17 10.41.46.024000000 AM','DD-MON-RR HH.MI.SSXFF AM'),90,'C:\Users\Yousef\shareit\images\sharedItems\foldingChairTables.jpg',61,'home');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (33,'belgian waffle maker','Belgian waffle maker in good condition.',62,0,to_timestamp('20-JUN-17 11.44.41.259000000 AM','DD-MON-RR HH.MI.SSXFF AM'),40,'C:\Users\Yousef\shareit\images\sharedItems\waffleMaker.jpg',61,'home,kitchen,good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (34,'4-Slice Toaster Oven','Black & Decker TRO480BS Toast-R-Oven 4-Slice Toaster Oven  Excellent condition, used only a few times.  * Large-capacity 1200-watt 4-slice toaster oven * Bake, broil, bagel, toast, and keep-warm functions * Adjustable temperature up to 450 degrees F * 30-minute timer; slide-out crumb tray; baking pan included * Measures approximately 10 by 8.5 by 16 inches',62,1,to_timestamp('20-JUN-17 11.47.09.825000000 AM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\toasterOven.jpg',61,'excellent_condition,home,kitchen,prize');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (35,'food processor','large KitchenAid food processor in very good condition with all the attachments',62,1,to_timestamp('20-JUN-17 11.50.03.654000000 AM','DD-MON-RR HH.MI.SSXFF AM'),200,'C:\Users\Yousef\shareit\images\sharedItems\foodProcessor.jpg',61,'kitchen,home,new_condition,food');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (36,' Portable Electric Stove','This is the stove I normally use, but I don''t cook often, so it is generally used only once every couple weeks.  * 2 large coil tubular burners: one 1000 watts, 400 watts * Powerful heating elements * Dual power indicator lights * Fully-adjustable temperature control settings * Chrome drip pans',62,1,to_timestamp('20-JUN-17 11.52.35.308000000 AM','DD-MON-RR HH.MI.SSXFF AM'),33,'C:\Users\Yousef\shareit\images\sharedItems\portableStove.jpg',61,'stove,kitchen,portable,very_good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (37,'Portable Gas Stove','* CSA (US & Canadian Standards Association) Approved * Good for use in camping and emergencies * Built-in push-button piezo-electric starter - no matches, lighter or batteries needed * Produces generous flame from standard 8 oz Butane canister; automatic canister level indicator * Safety shut-off system',62,1,to_timestamp('20-JUN-17 11.55.15.257000000 AM','DD-MON-RR HH.MI.SSXFF AM'),28,'C:\Users\Yousef\shareit\images\sharedItems\gasStove.jpg',61,'home,kitchen,gas');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (38,'Folding Chairs (Dark Wood) ','Product dimensions Width: 17 3/8 " Depth: 20 1/8 " Height: 30 3/8 " Seat width: 15 " Seat depth: 13 " Seat height: 18 1/8 "',62,1,to_timestamp('20-JUN-17 11.56.56.813000000 AM','DD-MON-RR HH.MI.SSXFF AM'),15,'C:\Users\Yousef\shareit\images\sharedItems\darkChaired.jpg',61,'chair,home');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (39,'silex stick blender','Proctor Silex Stick Blender, basic stick blender for smoothies, soups, etc. Works great!',62,1,to_timestamp('20-JUN-17 11.58.12.839000000 AM','DD-MON-RR HH.MI.SSXFF AM'),15,'C:\Users\Yousef\shareit\images\sharedItems\proctor_blender.jpg',61,'kitchen,home,prize,good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (40,'Steam Mop','This thing is magical',62,1,to_timestamp('20-JUN-17 11.59.22.474000000 AM','DD-MON-RR HH.MI.SSXFF AM'),80,'C:\Users\Yousef\shareit\images\sharedItems\steam.jpg',61,'dust,clean,home');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (41,'Pyrex glassware',' I have a glass pie pan, 8 inch square pan and 13X9 pans',62,1,to_timestamp('20-JUN-17 12.00.40.102000000 PM','DD-MON-RR HH.MI.SSXFF AM'),40,'C:\Users\Yousef\shareit\images\sharedItems\pyrex.jpg',61,'home,kitchen,pyrex');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (42,'Fisher Price shape sorter','Shaped as a snail, encourages shape sorting and stacking, and makes music. Makes music.',63,1,to_timestamp('20-JUN-17 12.01.45.276000000 PM','DD-MON-RR HH.MI.SSXFF AM'),20,'C:\Users\Yousef\shareit\images\sharedItems\snailSorterjpg.jpg',61,'snail,kids,toys');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (43,'Tune Xylophone',' Little Tikes Tap A Tune Xylophone',63,1,to_timestamp('20-JUN-17 12.02.52.463000000 PM','DD-MON-RR HH.MI.SSXFF AM'),30,'C:\Users\Yousef\shareit\images\sharedItems\xyliphone.jpg',61,'xyliphone,kid,toys');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (44,'Maysaa''s Tricycle ','Pink tricycle and helmet',63,1,to_timestamp('20-JUN-17 12.04.31.938000000 PM','DD-MON-RR HH.MI.SSXFF AM'),60,'C:\Users\Yousef\shareit\images\sharedItems\tryciclejpg.jpg',61,'fun,kid,toy,bicycle');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (45,'Smart Support Hiking Backpack','the smarter way to carry your growing child. The height-adjustable seat supports your child as they grow, while the backpack supports you with its lightweight aluminum frame, generously padded shoulder straps, adjustable belt straps, and energy-absorbing lumbar padding. These extra supports reduce the strain on your back and shoulders and increase airflow keeping you cool and comfortable. Includes a zip-quick weather shield. It is freestanding, making loading and unloading your child virtually effortless. The SmartSupport Backpack''s flat-fold makes it easy to store and transport. Child needs to be able to sit up unassisted to use this product.  Child Weight Min: 1 lbs Child Weight Max: 40 lbs Product Weight: 8.2 pounds Product Dimensions (in inches):23.5 x 17.1 x 7.4',63,1,to_timestamp('20-JUN-17 12.09.49.107000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\Hiking-Backpack.jpg',61,'kid,kids,travel,baby_care');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (46,'Baby Gate','plastic baby gate',63,1,to_timestamp('20-JUN-17 12.14.00.299000000 PM','DD-MON-RR HH.MI.SSXFF AM'),20,'C:\Users\Yousef\shareit\images\sharedItems\babyCare.jpg',61,'kids,baby,care');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (47,'Toddlers DVD set','Mostly Dora, some Diego, my little ponies and strawberry shortcake.',63,1,to_timestamp('20-JUN-17 12.15.56.129000000 PM','DD-MON-RR HH.MI.SSXFF AM'),50,'C:\Users\Yousef\shareit\images\sharedItems\kidsDvds.jpg',61,'kid,cartoon,happy');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (48,'Nokia N95 8GB',' Like new Nokia N95 8GB, sitting in a drawer. I have the box and all the accessories. I have no use for this! Maybe you do. It takes good video, does 3G, international roaming, Wifi.  If you want a video / Wifi / music phone and don''t want an iPhone -- for instance, you want a real keyboard -- this might be the one for you.',64,1,to_timestamp('20-JUN-17 12.18.13.966000000 PM','DD-MON-RR HH.MI.SSXFF AM'),400,'C:\Users\Yousef\shareit\images\sharedItems\n95.jpg',61,'nokia,memories,phone,smart_phone');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (49,'Xbox 360','no games',64,1,to_timestamp('20-JUN-17 12.19.35.954000000 PM','DD-MON-RR HH.MI.SSXFF AM'),300,'C:\Users\Yousef\shareit\images\sharedItems\xbox.jpg',61,'xbox,fun,good_moments');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (50,'Yamaha Keyboard Piano','Yamaha keyborad Piano. PSR Model. Not digital. I bought it in 1998.',64,1,to_timestamp('20-JUN-17 12.37.29.761000000 PM','DD-MON-RR HH.MI.SSXFF AM'),300,'C:\Users\Yousef\shareit\images\sharedItems\keyboardPiano.gif',61,'piano,fun,hobbies');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (51,'Lumix Camera',' I bought this a year ago and took it to burning man and basically travel everywhere with it. I''ve also started to rent it out to other cinematographers who don''t want to buy one. All money from this gets put back into my filmmaking efforts.',64,1,to_timestamp('20-JUN-17 12.39.28.546000000 PM','DD-MON-RR HH.MI.SSXFF AM'),1000,'C:\Users\Yousef\shareit\images\sharedItems\lumix camera.jpg',61,'camera,hobbies,quality,memories');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (52,'wacom bamboo touch ',' finger-only tablet, great for desktop or laptop users, allows some gestures, if you want to buy, contact me.',64,1,to_timestamp('20-JUN-17 12.43.03.352000000 PM','DD-MON-RR HH.MI.SSXFF AM'),60,'C:\Users\Yousef\shareit\images\sharedItems\wacomTablet.jpg',61,'wacom,tablet');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (53,'Slide projectors - kodak carousel',' I have at least 2, maybe three kodak carousel slide projectors with wide angle and telephoto lenses. These are rated at roughly 350 lumens.',64,1,to_timestamp('20-JUN-17 12.44.12.348000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\kodakProjector.jpg',61,'kodak,projector');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (54,'Sony Blu-ray disc player','BDP-S370 Blu-ray Disc player features Blu-ray 3D playback, Wi-Fi Internet connectivity, and instant streaming of online video content from the BRAVIA Internet Video platform. When connected to a broadband Internet network, the BDP-S370 instantly streams movies, videos, music and more from YouTube, Yahoo!, Dailymotion, Wired, Sports Illustrated, Sony Pictures, and other providers through the Sony BRAVIA Internet Video platform. Sony''s Blu-ray Disc player BDP-S370 features an entertainment database browser',64,1,to_timestamp('20-JUN-17 12.46.36.239000000 PM','DD-MON-RR HH.MI.SSXFF AM'),235,'C:\Users\Yousef\shareit\images\sharedItems\sonyBluRay.jpg',61,'sony,blu-ray,good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (55,'Instant Polaroid Camera','nstant Photo Camera With its rounded shape, easy-to-hold side grip, and fingertip controllable composite control panel, the Instax 210 offers vivid, high-quality prints almost instantly. Its automatically-adjusting flash, high-resolution retracting lens and big clear viewfinder add up to unsurpassed performance.',64,1,to_timestamp('20-JUN-17 12.48.10.314000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\polaridCamera.jpg',61,'camera,pics,photos,memories');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (56,'TedwardHall''s Wii','Wii :)',64,1,to_timestamp('20-JUN-17 12.50.47.231000000 PM','DD-MON-RR HH.MI.SSXFF AM'),250,'C:\Users\Yousef\shareit\images\sharedItems\wii.jpg',61,'games,fun,timeKiller');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (57,'phillips''s DVD VHS player combo','palys dvds and vhs cassette tapes made by Zenith',64,1,to_timestamp('20-JUN-17 12.53.46.494000000 PM','DD-MON-RR HH.MI.SSXFF AM'),60,'C:\Users\Yousef\shareit\images\sharedItems\vhsPlayerCombo.jpg',61,'dvd,movies,electornics');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (58,'Batterij Toshiba PA3928U-1BRS',' http://www.caccu.nl/toshiba-pa3928u-1brs.html  This Toshiba PA3928U-1BRS batterij replacement maked with Hi-Quality battery cells, which is guaranteed to meet or exceed OEM specifications, Li-ion 14.40V and 47Wh capacity and is 100% compatible with the original equipment. All PA3928U-1BRS laptop batterij on sale have passed the stringent quality control tests that ensure they will work well with your model.',64,1,to_timestamp('20-JUN-17 12.56.34.117000000 PM','DD-MON-RR HH.MI.SSXFF AM'),50,'C:\Users\Yousef\shareit\images\sharedItems\toshibaBatery.gif',61,'toshiba,laptop,battery,good_condition');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (59,'expertise in tutoring ','Tutoring services in ELA and math for elementary school students.',65,1,to_timestamp('20-JUN-17 12.57.59.696000000 PM','DD-MON-RR HH.MI.SSXFF AM'),50,'C:\Users\Yousef\shareit\images\sharedItems\booksTutoring.jpg',61,'book,education,science');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (60,'large box of pencils','Only for use at TCS. a large copy paper box full of unopened pencils. To give not borrow.',65,1,to_timestamp('20-JUN-17 12.59.12.987000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\boxPencils.jpg',61,'student,education,pencil,write');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (61,'Dry erase markers',' 4 packs of colored dry erase markers. Office max and target brand. only for TCS school use. To give not borrow.',65,1,to_timestamp('20-JUN-17 01.02.08.073000000 PM','DD-MON-RR HH.MI.SSXFF AM'),30,'C:\Users\Yousef\shareit\images\sharedItems\erasers.jpg',61,'markers');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (62,'Bike wheel truing stand',' A stand to "true" bike tires. I also have the trueing tool if you need that too.',66,1,to_timestamp('20-JUN-17 01.03.30.638000000 PM','DD-MON-RR HH.MI.SSXFF AM'),40,'C:\Users\Yousef\shareit\images\sharedItems\bikeWheel.jpg',61,'bike,sports');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (86,'new Item','any thing',67,1,to_timestamp('20-JUN-17 04.46.51.995000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\xyliphone.jpg',61,'new,test');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (84,'Tennis raquette',' Tennis raquette Wilson',66,0,to_timestamp('20-JUN-17 04.01.52.277000000 PM','DD-MON-RR HH.MI.SSXFF AM'),175,'C:\Users\Yousef\shareit\images\sharedItems\tennisPoints.jpg',64,'sports,exercise');
Insert into SHAREIT.T_ITEM (ID,NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS,IMAGE_URL,USER_FROM,TAGS) values (85,'aprillynn''s 2 person ALPS tent ','2 person tent - ALPS brand',66,1,to_timestamp('20-JUN-17 04.36.13.862000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100,'C:\Users\Yousef\shareit\images\sharedItems\tent.jpg',13,'tent,camp,outdoor');
REM INSERTING into SHAREIT.T_NOTIFICATION
SET DEFINE OFF;
Insert into SHAREIT.T_NOTIFICATION (ID,ITEM,FROM_USER,TO_USER,POINTS_DEDUCTED,MEETING_POINT,DAYS,SEEN) values (-29,33,13,61,60,'giza square',-725933,0);
Insert into SHAREIT.T_NOTIFICATION (ID,ITEM,FROM_USER,TO_USER,POINTS_DEDUCTED,MEETING_POINT,DAYS,SEEN) values (-28,84,61,64,365,'masr el gdida - el t7awy',-725933,1);
REM INSERTING into SHAREIT.T_STATE
SET DEFINE OFF;
Insert into SHAREIT.T_STATE (ID,STATE) values (1,'NewYork');
Insert into SHAREIT.T_STATE (ID,STATE) values (2,'NoState');
REM INSERTING into SHAREIT.T_STATUS
SET DEFINE OFF;
Insert into SHAREIT.T_STATUS (ID,STATUS) values (1,'Pending');
Insert into SHAREIT.T_STATUS (ID,STATUS) values (2,'Accepted');
Insert into SHAREIT.T_STATUS (ID,STATUS) values (3,'Declined');
REM INSERTING into SHAREIT.T_USER
SET DEFINE OFF;
Insert into SHAREIT.T_USER (ID,USERNAME,EMAIL,PASSWORD,IMAGE_URL,POINTS,GENDER,ADDRESS) values (13,'dina','dina@gmail.com','827ccb0eea8a706c4c34a16891f84e7b',null,60,21,2);
Insert into SHAREIT.T_USER (ID,USERNAME,EMAIL,PASSWORD,IMAGE_URL,POINTS,GENDER,ADDRESS) values (12,'Ahmed','Ahmed@shareit.com','9193ce3b31332b03f7d8af056c692b84',null,100,1,2);
Insert into SHAREIT.T_USER (ID,USERNAME,EMAIL,PASSWORD,IMAGE_URL,POINTS,GENDER,ADDRESS) values (64,'sara','sara@gmail.com','827ccb0eea8a706c4c34a16891f84e7b',null,275,21,4);
Insert into SHAREIT.T_USER (ID,USERNAME,EMAIL,PASSWORD,IMAGE_URL,POINTS,GENDER,ADDRESS) values (61,'Yousef Salah','yousef@shareit.com','75b42d69fc527e6a37fc7bf10ae15d5e','C:\Users\Yousef\shareit\images\userProfile\yousef.jpg',365,1,2);
