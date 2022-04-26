### https://www.baeldung.com/spring-data-mongodb-tutorial
### https://javatodev.com/spring-boot-mongodb-crud-api/
### https://www.journaldev.com/18156/spring-boot-mongodb
### https://www.mongodb.com/compatibility/spring-boot
### https://hevodata.com/learn/spring-boot-mongodb-config/
### https://www.bezkoder.com/spring-boot-mongodb-crud/
### https://medium.com/javarevisited/building-a-rest-service-with-spring-boot-and-mongodb-part-1-2de01e4f434d


### https://start.spring.io/
- Select:
    - Project: Maven Project
    - Language: Java
    - Spring Boot: version (2.6.6)
    - Packaging: jar

- Project Metadata:
    - Group: com.example
    - Artifact: mdb-spring-boot
    - Name: mdb-spring-boot

- Add dependencies:
    - Spring Web
    - Spring Data MongoDB
    - Lombok
    - Validation

- click on the Generate button
- once the ZIP file is downloaded, unzip the project 
- open the project from the IDE

### pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

### We can run the app simply by using a single command
```
mvn spring-boot:run
```

### installing mongodb
```
https://docs.mongodb.org/manual/core/introduction/
```

### inside the project directory create a file employees.json (mongodb database data)
```json
[{"firstName":"Gery","lastName":"Trittam","email":"gtrittam0@4shared.com","gender":"Male","department":"HR","projects":["Project 4","Project 8","Project 3","Project 5"],"salary":3393,"createdAt":{"$date":"2011-03-15T06:00:48.000Z"}},
{"firstName":"Rory","lastName":"McGinty","email":"rmcginty1@baidu.com","gender":"Female","department":"HR","projects":["Project 1","Project 7"],"salary":4800,"createdAt":{"$date":"2019-04-26T18:26:24.000Z"}},
{"firstName":"Jami","lastName":"Diack","email":"jdiack2@ucla.edu","gender":"Female","department":"Sales","projects":["Project 1"],"salary":3400,"createdAt":{"$date":"2020-06-28T18:35:30.000Z"}},
{"firstName":"Marten","lastName":"Robertelli","email":"mrobertelli3@tinyurl.com","gender":"Male","department":"Production","projects":["Project 2","Project 1","Project 6"],"salary":6227,"createdAt":{"$date":"2021-02-06T19:05:55.000Z"}},
{"firstName":"Bertram","lastName":"Hurles","email":"bhurles4@artisteer.com","gender":"Male","department":"Customer Service","projects":["Project 2","Project 9"],"salary":6806,"createdAt":{"$date":"2018-07-25T08:51:11.000Z"}},
{"firstName":"Sig","lastName":"Chessman","email":"schessman5@yandex.ru","gender":"Male","department":"Purchase","projects":["Project 5","Project 3","Project 10"],"salary":4493,"createdAt":{"$date":"2015-12-09T15:10:33.000Z"}},
{"firstName":"Kendell","lastName":"Lathee","email":"klathee6@ucsd.edu","gender":"Male","department":"Purchase","projects":["Project 1","Project 3","Project 8","Project 5"],"salary":3528,"createdAt":{"$date":"2013-02-03T08:00:34.000Z"}},
{"firstName":"Sarajane","lastName":"Hakonsen","email":"shakonsen7@dyndns.org","gender":"Female","department":"R&D","projects":["Project 4","Project 2","Project 6","Project 3"],"salary":3766,"createdAt":{"$date":"2020-08-27T12:43:22.000Z"}},
{"firstName":"Peggi","lastName":"Scalia","email":"pscalia8@google.de","gender":"Female","department":"Operations","projects":["Project 8","Project 6","Project 1","Project 3"],"salary":4152,"createdAt":{"$date":"2017-04-08T10:13:41.000Z"}},
{"firstName":"Cully","lastName":"Boyfield","email":"cboyfield9@posterous.com","gender":"Male","department":"Marketing","projects":["Project 4"],"salary":3834,"createdAt":{"$date":"2018-11-01T16:01:00.000Z"}},
{"firstName":"Gino","lastName":"Jedraszek","email":"gjedraszeka@microsoft.com","gender":"Bigender","department":"Sales","projects":["Project 8"],"salary":3580,"createdAt":{"$date":"2020-01-13T16:52:10.000Z"}},
{"firstName":"Wes","lastName":"Geistbeck","email":"wgeistbeckb@drupal.org","gender":"Male","department":"Finance","projects":["Project 1"],"salary":5169,"createdAt":{"$date":"2011-04-10T09:49:31.000Z"}},
{"firstName":"Eimile","lastName":"Coffee","email":"ecoffeec@google.cn","gender":"Female","department":"Operations","projects":["Project 7","Project 4","Project 6"],"salary":4436,"createdAt":{"$date":"2012-05-15T13:13:47.000Z"}},
{"firstName":"Wilbert","lastName":"Bruster","email":"wbrusterd@liveinternet.ru","gender":"Male","department":"IT","projects":["Project 2","Project 10"],"salary":5211,"createdAt":{"$date":"2011-02-14T13:56:25.000Z"}},
{"firstName":"Lynea","lastName":"Van de Castele","email":"lvandecastelee@illinois.edu","gender":"Female","department":"IT","projects":["Project 8"],"salary":5592,"createdAt":{"$date":"2013-09-25T04:08:56.000Z"}},
{"firstName":"Fonsie","lastName":"Fearnill","email":"ffearnillf@1und1.de","gender":"Male","department":"Sales","projects":["Project 4"],"salary":5919,"createdAt":{"$date":"2020-01-14T19:02:36.000Z"}},
{"firstName":"Marley","lastName":"Filshin","email":"mfilshing@craigslist.org","gender":"Female","department":"Purchase","projects":["Project 8"],"salary":3305,"createdAt":{"$date":"2014-08-21T12:25:25.000Z"}},
{"firstName":"Winston","lastName":"Jinkinson","email":"wjinkinsonh@state.gov","gender":"Male","department":"Finance","projects":["Project 4","Project 1","Project 6","Project 7"],"salary":3397,"createdAt":{"$date":"2011-05-03T12:13:14.000Z"}},
{"firstName":"Terrance","lastName":"Wildash","email":"twildashi@harvard.edu","gender":"Male","department":"Operations","projects":["Project 5","Project 9","Project 6","Project 2"],"salary":4538,"createdAt":{"$date":"2016-01-22T04:31:22.000Z"}},
{"firstName":"Daria","lastName":"Thiolier","email":"dthiolierj@devhub.com","gender":"Female","department":"R&D","projects":["Project 1"],"salary":6733,"createdAt":{"$date":"2017-08-03T16:20:07.000Z"}},
{"firstName":"Camilla","lastName":"Rapier","email":"crapierk@reuters.com","gender":"Female","department":"Sales","projects":["Project 2","Project 9","Project 6"],"salary":6898,"createdAt":{"$date":"2019-10-03T22:53:02.000Z"}},
{"firstName":"Kris","lastName":"Husband","email":"khusbandl@freewebs.com","gender":"Male","department":"R&D","projects":["Project 8","Project 10"],"salary":6345,"createdAt":{"$date":"2019-06-15T19:55:31.000Z"}},
{"firstName":"Meara","lastName":"Aveling","email":"mavelingm@oakley.com","gender":"Female","department":"IT","projects":["Project 3"],"salary":6118,"createdAt":{"$date":"2012-06-26T12:32:01.000Z"}},
{"firstName":"Kimberli","lastName":"Whorall","email":"kwhoralln@hubpages.com","gender":"Female","department":"Production","projects":["Project 10","Project 6","Project 4"],"salary":6314,"createdAt":{"$date":"2021-05-24T20:39:39.000Z"}},
{"firstName":"Allis","lastName":"de Grey","email":"adegreyo@narod.ru","gender":"Female","department":"Production","projects":["Project 7","Project 1","Project 9","Project 8"],"salary":3943,"createdAt":{"$date":"2018-03-27T07:34:41.000Z"}},
{"firstName":"Hamilton","lastName":"Garrard","email":"hgarrardp@prlog.org","gender":"Male","department":"HR","projects":["Project 8","Project 6","Project 7"],"salary":6137,"createdAt":{"$date":"2022-03-21T17:11:16.000Z"}},
{"firstName":"Andee","lastName":"Cowburn","email":"acowburnq@gnu.org","gender":"Female","department":"Marketing","projects":["Project 7","Project 5"],"salary":4110,"createdAt":{"$date":"2013-04-21T07:18:36.000Z"}},
{"firstName":"Free","lastName":"Wilkison","email":"fwilkisonr@businessinsider.com","gender":"Male","department":"Production","projects":["Project 1","Project 6","Project 8","Project 9"],"salary":4076,"createdAt":{"$date":"2022-02-26T02:35:05.000Z"}},
{"firstName":"Carroll","lastName":"Rottgers","email":"crottgerss@fda.gov","gender":"Male","department":"Purchase","projects":["Project 3","Project 2","Project 5"],"salary":4645,"createdAt":{"$date":"2018-01-27T07:36:03.000Z"}},
{"firstName":"Yevette","lastName":"Gatty","email":"ygattyt@auda.org.au","gender":"Female","department":"Purchase","projects":["Project 8","Project 5"],"salary":3715,"createdAt":{"$date":"2017-10-22T23:52:05.000Z"}},
{"firstName":"Neile","lastName":"Boldra","email":"nboldrau@reverbnation.com","gender":"Female","department":"R&D","projects":["Project 9"],"salary":6329,"createdAt":{"$date":"2015-11-23T14:06:42.000Z"}},
{"firstName":"Dionisio","lastName":"Chappelow","email":"dchappelowv@unc.edu","gender":"Male","department":"Sales","projects":["Project 1","Project 9","Project 4","Project 8"],"salary":4776,"createdAt":{"$date":"2022-01-19T15:57:03.000Z"}},
{"firstName":"Cammie","lastName":"Remnant","email":"cremnantw@psu.edu","gender":"Female","department":"IT","projects":["Project 9","Project 3","Project 7","Project 5"],"salary":4843,"createdAt":{"$date":"2011-04-20T16:03:05.000Z"}},
{"firstName":"Dulcie","lastName":"Farre","email":"dfarrex@yahoo.co.jp","gender":"Female","department":"Customer Service","projects":["Project 10","Project 2","Project 4"],"salary":6235,"createdAt":{"$date":"2013-03-03T14:36:09.000Z"}},
{"firstName":"Davidde","lastName":"Dobrovolski","email":"ddobrovolskiy@cpanel.net","gender":"Male","department":"Purchase","projects":["Project 3","Project 7","Project 5","Project 1"],"salary":6985,"createdAt":{"$date":"2020-12-08T12:47:32.000Z"}},
{"firstName":"Keenan","lastName":"Morfett","email":"kmorfettz@wikia.com","gender":"Male","department":"R&D","projects":["Project 6","Project 5"],"salary":4622,"createdAt":{"$date":"2013-01-30T18:31:06.000Z"}},
{"firstName":"Kerianne","lastName":"Canero","email":"kcanero10@hostgator.com","gender":"Female","department":"R&D","projects":["Project 6"],"salary":3503,"createdAt":{"$date":"2010-03-03T15:53:49.000Z"}},
{"firstName":"Vivia","lastName":"Meneely","email":"vmeneely11@xrea.com","gender":"Agender","department":"Sales","projects":["Project 4","Project 3","Project 7"],"salary":4869,"createdAt":{"$date":"2014-07-27T06:16:39.000Z"}},
{"firstName":"Doralynne","lastName":"Gabrieli","email":"dgabrieli12@youtube.com","gender":"Female","department":"Finance","projects":["Project 8","Project 3","Project 2","Project 4"],"salary":4341,"createdAt":{"$date":"2010-06-26T04:10:20.000Z"}},
{"firstName":"Marice","lastName":"Roscam","email":"mroscam13@canalblog.com","gender":"Female","department":"HR","projects":["Project 2"],"salary":5357,"createdAt":{"$date":"2011-11-30T16:01:32.000Z"}},
{"firstName":"Kelsi","lastName":"Tante","email":"ktante14@va.gov","gender":"Female","department":"Sales","projects":["Project 4","Project 5","Project 7","Project 9"],"salary":4145,"createdAt":{"$date":"2013-01-12T03:28:04.000Z"}},
{"firstName":"Wallis","lastName":"Worsell","email":"wworsell15@ovh.net","gender":"Male","department":"Operations","projects":["Project 7"],"salary":4632,"createdAt":{"$date":"2013-03-25T11:31:30.000Z"}},
{"firstName":"Mariellen","lastName":"Hedditch","email":"mhedditch16@reverbnation.com","gender":"Female","department":"Sales","projects":["Project 9","Project 7","Project 4"],"salary":5108,"createdAt":{"$date":"2016-11-13T21:47:26.000Z"}},
{"firstName":"Murvyn","lastName":"Bolley","email":"mbolley17@ning.com","gender":"Male","department":"Finance","projects":["Project 7","Project 1"],"salary":5977,"createdAt":{"$date":"2011-11-04T02:21:21.000Z"}},
{"firstName":"Valentia","lastName":"Olyonov","email":"volyonov18@surveymonkey.com","gender":"Female","department":"Customer Service","projects":["Project 1"],"salary":4144,"createdAt":{"$date":"2015-07-13T10:48:47.000Z"}},
{"firstName":"Elinore","lastName":"McGunley","email":"emcgunley19@google.nl","gender":"Bigender","department":"Customer Service","projects":["Project 7","Project 8"],"salary":6145,"createdAt":{"$date":"2019-09-03T03:48:18.000Z"}},
{"firstName":"Hobard","lastName":"Timewell","email":"htimewell1a@sakura.ne.jp","gender":"Polygender","department":"Marketing","projects":["Project 5","Project 10","Project 8","Project 1"],"salary":4497,"createdAt":{"$date":"2012-01-29T00:13:42.000Z"}},
{"firstName":"Benjamin","lastName":"Whyborn","email":"bwhyborn1b@auda.org.au","gender":"Male","department":"Operations","projects":["Project 8","Project 10","Project 3","Project 7"],"salary":3973,"createdAt":{"$date":"2020-09-09T14:31:01.000Z"}},
{"firstName":"Donni","lastName":"Local","email":"dlocal1c@themeforest.net","gender":"Female","department":"Purchase","projects":["Project 3","Project 1","Project 7"],"salary":4729,"createdAt":{"$date":"2014-01-02T03:21:59.000Z"}},
{"firstName":"Spike","lastName":"Colum","email":"scolum1d@answers.com","gender":"Male","department":"IT","projects":["Project 3","Project 5","Project 9","Project 1"],"salary":5735,"createdAt":{"$date":"2020-03-14T02:57:08.000Z"}},
{"firstName":"Gustavo","lastName":"Bubeer","email":"gbubeer1e@usnews.com","gender":"Male","department":"Finance","projects":["Project 9","Project 8"],"salary":4330,"createdAt":{"$date":"2012-12-18T03:25:58.000Z"}},
{"firstName":"Norrie","lastName":"Simonelli","email":"nsimonelli1f@washingtonpost.com","gender":"Male","department":"IT","projects":["Project 6","Project 1","Project 5","Project 3"],"salary":5591,"createdAt":{"$date":"2021-05-17T23:19:46.000Z"}},
{"firstName":"Karon","lastName":"Cohani","email":"kcohani1g@vkontakte.ru","gender":"Female","department":"HR","projects":["Project 4","Project 10","Project 3","Project 8"],"salary":4655,"createdAt":{"$date":"2016-08-27T19:02:26.000Z"}},
{"firstName":"Raquel","lastName":"Templeton","email":"rtempleton1h@ehow.com","gender":"Female","department":"Production","projects":["Project 8","Project 7","Project 3"],"salary":5272,"createdAt":{"$date":"2018-08-03T10:03:20.000Z"}},
{"firstName":"Hannis","lastName":"Tantrum","email":"htantrum1i@ezinearticles.com","gender":"Female","department":"Production","projects":["Project 2"],"salary":6714,"createdAt":{"$date":"2014-07-04T09:07:58.000Z"}},
{"firstName":"Sergeant","lastName":"Woodburn","email":"swoodburn1j@timesonline.co.uk","gender":"Male","department":"GM","projects":["Project 5","Project 10","Project 6","Project 8"],"salary":6694,"createdAt":{"$date":"2015-09-07T12:23:11.000Z"}},
{"firstName":"Viva","lastName":"Cardenoso","email":"vcardenoso1k@yolasite.com","gender":"Female","department":"Finance","projects":["Project 3"],"salary":4398,"createdAt":{"$date":"2013-06-10T03:52:06.000Z"}},
{"firstName":"Holly","lastName":"Fanshaw","email":"hfanshaw1l@icq.com","gender":"Female","department":"HR","projects":["Project 3","Project 7","Project 8","Project 1"],"salary":5683,"createdAt":{"$date":"2018-02-08T21:12:26.000Z"}},
{"firstName":"Charles","lastName":"Choudhury","email":"cchoudhury1m@pagesperso-orange.fr","gender":"Male","department":"Purchase","projects":["Project 8","Project 10","Project 4"],"salary":3443,"createdAt":{"$date":"2013-06-03T06:08:30.000Z"}},
{"firstName":"Giavani","lastName":"Bes","email":"gbes1n@plala.or.jp","gender":"Male","department":"R&D","projects":["Project 3","Project 2","Project 1"],"salary":5607,"createdAt":{"$date":"2016-11-07T07:44:14.000Z"}},
{"firstName":"Dido","lastName":"Cryer","email":"dcryer1o@blinklist.com","gender":"Female","department":"Purchase","projects":["Project 4"],"salary":5912,"createdAt":{"$date":"2022-03-08T07:35:50.000Z"}},
{"firstName":"Nadia","lastName":"Iremonger","email":"niremonger1p@privacy.gov.au","gender":"Female","department":"Customer Service","projects":["Project 10","Project 5"],"salary":5334,"createdAt":{"$date":"2022-01-20T06:21:04.000Z"}},
{"firstName":"Suellen","lastName":"Fredi","email":"sfredi1q@usnews.com","gender":"Female","department":"Sales","projects":["Project 7"],"salary":6800,"createdAt":{"$date":"2011-05-11T11:14:00.000Z"}},
{"firstName":"Leonanie","lastName":"Blakeley","email":"lblakeley1r@ucsd.edu","gender":"Female","department":"HR","projects":["Project 8","Project 7","Project 9"],"salary":6265,"createdAt":{"$date":"2014-02-03T20:34:38.000Z"}},
{"firstName":"Sindee","lastName":"Tortis","email":"stortis1s@telegraph.co.uk","gender":"Female","department":"Operations","projects":["Project 6","Project 1"],"salary":3632,"createdAt":{"$date":"2018-01-17T15:24:34.000Z"}},
{"firstName":"Dido","lastName":"Nelle","email":"dnelle1t@nationalgeographic.com","gender":"Female","department":"Marketing","projects":["Project 3","Project 1","Project 8"],"salary":5414,"createdAt":{"$date":"2016-06-10T11:23:06.000Z"}},
{"firstName":"Bryn","lastName":"Skotcher","email":"bskotcher1u@odnoklassniki.ru","gender":"Female","department":"Operations","projects":["Project 5","Project 8"],"salary":5375,"createdAt":{"$date":"2019-04-29T13:18:08.000Z"}},
{"firstName":"Gerri","lastName":"Tanguy","email":"gtanguy1v@sphinn.com","gender":"Female","department":"GM","projects":["Project 4","Project 10","Project 7"],"salary":5518,"createdAt":{"$date":"2014-04-09T09:46:44.000Z"}},
{"firstName":"Garrot","lastName":"Mulles","email":"gmulles1w@netlog.com","gender":"Male","department":"GM","projects":["Project 1","Project 6","Project 8"],"salary":6218,"createdAt":{"$date":"2018-04-27T16:52:56.000Z"}},
{"firstName":"Hiram","lastName":"Rodenborch","email":"hrodenborch1x@home.pl","gender":"Male","department":"Purchase","projects":["Project 10"],"salary":5592,"createdAt":{"$date":"2017-02-25T07:51:30.000Z"}},
{"firstName":"Augie","lastName":"Duerdin","email":"aduerdin1y@rediff.com","gender":"Male","department":"Customer Service","projects":["Project 9","Project 5"],"salary":5541,"createdAt":{"$date":"2018-03-30T03:47:04.000Z"}},
{"firstName":"Elroy","lastName":"Kynastone","email":"ekynastone1z@multiply.com","gender":"Male","department":"Customer Service","projects":["Project 5"],"salary":4146,"createdAt":{"$date":"2014-12-29T16:42:39.000Z"}},
{"firstName":"Page","lastName":"Ricciardiello","email":"pricciardiello20@yahoo.co.jp","gender":"Female","department":"Marketing","projects":["Project 10"],"salary":4473,"createdAt":{"$date":"2011-02-12T21:14:34.000Z"}},
{"firstName":"Jarrad","lastName":"Benezet","email":"jbenezet21@infoseek.co.jp","gender":"Male","department":"Marketing","projects":["Project 7"],"salary":5328,"createdAt":{"$date":"2010-01-12T16:28:24.000Z"}},
{"firstName":"Roman","lastName":"Daal","email":"rdaal22@house.gov","gender":"Male","department":"GM","projects":["Project 4","Project 6"],"salary":5652,"createdAt":{"$date":"2014-09-02T22:28:25.000Z"}},
{"firstName":"Llywellyn","lastName":"Bavridge","email":"lbavridge23@census.gov","gender":"Male","department":"Customer Service","projects":["Project 4","Project 8","Project 3"],"salary":5563,"createdAt":{"$date":"2018-01-30T01:48:10.000Z"}},
{"firstName":"Allan","lastName":"Gozzard","email":"agozzard24@shop-pro.jp","gender":"Male","department":"R&D","projects":["Project 4","Project 5","Project 2","Project 9"],"salary":4170,"createdAt":{"$date":"2013-01-14T17:33:26.000Z"}},
{"firstName":"Gregoor","lastName":"Klimus","email":"gklimus25@mayoclinic.com","gender":"Male","department":"IT","projects":["Project 5"],"salary":5379,"createdAt":{"$date":"2014-08-27T19:32:38.000Z"}},
{"firstName":"Xerxes","lastName":"Goodlett","email":"xgoodlett26@vistaprint.com","gender":"Male","department":"Purchase","projects":["Project 8","Project 6","Project 5","Project 9"],"salary":3656,"createdAt":{"$date":"2018-01-31T10:58:44.000Z"}},
{"firstName":"Marley","lastName":"Linklater","email":"mlinklater27@over-blog.com","gender":"Genderqueer","department":"Sales","projects":["Project 9","Project 10","Project 4","Project 8"],"salary":5345,"createdAt":{"$date":"2010-09-19T20:58:41.000Z"}},
{"firstName":"Giovanni","lastName":"Reide","email":"greide28@vinaora.com","gender":"Genderfluid","department":"Production","projects":["Project 4"],"salary":6250,"createdAt":{"$date":"2019-11-28T01:18:20.000Z"}},
{"firstName":"Janina","lastName":"Burkin","email":"jburkin29@elegantthemes.com","gender":"Female","department":"Purchase","projects":["Project 3"],"salary":3528,"createdAt":{"$date":"2016-01-09T12:08:55.000Z"}},
{"firstName":"Rhea","lastName":"Keigher","email":"rkeigher2a@aol.com","gender":"Female","department":"Production","projects":["Project 10","Project 8","Project 4"],"salary":5019,"createdAt":{"$date":"2021-03-07T19:39:09.000Z"}},
{"firstName":"Noe","lastName":"Raper","email":"nraper2b@wunderground.com","gender":"Male","department":"HR","projects":["Project 3","Project 5","Project 9","Project 8"],"salary":3303,"createdAt":{"$date":"2015-03-15T11:17:21.000Z"}},
{"firstName":"Eleanor","lastName":"Saban","email":"esaban2c@histats.com","gender":"Female","department":"R&D","projects":["Project 6","Project 3"],"salary":6059,"createdAt":{"$date":"2016-09-30T00:10:56.000Z"}},
{"firstName":"Jane","lastName":"MacRorie","email":"jmacrorie2d@dell.com","gender":"Female","department":"HR","projects":["Project 2","Project 8","Project 9","Project 5"],"salary":5236,"createdAt":{"$date":"2019-12-19T13:37:31.000Z"}},
{"firstName":"Demetra","lastName":"Minghetti","email":"dminghetti2e@spiegel.de","gender":"Female","department":"Customer Service","projects":["Project 9"],"salary":5639,"createdAt":{"$date":"2021-02-12T06:41:56.000Z"}},
{"firstName":"Marjy","lastName":"Rowlings","email":"mrowlings2f@vistaprint.com","gender":"Female","department":"GM","projects":["Project 8"],"salary":4757,"createdAt":{"$date":"2013-12-17T00:38:05.000Z"}},
{"firstName":"Myrna","lastName":"Dineen","email":"mdineen2g@jugem.jp","gender":"Genderfluid","department":"Customer Service","projects":["Project 3"],"salary":5377,"createdAt":{"$date":"2020-07-05T20:34:30.000Z"}},
{"firstName":"Sydney","lastName":"Mallett","email":"smallett2h@wp.com","gender":"Female","department":"Finance","projects":["Project 6","Project 8"],"salary":4027,"createdAt":{"$date":"2020-03-08T19:43:34.000Z"}},
{"firstName":"Eula","lastName":"Ransfield","email":"eransfield2i@nbcnews.com","gender":"Female","department":"Production","projects":["Project 6","Project 7","Project 3","Project 8"],"salary":6984,"createdAt":{"$date":"2013-05-29T03:55:00.000Z"}},
{"firstName":"Jarib","lastName":"Peet","email":"jpeet2j@flavors.me","gender":"Male","department":"Operations","projects":["Project 9"],"salary":6418,"createdAt":{"$date":"2013-02-20T02:44:07.000Z"}},
{"firstName":"Guss","lastName":"Duffil","email":"gduffil2k@google.nl","gender":"Male","department":"Sales","projects":["Project 1","Project 10","Project 5"],"salary":6562,"createdAt":{"$date":"2021-07-11T12:33:32.000Z"}},
{"firstName":"David","lastName":"Bouts","email":"dbouts2l@huffingtonpost.com","gender":"Male","department":"Marketing","projects":["Project 2","Project 6","Project 8","Project 9"],"salary":3300,"createdAt":{"$date":"2014-12-15T16:06:25.000Z"}},
{"firstName":"Ambrosius","lastName":"Loomes","email":"aloomes2m@blinklist.com","gender":"Male","department":"GM","projects":["Project 8"],"salary":3491,"createdAt":{"$date":"2022-03-10T05:56:38.000Z"}},
{"firstName":"Toby","lastName":"Stubbley","email":"tstubbley2n@sitemeter.com","gender":"Male","department":"IT","projects":["Project 2","Project 6","Project 10"],"salary":4594,"createdAt":{"$date":"2013-10-04T01:14:40.000Z"}},
{"firstName":"Lucretia","lastName":"Yesenin","email":"lyesenin2o@amazon.co.uk","gender":"Female","department":"Production","projects":["Project 8","Project 4"],"salary":5654,"createdAt":{"$date":"2020-11-29T06:19:54.000Z"}},
{"firstName":"Rowan","lastName":"Delgua","email":"rdelgua2p@weather.com","gender":"Male","department":"Operations","projects":["Project 6","Project 5","Project 1","Project 7"],"salary":3772,"createdAt":{"$date":"2013-12-13T10:48:42.000Z"}},
{"firstName":"Alvin","lastName":"Caban","email":"acaban2q@google.nl","gender":"Male","department":"Sales","projects":["Project 9","Project 6","Project 5","Project 3"],"salary":3807,"createdAt":{"$date":"2012-07-09T12:25:09.000Z"}},
{"firstName":"Minnnie","lastName":"Meadows","email":"mmeadows2r@nyu.edu","gender":"Female","department":"Customer Service","projects":["Project 8","Project 9"],"salary":3455,"createdAt":{"$date":"2013-11-04T19:54:17.000Z"}},
{"firstName":"Marylin","lastName":"Millen","email":"mmillen2s@nytimes.com","gender":"Non-binary","department":"Operations","projects":["Project 7"],"salary":6990,"createdAt":{"$date":"2013-06-28T15:36:38.000Z"}},
{"firstName":"Dolph","lastName":"Wraggs","email":"dwraggs2t@fema.gov","gender":"Male","department":"Production","projects":["Project 4"],"salary":5599,"createdAt":{"$date":"2022-04-11T21:56:33.000Z"}},
{"firstName":"Addy","lastName":"Collum","email":"acollum2u@1688.com","gender":"Female","department":"Customer Service","projects":["Project 4","Project 5","Project 10"],"salary":5302,"createdAt":{"$date":"2019-01-31T18:04:29.000Z"}},
{"firstName":"Adriena","lastName":"Alvin","email":"aalvin2v@dailymail.co.uk","gender":"Female","department":"GM","projects":["Project 9"],"salary":3713,"createdAt":{"$date":"2011-08-14T22:52:24.000Z"}},
{"firstName":"Jeanne","lastName":"Thiese","email":"jthiese2w@springer.com","gender":"Female","department":"Production","projects":["Project 2","Project 3","Project 6"],"salary":3954,"createdAt":{"$date":"2019-04-07T10:07:06.000Z"}},
{"firstName":"Willard","lastName":"Orrock","email":"worrock2x@narod.ru","gender":"Polygender","department":"Purchase","projects":["Project 6","Project 7"],"salary":3884,"createdAt":{"$date":"2011-04-12T14:10:34.000Z"}},
{"firstName":"Ulric","lastName":"Carruthers","email":"ucarruthers2y@amazon.com","gender":"Male","department":"Production","projects":["Project 4","Project 10"],"salary":5008,"createdAt":{"$date":"2020-01-20T07:03:38.000Z"}},
{"firstName":"Moses","lastName":"Leather","email":"mleather2z@dion.ne.jp","gender":"Male","department":"Production","projects":["Project 5","Project 10"],"salary":6516,"createdAt":{"$date":"2012-01-13T13:50:30.000Z"}},
{"firstName":"Percival","lastName":"Letherbury","email":"pletherbury30@eepurl.com","gender":"Male","department":"GM","projects":["Project 1","Project 5","Project 9","Project 8"],"salary":5979,"createdAt":{"$date":"2018-02-26T10:53:52.000Z"}},
{"firstName":"Cammy","lastName":"Flett","email":"cflett31@acquirethisname.com","gender":"Genderqueer","department":"Production","projects":["Project 5","Project 9","Project 7"],"salary":5838,"createdAt":{"$date":"2016-01-12T01:53:40.000Z"}},
{"firstName":"Frederigo","lastName":"Paaso","email":"fpaaso32@imdb.com","gender":"Male","department":"Production","projects":["Project 4","Project 7","Project 3","Project 6"],"salary":3335,"createdAt":{"$date":"2020-06-12T00:30:00.000Z"}},
{"firstName":"Keenan","lastName":"Wallsworth","email":"kwallsworth33@rambler.ru","gender":"Male","department":"Sales","projects":["Project 9","Project 8","Project 7","Project 1"],"salary":4351,"createdAt":{"$date":"2018-02-12T08:13:03.000Z"}},
{"firstName":"North","lastName":"Saffer","email":"nsaffer34@nps.gov","gender":"Male","department":"Production","projects":["Project 6","Project 4","Project 8","Project 3"],"salary":6552,"createdAt":{"$date":"2013-03-24T23:09:50.000Z"}},
{"firstName":"Christel","lastName":"Shannon","email":"cshannon35@nydailynews.com","gender":"Female","department":"Purchase","projects":["Project 4","Project 10","Project 7","Project 9"],"salary":6972,"createdAt":{"$date":"2019-07-25T07:17:48.000Z"}},
{"firstName":"Normy","lastName":"Huxton","email":"nhuxton36@dell.com","gender":"Agender","department":"HR","projects":["Project 5","Project 10","Project 7"],"salary":5080,"createdAt":{"$date":"2014-06-25T10:48:33.000Z"}},
{"firstName":"Horatio","lastName":"Costar","email":"hcostar37@google.es","gender":"Male","department":"HR","projects":["Project 1","Project 2","Project 9","Project 3"],"salary":5172,"createdAt":{"$date":"2013-02-23T21:57:40.000Z"}},
{"firstName":"Malchy","lastName":"Margrem","email":"mmargrem38@printfriendly.com","gender":"Male","department":"R&D","projects":["Project 3"],"salary":6782,"createdAt":{"$date":"2011-07-29T00:11:34.000Z"}},
{"firstName":"Gabe","lastName":"Meredith","email":"gmeredith39@trellian.com","gender":"Male","department":"Customer Service","projects":["Project 8","Project 10","Project 6"],"salary":3495,"createdAt":{"$date":"2015-06-08T15:00:19.000Z"}},
{"firstName":"Eb","lastName":"Keene","email":"ekeene3a@weibo.com","gender":"Male","department":"GM","projects":["Project 4","Project 2","Project 1"],"salary":6737,"createdAt":{"$date":"2010-02-04T08:50:46.000Z"}},
{"firstName":"Lew","lastName":"Juanico","email":"ljuanico3b@livejournal.com","gender":"Male","department":"Finance","projects":["Project 7","Project 9"],"salary":5251,"createdAt":{"$date":"2015-04-11T05:14:36.000Z"}},
{"firstName":"Lilli","lastName":"Sirmond","email":"lsirmond3c@noaa.gov","gender":"Female","department":"IT","projects":["Project 4","Project 6","Project 2","Project 7"],"salary":5441,"createdAt":{"$date":"2017-10-25T20:56:16.000Z"}},
{"firstName":"Tabbie","lastName":"Tither","email":"ttither3d@samsung.com","gender":"Female","department":"Marketing","projects":["Project 1"],"salary":4997,"createdAt":{"$date":"2014-11-22T16:25:58.000Z"}},
{"firstName":"Wallace","lastName":"Bernon","email":"wbernon3e@auda.org.au","gender":"Male","department":"HR","projects":["Project 5","Project 2"],"salary":4590,"createdAt":{"$date":"2019-09-01T20:33:00.000Z"}},
{"firstName":"Evy","lastName":"Core","email":"ecore3f@amazon.co.jp","gender":"Female","department":"R&D","projects":["Project 10","Project 1"],"salary":4208,"createdAt":{"$date":"2011-02-10T15:59:55.000Z"}},
{"firstName":"Esteban","lastName":"Lavender","email":"elavender3g@i2i.jp","gender":"Male","department":"GM","projects":["Project 2","Project 4"],"salary":5956,"createdAt":{"$date":"2019-05-03T09:22:01.000Z"}},
{"firstName":"Devlin","lastName":"Meatyard","email":"dmeatyard3h@shareasale.com","gender":"Male","department":"Finance","projects":["Project 6","Project 8"],"salary":6245,"createdAt":{"$date":"2020-09-14T01:47:14.000Z"}},
{"firstName":"Luz","lastName":"Semmence","email":"lsemmence3i@uol.com.br","gender":"Female","department":"Finance","projects":["Project 5"],"salary":6351,"createdAt":{"$date":"2019-01-24T18:14:03.000Z"}},
{"firstName":"Tobie","lastName":"Luis","email":"tluis3j@sitemeter.com","gender":"Genderfluid","department":"Finance","projects":["Project 10","Project 7","Project 5","Project 4"],"salary":5042,"createdAt":{"$date":"2014-10-01T21:22:14.000Z"}},
{"firstName":"Izaak","lastName":"Beard","email":"ibeard3k@si.edu","gender":"Male","department":"Purchase","projects":["Project 6","Project 4","Project 5"],"salary":5667,"createdAt":{"$date":"2010-08-13T09:35:09.000Z"}},
{"firstName":"Dill","lastName":"Raven","email":"draven3l@twitter.com","gender":"Male","department":"Operations","projects":["Project 1","Project 9","Project 2"],"salary":6659,"createdAt":{"$date":"2019-12-09T23:10:45.000Z"}},
{"firstName":"Robbert","lastName":"O'Fogerty","email":"rofogerty3m@usgs.gov","gender":"Non-binary","department":"Purchase","projects":["Project 3","Project 5","Project 9","Project 6"],"salary":6306,"createdAt":{"$date":"2016-07-20T07:54:13.000Z"}},
{"firstName":"Noel","lastName":"Dunston","email":"ndunston3n@addthis.com","gender":"Female","department":"Marketing","projects":["Project 6","Project 5","Project 8"],"salary":6325,"createdAt":{"$date":"2015-01-08T23:40:04.000Z"}},
{"firstName":"Daria","lastName":"Trenholm","email":"dtrenholm3o@baidu.com","gender":"Female","department":"IT","projects":["Project 6","Project 3","Project 7","Project 1"],"salary":5845,"createdAt":{"$date":"2016-03-23T05:24:24.000Z"}},
{"firstName":"Malinda","lastName":"Lupson","email":"mlupson3p@paypal.com","gender":"Female","department":"IT","projects":["Project 4","Project 8","Project 5"],"salary":4541,"createdAt":{"$date":"2011-12-15T20:26:23.000Z"}},
{"firstName":"Giraldo","lastName":"Sorton","email":"gsorton3q@issuu.com","gender":"Male","department":"Sales","projects":["Project 6","Project 3"],"salary":5958,"createdAt":{"$date":"2022-01-12T08:16:05.000Z"}},
{"firstName":"Casper","lastName":"Feldharker","email":"cfeldharker3r@histats.com","gender":"Male","department":"Customer Service","projects":["Project 1"],"salary":6123,"createdAt":{"$date":"2017-03-25T07:51:52.000Z"}},
{"firstName":"Devlin","lastName":"Biggerstaff","email":"dbiggerstaff3s@diigo.com","gender":"Male","department":"HR","projects":["Project 8","Project 2"],"salary":5411,"createdAt":{"$date":"2016-07-28T05:09:26.000Z"}},
{"firstName":"Marsha","lastName":"Picopp","email":"mpicopp3t@networkadvertising.org","gender":"Female","department":"R&D","projects":["Project 1","Project 8","Project 2"],"salary":5714,"createdAt":{"$date":"2010-05-07T17:41:36.000Z"}},
{"firstName":"Trevor","lastName":"Orgill","email":"torgill3u@admin.ch","gender":"Male","department":"Marketing","projects":["Project 3","Project 10","Project 6","Project 7"],"salary":5952,"createdAt":{"$date":"2017-05-29T03:34:23.000Z"}},
{"firstName":"Catherine","lastName":"Doughton","email":"cdoughton3v@odnoklassniki.ru","gender":"Non-binary","department":"Production","projects":["Project 7"],"salary":4575,"createdAt":{"$date":"2016-05-24T17:04:46.000Z"}},
{"firstName":"Mela","lastName":"Delyth","email":"mdelyth3w@businessweek.com","gender":"Female","department":"Operations","projects":["Project 3","Project 2","Project 1","Project 9"],"salary":5107,"createdAt":{"$date":"2018-09-28T04:51:25.000Z"}},
{"firstName":"Neel","lastName":"Lefley","email":"nlefley3x@hud.gov","gender":"Male","department":"Sales","projects":["Project 4"],"salary":6331,"createdAt":{"$date":"2018-02-11T22:01:25.000Z"}},
{"firstName":"Lin","lastName":"Luckcock","email":"lluckcock3y@hubpages.com","gender":"Female","department":"IT","projects":["Project 7","Project 8","Project 9","Project 6"],"salary":3684,"createdAt":{"$date":"2014-10-01T16:27:42.000Z"}},
{"firstName":"Gipsy","lastName":"Beernt","email":"gbeernt3z@nhs.uk","gender":"Female","department":"Sales","projects":["Project 10","Project 2"],"salary":4955,"createdAt":{"$date":"2016-11-02T08:38:25.000Z"}},
{"firstName":"Barth","lastName":"Bollans","email":"bbollans40@hhs.gov","gender":"Male","department":"Marketing","projects":["Project 4","Project 2","Project 1","Project 8"],"salary":4060,"createdAt":{"$date":"2011-06-08T13:17:28.000Z"}},
{"firstName":"Dominic","lastName":"Halvorsen","email":"dhalvorsen41@sitemeter.com","gender":"Male","department":"R&D","projects":["Project 8","Project 9","Project 3"],"salary":4219,"createdAt":{"$date":"2018-08-10T23:15:04.000Z"}},
{"firstName":"Jannelle","lastName":"Fernehough","email":"jfernehough42@ca.gov","gender":"Female","department":"Customer Service","projects":["Project 3","Project 5","Project 2"],"salary":6021,"createdAt":{"$date":"2010-03-24T02:25:00.000Z"}},
{"firstName":"Lucio","lastName":"Lovatt","email":"llovatt43@fotki.com","gender":"Male","department":"Operations","projects":["Project 9"],"salary":3515,"createdAt":{"$date":"2014-05-28T00:30:00.000Z"}},
{"firstName":"Tabbi","lastName":"Dowd","email":"tdowd44@gnu.org","gender":"Female","department":"R&D","projects":["Project 2","Project 1","Project 5","Project 7"],"salary":5531,"createdAt":{"$date":"2011-08-04T11:59:14.000Z"}},
{"firstName":"Peri","lastName":"Coxen","email":"pcoxen45@oaic.gov.au","gender":"Non-binary","department":"GM","projects":["Project 7"],"salary":6408,"createdAt":{"$date":"2012-01-13T05:23:30.000Z"}},
{"firstName":"Edithe","lastName":"Navarre","email":"enavarre46@dyndns.org","gender":"Female","department":"Operations","projects":["Project 2"],"salary":5751,"createdAt":{"$date":"2015-10-28T21:05:34.000Z"}},
{"firstName":"Nicolais","lastName":"De La Haye","email":"ndelahaye47@hp.com","gender":"Male","department":"HR","projects":["Project 9","Project 8","Project 10"],"salary":4584,"createdAt":{"$date":"2010-07-06T05:08:37.000Z"}},
{"firstName":"Chloette","lastName":"Paslow","email":"cpaslow48@apache.org","gender":"Female","department":"R&D","projects":["Project 1","Project 9","Project 2","Project 3"],"salary":5175,"createdAt":{"$date":"2021-01-16T19:49:36.000Z"}},
{"firstName":"Roma","lastName":"Trousdale","email":"rtrousdale49@nyu.edu","gender":"Male","department":"Production","projects":["Project 6","Project 1","Project 3"],"salary":4986,"createdAt":{"$date":"2012-12-12T12:53:11.000Z"}},
{"firstName":"Zebulon","lastName":"Loudwell","email":"zloudwell4a@ucsd.edu","gender":"Male","department":"Purchase","projects":["Project 2","Project 10"],"salary":5693,"createdAt":{"$date":"2014-08-24T13:36:10.000Z"}},
{"firstName":"Sapphire","lastName":"Wheelband","email":"swheelband4b@sun.com","gender":"Female","department":"Production","projects":["Project 3","Project 2","Project 1","Project 7"],"salary":3737,"createdAt":{"$date":"2011-10-11T03:48:29.000Z"}},
{"firstName":"Valaria","lastName":"Letterese","email":"vletterese4c@so-net.ne.jp","gender":"Female","department":"Operations","projects":["Project 4","Project 5","Project 7"],"salary":5294,"createdAt":{"$date":"2014-10-18T04:32:51.000Z"}},
{"firstName":"Hattie","lastName":"Benny","email":"hbenny4d@umn.edu","gender":"Female","department":"IT","projects":["Project 7","Project 9"],"salary":6316,"createdAt":{"$date":"2013-01-08T22:03:17.000Z"}},
{"firstName":"Arron","lastName":"Woodruffe","email":"awoodruffe4e@mysql.com","gender":"Male","department":"Customer Service","projects":["Project 5","Project 3"],"salary":4840,"createdAt":{"$date":"2021-06-01T07:10:04.000Z"}},
{"firstName":"Roxane","lastName":"Hillyatt","email":"rhillyatt4f@nsw.gov.au","gender":"Female","department":"Customer Service","projects":["Project 2","Project 9","Project 10","Project 8"],"salary":3473,"createdAt":{"$date":"2013-05-19T12:28:29.000Z"}},
{"firstName":"Iosep","lastName":"Gammade","email":"igammade4g@mozilla.com","gender":"Male","department":"Customer Service","projects":["Project 10","Project 6","Project 9"],"salary":4401,"createdAt":{"$date":"2018-06-09T19:45:15.000Z"}},
{"firstName":"Lorna","lastName":"Flisher","email":"lflisher4h@typepad.com","gender":"Non-binary","department":"Sales","projects":["Project 10","Project 8","Project 9"],"salary":5136,"createdAt":{"$date":"2022-01-16T05:15:14.000Z"}},
{"firstName":"Morty","lastName":"Grassot","email":"mgrassot4i@mlb.com","gender":"Male","department":"HR","projects":["Project 9","Project 3","Project 5"],"salary":6252,"createdAt":{"$date":"2011-07-14T00:19:57.000Z"}},
{"firstName":"Percy","lastName":"Troucher","email":"ptroucher4j@japanpost.jp","gender":"Polygender","department":"Finance","projects":["Project 5","Project 10","Project 6","Project 7"],"salary":6195,"createdAt":{"$date":"2010-07-16T03:18:35.000Z"}},
{"firstName":"Kennan","lastName":"Gilmore","email":"kgilmore4k@chron.com","gender":"Male","department":"Purchase","projects":["Project 9","Project 4","Project 7"],"salary":6909,"createdAt":{"$date":"2011-04-19T21:47:04.000Z"}},
{"firstName":"Avictor","lastName":"Onions","email":"aonions4l@auda.org.au","gender":"Male","department":"HR","projects":["Project 2","Project 4","Project 7"],"salary":4402,"createdAt":{"$date":"2018-04-14T03:35:30.000Z"}},
{"firstName":"Hamlin","lastName":"Stiger","email":"hstiger4m@eepurl.com","gender":"Male","department":"Finance","projects":["Project 1","Project 9","Project 6","Project 8"],"salary":3415,"createdAt":{"$date":"2011-01-30T04:28:54.000Z"}},
{"firstName":"Sharla","lastName":"Fry","email":"sfry4n@marketwatch.com","gender":"Female","department":"Finance","projects":["Project 3"],"salary":6709,"createdAt":{"$date":"2019-09-06T22:23:06.000Z"}},
{"firstName":"Darn","lastName":"Rawstorne","email":"drawstorne4o@nba.com","gender":"Male","department":"Customer Service","projects":["Project 7","Project 5","Project 3","Project 6"],"salary":6568,"createdAt":{"$date":"2018-04-26T04:00:25.000Z"}},
{"firstName":"Fanya","lastName":"Vequaud","email":"fvequaud4p@ocn.ne.jp","gender":"Female","department":"GM","projects":["Project 1"],"salary":3711,"createdAt":{"$date":"2020-04-16T16:52:34.000Z"}},
{"firstName":"Valaree","lastName":"Cecely","email":"vcecely4q@walmart.com","gender":"Female","department":"Production","projects":["Project 7","Project 10","Project 9"],"salary":3337,"createdAt":{"$date":"2018-04-27T20:34:18.000Z"}},
{"firstName":"Alfreda","lastName":"Ferber","email":"aferber4r@creativecommons.org","gender":"Female","department":"IT","projects":["Project 3","Project 1","Project 8"],"salary":6428,"createdAt":{"$date":"2010-05-28T12:29:09.000Z"}},
{"firstName":"Pavlov","lastName":"Spurnier","email":"pspurnier4s@sbwire.com","gender":"Male","department":"Production","projects":["Project 7","Project 9","Project 8","Project 4"],"salary":4773,"createdAt":{"$date":"2015-10-02T13:15:58.000Z"}},
{"firstName":"Ediva","lastName":"Smyley","email":"esmyley4t@si.edu","gender":"Female","department":"Finance","projects":["Project 4","Project 3","Project 6"],"salary":6451,"createdAt":{"$date":"2019-10-25T11:46:05.000Z"}},
{"firstName":"Natividad","lastName":"Sines","email":"nsines4u@blinklist.com","gender":"Female","department":"R&D","projects":["Project 4","Project 6"],"salary":5995,"createdAt":{"$date":"2011-07-14T06:05:56.000Z"}},
{"firstName":"Shayna","lastName":"Brodest","email":"sbrodest4v@tmall.com","gender":"Female","department":"Purchase","projects":["Project 4","Project 7","Project 5"],"salary":5655,"createdAt":{"$date":"2015-10-18T22:16:25.000Z"}},
{"firstName":"Zorana","lastName":"Brice","email":"zbrice4w@cargocollective.com","gender":"Female","department":"Purchase","projects":["Project 7","Project 8"],"salary":5420,"createdAt":{"$date":"2013-07-08T23:24:23.000Z"}},
{"firstName":"Devlin","lastName":"Bees","email":"dbees4x@twitter.com","gender":"Male","department":"GM","projects":["Project 8","Project 7","Project 4"],"salary":4803,"createdAt":{"$date":"2021-11-25T21:30:12.000Z"}},
{"firstName":"Lula","lastName":"Duguid","email":"lduguid4y@liveinternet.ru","gender":"Female","department":"Sales","projects":["Project 8"],"salary":4838,"createdAt":{"$date":"2015-03-09T00:42:56.000Z"}},
{"firstName":"Kaila","lastName":"Burdess","email":"kburdess4z@goodreads.com","gender":"Polygender","department":"Operations","projects":["Project 4","Project 9","Project 1","Project 6"],"salary":6928,"createdAt":{"$date":"2017-12-14T06:14:27.000Z"}},
{"firstName":"Jessie","lastName":"Hoffmann","email":"jhoffmann50@4shared.com","gender":"Male","department":"Sales","projects":["Project 4","Project 8","Project 7","Project 2"],"salary":6328,"createdAt":{"$date":"2011-03-20T18:07:57.000Z"}},
{"firstName":"Hilde","lastName":"Wittleton","email":"hwittleton51@reuters.com","gender":"Female","department":"Marketing","projects":["Project 2"],"salary":6146,"createdAt":{"$date":"2022-01-03T14:20:37.000Z"}},
{"firstName":"Currie","lastName":"Meuse","email":"cmeuse52@bbc.co.uk","gender":"Non-binary","department":"Production","projects":["Project 2","Project 10","Project 9","Project 3"],"salary":5843,"createdAt":{"$date":"2020-02-19T12:04:32.000Z"}},
{"firstName":"Gifford","lastName":"Gaitskell","email":"ggaitskell53@hostgator.com","gender":"Genderfluid","department":"Marketing","projects":["Project 4"],"salary":6315,"createdAt":{"$date":"2022-02-23T04:13:52.000Z"}},
{"firstName":"Adara","lastName":"Pattingson","email":"apattingson54@elegantthemes.com","gender":"Female","department":"Purchase","projects":["Project 10","Project 6","Project 5","Project 8"],"salary":3840,"createdAt":{"$date":"2014-12-02T13:38:46.000Z"}},
{"firstName":"Darsey","lastName":"Klemps","email":"dklemps55@nhs.uk","gender":"Female","department":"Customer Service","projects":["Project 9","Project 5"],"salary":5422,"createdAt":{"$date":"2021-07-24T07:11:08.000Z"}},
{"firstName":"Amabelle","lastName":"Dabinett","email":"adabinett56@sourceforge.net","gender":"Non-binary","department":"IT","projects":["Project 2","Project 9","Project 1","Project 5"],"salary":5154,"createdAt":{"$date":"2014-07-27T19:22:25.000Z"}},
{"firstName":"Haily","lastName":"Thebeaud","email":"hthebeaud57@nytimes.com","gender":"Female","department":"Marketing","projects":["Project 8","Project 4","Project 1"],"salary":6012,"createdAt":{"$date":"2021-03-13T20:53:57.000Z"}},
{"firstName":"Diana","lastName":"Bridle","email":"dbridle58@independent.co.uk","gender":"Female","department":"IT","projects":["Project 8","Project 3"],"salary":5445,"createdAt":{"$date":"2016-11-03T14:40:32.000Z"}},
{"firstName":"Loy","lastName":"Cregeen","email":"lcregeen59@phoca.cz","gender":"Male","department":"Purchase","projects":["Project 8","Project 1","Project 4"],"salary":6856,"createdAt":{"$date":"2021-05-21T12:42:17.000Z"}},
{"firstName":"Rhonda","lastName":"Cathel","email":"rcathel5a@japanpost.jp","gender":"Female","department":"Customer Service","projects":["Project 7"],"salary":5460,"createdAt":{"$date":"2015-06-15T17:42:17.000Z"}},
{"firstName":"Eba","lastName":"Elms","email":"eelms5b@linkedin.com","gender":"Female","department":"Marketing","projects":["Project 4"],"salary":5251,"createdAt":{"$date":"2016-04-28T10:07:04.000Z"}},
{"firstName":"Reinwald","lastName":"Gatheral","email":"rgatheral5c@comcast.net","gender":"Male","department":"Finance","projects":["Project 6","Project 7","Project 5","Project 1"],"salary":3362,"createdAt":{"$date":"2021-11-08T18:09:02.000Z"}},
{"firstName":"Alwyn","lastName":"Willimot","email":"awillimot5d@illinois.edu","gender":"Male","department":"Customer Service","projects":["Project 9","Project 5"],"salary":4519,"createdAt":{"$date":"2021-09-27T12:55:44.000Z"}},
{"firstName":"Marita","lastName":"Capps","email":"mcapps5e@sciencedirect.com","gender":"Female","department":"Finance","projects":["Project 7"],"salary":6891,"createdAt":{"$date":"2010-08-31T18:40:09.000Z"}},
{"firstName":"Wenda","lastName":"Beaument","email":"wbeaument5f@jugem.jp","gender":"Female","department":"Finance","projects":["Project 7"],"salary":3673,"createdAt":{"$date":"2017-06-24T02:03:08.000Z"}},
{"firstName":"Valdemar","lastName":"Noir","email":"vnoir5g@newyorker.com","gender":"Male","department":"HR","projects":["Project 8"],"salary":3519,"createdAt":{"$date":"2013-06-24T21:35:27.000Z"}},
{"firstName":"Vannie","lastName":"Charville","email":"vcharville5h@godaddy.com","gender":"Female","department":"HR","projects":["Project 3","Project 4","Project 7"],"salary":6679,"createdAt":{"$date":"2014-04-02T07:12:38.000Z"}},
{"firstName":"Pammie","lastName":"Jones","email":"pjones5i@dailymotion.com","gender":"Female","department":"GM","projects":["Project 9","Project 5"],"salary":4155,"createdAt":{"$date":"2014-01-15T23:26:02.000Z"}},
{"firstName":"Hillyer","lastName":"Hazelgrove","email":"hhazelgrove5j@hugedomains.com","gender":"Male","department":"Customer Service","projects":["Project 3"],"salary":4086,"createdAt":{"$date":"2011-09-07T15:02:44.000Z"}},
{"firstName":"Vivi","lastName":"Gilpillan","email":"vgilpillan5k@bloglines.com","gender":"Female","department":"R&D","projects":["Project 9","Project 6"],"salary":3391,"createdAt":{"$date":"2013-03-01T04:21:47.000Z"}},
{"firstName":"Salomo","lastName":"Tokell","email":"stokell5l@cyberchimps.com","gender":"Male","department":"HR","projects":["Project 9","Project 10"],"salary":5119,"createdAt":{"$date":"2011-03-10T01:12:16.000Z"}},
{"firstName":"Devan","lastName":"Alliker","email":"dalliker5m@hostgator.com","gender":"Female","department":"R&D","projects":["Project 2","Project 7"],"salary":6884,"createdAt":{"$date":"2020-06-08T01:58:45.000Z"}},
{"firstName":"Jany","lastName":"Charlwood","email":"jcharlwood5n@imageshack.us","gender":"Female","department":"GM","projects":["Project 4","Project 9","Project 10","Project 6"],"salary":4435,"createdAt":{"$date":"2014-09-26T02:54:08.000Z"}},
{"firstName":"Pavlov","lastName":"Giraldon","email":"pgiraldon5o@answers.com","gender":"Male","department":"Marketing","projects":["Project 6","Project 9"],"salary":6211,"createdAt":{"$date":"2019-09-11T23:08:37.000Z"}},
{"firstName":"Zechariah","lastName":"Rait","email":"zrait5p@answers.com","gender":"Male","department":"GM","projects":["Project 5","Project 10","Project 8","Project 9"],"salary":3420,"createdAt":{"$date":"2021-06-08T11:08:25.000Z"}},
{"firstName":"Shepperd","lastName":"Rivers","email":"srivers5q@slate.com","gender":"Male","department":"Sales","projects":["Project 7","Project 1","Project 8"],"salary":6101,"createdAt":{"$date":"2015-06-13T06:39:38.000Z"}},
{"firstName":"Shoshana","lastName":"Punchard","email":"spunchard5r@merriam-webster.com","gender":"Female","department":"Sales","projects":["Project 3","Project 7"],"salary":4186,"createdAt":{"$date":"2015-04-15T03:39:35.000Z"}},
{"firstName":"Derek","lastName":"Thickpenny","email":"dthickpenny5s@bigcartel.com","gender":"Male","department":"Customer Service","projects":["Project 4","Project 7"],"salary":6409,"createdAt":{"$date":"2013-02-10T15:51:14.000Z"}},
{"firstName":"Gal","lastName":"Knight","email":"gknight5t@instagram.com","gender":"Male","department":"Production","projects":["Project 2","Project 5"],"salary":5194,"createdAt":{"$date":"2021-12-29T04:19:12.000Z"}},
{"firstName":"Kearney","lastName":"Lamyman","email":"klamyman5u@dot.gov","gender":"Male","department":"IT","projects":["Project 10","Project 5"],"salary":4036,"createdAt":{"$date":"2011-07-22T10:06:31.000Z"}},
{"firstName":"Binny","lastName":"Redgate","email":"bredgate5v@hexun.com","gender":"Female","department":"Finance","projects":["Project 5"],"salary":5202,"createdAt":{"$date":"2018-09-25T09:36:02.000Z"}},
{"firstName":"Hermie","lastName":"McAllister","email":"hmcallister5w@cbsnews.com","gender":"Male","department":"HR","projects":["Project 7","Project 2","Project 1"],"salary":4797,"createdAt":{"$date":"2010-07-16T08:40:17.000Z"}},
{"firstName":"Hatti","lastName":"Prior","email":"hprior5x@networkadvertising.org","gender":"Female","department":"Finance","projects":["Project 2","Project 10"],"salary":4725,"createdAt":{"$date":"2012-06-09T16:01:33.000Z"}},
{"firstName":"Clyde","lastName":"Sherer","email":"csherer5y@dmoz.org","gender":"Male","department":"Purchase","projects":["Project 9","Project 8","Project 2","Project 1"],"salary":3970,"createdAt":{"$date":"2010-12-12T03:04:27.000Z"}},
{"firstName":"Abagael","lastName":"Haselgrove","email":"ahaselgrove5z@buzzfeed.com","gender":"Female","department":"HR","projects":["Project 3"],"salary":3318,"createdAt":{"$date":"2018-06-03T09:03:29.000Z"}},
{"firstName":"Jobey","lastName":"Wallis","email":"jwallis60@dyndns.org","gender":"Non-binary","department":"Finance","projects":["Project 2"],"salary":4285,"createdAt":{"$date":"2018-05-09T18:58:19.000Z"}},
{"firstName":"Gaye","lastName":"Mason","email":"gmason61@t.co","gender":"Female","department":"Operations","projects":["Project 10","Project 8","Project 6","Project 7"],"salary":4601,"createdAt":{"$date":"2016-08-03T06:26:18.000Z"}},
{"firstName":"Kristi","lastName":"Rosengren","email":"krosengren62@home.pl","gender":"Female","department":"Sales","projects":["Project 3"],"salary":4863,"createdAt":{"$date":"2010-07-22T04:26:00.000Z"}},
{"firstName":"Rogers","lastName":"Salle","email":"rsalle63@nytimes.com","gender":"Male","department":"Purchase","projects":["Project 7","Project 8","Project 6","Project 1"],"salary":4077,"createdAt":{"$date":"2012-09-25T11:32:29.000Z"}},
{"firstName":"Cyndy","lastName":"Patnelli","email":"cpatnelli64@google.fr","gender":"Female","department":"Production","projects":["Project 8","Project 4","Project 3","Project 7"],"salary":5373,"createdAt":{"$date":"2015-11-29T17:41:12.000Z"}},
{"firstName":"Brose","lastName":"Rappport","email":"brappport65@mozilla.org","gender":"Male","department":"Marketing","projects":["Project 2"],"salary":4149,"createdAt":{"$date":"2015-02-02T04:25:50.000Z"}},
{"firstName":"Stearn","lastName":"Colquitt","email":"scolquitt66@goo.gl","gender":"Male","department":"Finance","projects":["Project 4","Project 3","Project 8"],"salary":3431,"createdAt":{"$date":"2012-08-03T17:09:13.000Z"}},
{"firstName":"Sibylla","lastName":"Danhel","email":"sdanhel67@yahoo.co.jp","gender":"Female","department":"GM","projects":["Project 3","Project 1","Project 7","Project 5"],"salary":5631,"createdAt":{"$date":"2020-09-02T16:22:26.000Z"}},
{"firstName":"Jany","lastName":"Collaton","email":"jcollaton68@studiopress.com","gender":"Female","department":"Customer Service","projects":["Project 4","Project 9"],"salary":4284,"createdAt":{"$date":"2012-10-06T12:41:59.000Z"}},
{"firstName":"Silvester","lastName":"Beden","email":"sbeden69@quantcast.com","gender":"Male","department":"Production","projects":["Project 3","Project 6"],"salary":6925,"createdAt":{"$date":"2013-10-19T18:45:29.000Z"}},
{"firstName":"Courtnay","lastName":"Hasselby","email":"chasselby6a@webmd.com","gender":"Female","department":"R&D","projects":["Project 4"],"salary":3502,"createdAt":{"$date":"2021-11-29T11:17:08.000Z"}},
{"firstName":"Diarmid","lastName":"Billingham","email":"dbillingham6b@washingtonpost.com","gender":"Male","department":"R&D","projects":["Project 9","Project 6","Project 7"],"salary":5656,"createdAt":{"$date":"2011-05-25T16:01:47.000Z"}},
{"firstName":"Man","lastName":"Gallafant","email":"mgallafant6c@google.it","gender":"Male","department":"Operations","projects":["Project 4"],"salary":5345,"createdAt":{"$date":"2015-06-20T00:24:32.000Z"}},
{"firstName":"Townie","lastName":"Benedito","email":"tbenedito6d@yolasite.com","gender":"Male","department":"GM","projects":["Project 3","Project 9"],"salary":6124,"createdAt":{"$date":"2014-10-12T22:01:49.000Z"}},
{"firstName":"Correy","lastName":"Saffran","email":"csaffran6e@g.co","gender":"Male","department":"Production","projects":["Project 4","Project 6","Project 1"],"salary":3318,"createdAt":{"$date":"2013-01-27T21:21:39.000Z"}},
{"firstName":"Bink","lastName":"Vitte","email":"bvitte6f@simplemachines.org","gender":"Male","department":"GM","projects":["Project 7","Project 2"],"salary":6495,"createdAt":{"$date":"2013-02-04T12:07:40.000Z"}},
{"firstName":"Chantal","lastName":"Clubb","email":"cclubb6g@icio.us","gender":"Female","department":"Customer Service","projects":["Project 8"],"salary":5541,"createdAt":{"$date":"2020-10-24T07:50:21.000Z"}},
{"firstName":"Deane","lastName":"Walhedd","email":"dwalhedd6h@deliciousdays.com","gender":"Male","department":"Production","projects":["Project 7","Project 10","Project 8"],"salary":5875,"createdAt":{"$date":"2010-12-28T11:56:22.000Z"}},
{"firstName":"Lorrie","lastName":"Whitmore","email":"lwhitmore6i@nifty.com","gender":"Female","department":"Finance","projects":["Project 1","Project 10","Project 8","Project 7"],"salary":3309,"createdAt":{"$date":"2020-07-11T07:19:50.000Z"}},
{"firstName":"Glynis","lastName":"Leneve","email":"gleneve6j@oracle.com","gender":"Female","department":"Purchase","projects":["Project 7","Project 6"],"salary":3942,"createdAt":{"$date":"2015-12-11T22:14:49.000Z"}},
{"firstName":"Davidson","lastName":"Malser","email":"dmalser6k@latimes.com","gender":"Male","department":"HR","projects":["Project 9"],"salary":6483,"createdAt":{"$date":"2010-12-02T20:45:02.000Z"}},
{"firstName":"Clare","lastName":"Dorro","email":"cdorro6l@canalblog.com","gender":"Female","department":"Operations","projects":["Project 10","Project 9","Project 3","Project 8"],"salary":5784,"createdAt":{"$date":"2016-09-22T22:36:31.000Z"}},
{"firstName":"Broddie","lastName":"Antoney","email":"bantoney6m@ted.com","gender":"Male","department":"HR","projects":["Project 10","Project 7"],"salary":5981,"createdAt":{"$date":"2011-02-27T08:00:06.000Z"}},
{"firstName":"Demetri","lastName":"Jahnig","email":"djahnig6n@infoseek.co.jp","gender":"Male","department":"Marketing","projects":["Project 6"],"salary":4931,"createdAt":{"$date":"2011-01-06T00:13:54.000Z"}},
{"firstName":"Lorenza","lastName":"Lewington","email":"llewington6o@eepurl.com","gender":"Female","department":"HR","projects":["Project 5","Project 10","Project 8"],"salary":6547,"createdAt":{"$date":"2017-01-19T12:06:05.000Z"}},
{"firstName":"Brooks","lastName":"Meus","email":"bmeus6p@wikispaces.com","gender":"Female","department":"IT","projects":["Project 3"],"salary":6605,"createdAt":{"$date":"2016-11-25T23:07:59.000Z"}},
{"firstName":"De","lastName":"Loxdale","email":"dloxdale6q@newyorker.com","gender":"Female","department":"Marketing","projects":["Project 6","Project 7","Project 10","Project 5"],"salary":3504,"createdAt":{"$date":"2021-08-12T04:25:14.000Z"}},
{"firstName":"Aryn","lastName":"Bohden","email":"abohden6r@sciencedaily.com","gender":"Female","department":"IT","projects":["Project 1"],"salary":4778,"createdAt":{"$date":"2012-10-23T06:24:23.000Z"}},
{"firstName":"Papageno","lastName":"Speddin","email":"pspeddin6s@sakura.ne.jp","gender":"Male","department":"Production","projects":["Project 8","Project 6"],"salary":3447,"createdAt":{"$date":"2017-08-08T14:52:52.000Z"}},
{"firstName":"Sheffield","lastName":"Stiant","email":"sstiant6t@symantec.com","gender":"Male","department":"Purchase","projects":["Project 10","Project 7","Project 6"],"salary":4238,"createdAt":{"$date":"2010-11-04T08:19:46.000Z"}},
{"firstName":"Helen-elizabeth","lastName":"Gratton","email":"hgratton6u@marketwatch.com","gender":"Female","department":"Finance","projects":["Project 8","Project 9","Project 3"],"salary":5093,"createdAt":{"$date":"2012-10-17T03:27:35.000Z"}},
{"firstName":"Jessamine","lastName":"Livesay","email":"jlivesay6v@bluehost.com","gender":"Female","department":"Customer Service","projects":["Project 3"],"salary":5819,"createdAt":{"$date":"2010-10-09T16:33:59.000Z"}},
{"firstName":"Drucill","lastName":"Huzzey","email":"dhuzzey6w@eventbrite.com","gender":"Female","department":"Marketing","projects":["Project 6","Project 10"],"salary":4840,"createdAt":{"$date":"2014-09-09T16:40:38.000Z"}},
{"firstName":"Abran","lastName":"Wickstead","email":"awickstead6x@aol.com","gender":"Male","department":"Purchase","projects":["Project 8","Project 1"],"salary":5244,"createdAt":{"$date":"2021-12-23T04:51:18.000Z"}},
{"firstName":"Clerc","lastName":"Yushmanov","email":"cyushmanov6y@slate.com","gender":"Male","department":"Purchase","projects":["Project 2","Project 3","Project 9"],"salary":5413,"createdAt":{"$date":"2013-10-18T09:27:30.000Z"}},
{"firstName":"Grayce","lastName":"Karpol","email":"gkarpol6z@ehow.com","gender":"Female","department":"HR","projects":["Project 10","Project 9","Project 3","Project 5"],"salary":3653,"createdAt":{"$date":"2018-04-06T21:31:25.000Z"}},
{"firstName":"Shane","lastName":"Dominetti","email":"sdominetti70@youku.com","gender":"Non-binary","department":"IT","projects":["Project 3","Project 2","Project 7"],"salary":4904,"createdAt":{"$date":"2021-09-14T19:16:47.000Z"}},
{"firstName":"Dru","lastName":"Mustoe","email":"dmustoe71@booking.com","gender":"Male","department":"Marketing","projects":["Project 4","Project 3","Project 1","Project 5"],"salary":4133,"createdAt":{"$date":"2020-08-05T00:10:44.000Z"}},
{"firstName":"Rey","lastName":"Lynd","email":"rlynd72@barnesandnoble.com","gender":"Female","department":"HR","projects":["Project 5","Project 10","Project 3"],"salary":4369,"createdAt":{"$date":"2016-01-05T19:05:20.000Z"}},
{"firstName":"Jesus","lastName":"Brooker","email":"jbrooker73@stanford.edu","gender":"Male","department":"GM","projects":["Project 4","Project 2"],"salary":3721,"createdAt":{"$date":"2014-06-04T04:41:10.000Z"}},
{"firstName":"Rinaldo","lastName":"Meneghi","email":"rmeneghi74@parallels.com","gender":"Male","department":"Purchase","projects":["Project 4","Project 7","Project 9","Project 10"],"salary":4093,"createdAt":{"$date":"2015-07-19T08:38:25.000Z"}},
{"firstName":"Milt","lastName":"Lints","email":"mlints75@acquirethisname.com","gender":"Male","department":"Sales","projects":["Project 1"],"salary":5649,"createdAt":{"$date":"2019-01-17T10:41:44.000Z"}},
{"firstName":"Gilbert","lastName":"Ivanichev","email":"givanichev76@nasa.gov","gender":"Male","department":"Finance","projects":["Project 3","Project 10","Project 8","Project 5"],"salary":6110,"createdAt":{"$date":"2018-07-02T14:04:54.000Z"}},
{"firstName":"Karlens","lastName":"Goodbairn","email":"kgoodbairn77@g.co","gender":"Male","department":"Finance","projects":["Project 4","Project 6","Project 1","Project 5"],"salary":4699,"createdAt":{"$date":"2021-12-20T23:15:07.000Z"}},
{"firstName":"Marla","lastName":"Saberton","email":"msaberton78@gmpg.org","gender":"Genderqueer","department":"R&D","projects":["Project 2","Project 4","Project 10","Project 1"],"salary":6446,"createdAt":{"$date":"2010-06-22T03:27:27.000Z"}},
{"firstName":"Fayette","lastName":"McGillivray","email":"fmcgillivray79@purevolume.com","gender":"Female","department":"Finance","projects":["Project 7","Project 3","Project 4","Project 8"],"salary":4502,"createdAt":{"$date":"2015-07-20T13:31:44.000Z"}},
{"firstName":"Valida","lastName":"Cosh","email":"vcosh7a@nasa.gov","gender":"Female","department":"Marketing","projects":["Project 7","Project 2"],"salary":4533,"createdAt":{"$date":"2013-12-10T05:35:22.000Z"}},
{"firstName":"Raeann","lastName":"Donativo","email":"rdonativo7b@slideshare.net","gender":"Female","department":"Purchase","projects":["Project 7","Project 2"],"salary":6128,"createdAt":{"$date":"2013-02-19T21:12:52.000Z"}},
{"firstName":"Papageno","lastName":"Hinsche","email":"phinsche7c@ycombinator.com","gender":"Male","department":"Operations","projects":["Project 9","Project 5"],"salary":5392,"createdAt":{"$date":"2018-05-22T09:39:56.000Z"}},
{"firstName":"Eleanora","lastName":"Hudless","email":"ehudless7d@smh.com.au","gender":"Non-binary","department":"Finance","projects":["Project 6","Project 7"],"salary":3347,"createdAt":{"$date":"2013-01-13T19:21:38.000Z"}},
{"firstName":"Milton","lastName":"Shellibeer","email":"mshellibeer7e@soundcloud.com","gender":"Male","department":"Marketing","projects":["Project 9","Project 8","Project 7"],"salary":3453,"createdAt":{"$date":"2011-09-05T08:40:16.000Z"}},
{"firstName":"Melisse","lastName":"Malim","email":"mmalim7f@storify.com","gender":"Non-binary","department":"R&D","projects":["Project 9","Project 3","Project 4"],"salary":3505,"createdAt":{"$date":"2015-04-22T18:35:42.000Z"}},
{"firstName":"Sim","lastName":"Litt","email":"slitt7g@multiply.com","gender":"Male","department":"HR","projects":["Project 1","Project 6","Project 8","Project 4"],"salary":3948,"createdAt":{"$date":"2014-05-14T00:37:53.000Z"}},
{"firstName":"Robin","lastName":"Vannuccinii","email":"rvannuccinii7h@shinystat.com","gender":"Female","department":"R&D","projects":["Project 3","Project 9","Project 6"],"salary":6872,"createdAt":{"$date":"2010-05-13T05:39:56.000Z"}},
{"firstName":"Mycah","lastName":"Lundbeck","email":"mlundbeck7i@wired.com","gender":"Male","department":"Purchase","projects":["Project 6"],"salary":4070,"createdAt":{"$date":"2016-09-27T01:52:43.000Z"}},
{"firstName":"Vivienne","lastName":"Ratke","email":"vratke7j@newsvine.com","gender":"Female","department":"Sales","projects":["Project 7","Project 8","Project 6"],"salary":5275,"createdAt":{"$date":"2011-01-12T21:18:23.000Z"}},
{"firstName":"Tiebout","lastName":"Stutely","email":"tstutely7k@jugem.jp","gender":"Male","department":"GM","projects":["Project 3","Project 6","Project 9"],"salary":5931,"createdAt":{"$date":"2013-04-29T21:57:25.000Z"}},
{"firstName":"Emmery","lastName":"Flucker","email":"eflucker7l@nifty.com","gender":"Male","department":"GM","projects":["Project 1","Project 10"],"salary":4824,"createdAt":{"$date":"2011-05-27T20:56:49.000Z"}},
{"firstName":"Antony","lastName":"Goodman","email":"agoodman7m@google.cn","gender":"Male","department":"Production","projects":["Project 4","Project 6","Project 8"],"salary":3361,"createdAt":{"$date":"2017-06-26T09:44:06.000Z"}},
{"firstName":"Mohandis","lastName":"Egar","email":"megar7n@discuz.net","gender":"Male","department":"Customer Service","projects":["Project 2","Project 5"],"salary":3868,"createdAt":{"$date":"2011-04-27T17:13:29.000Z"}},
{"firstName":"Sabrina","lastName":"Brinkworth","email":"sbrinkworth7o@canalblog.com","gender":"Female","department":"Operations","projects":["Project 4","Project 8","Project 9"],"salary":6295,"createdAt":{"$date":"2017-04-28T09:27:04.000Z"}},
{"firstName":"Hailee","lastName":"Woolerton","email":"hwoolerton7p@goodreads.com","gender":"Female","department":"Sales","projects":["Project 8","Project 5","Project 10","Project 1"],"salary":5221,"createdAt":{"$date":"2013-05-25T22:46:58.000Z"}},
{"firstName":"Marice","lastName":"Croote","email":"mcroote7q@eepurl.com","gender":"Female","department":"Finance","projects":["Project 5"],"salary":5030,"createdAt":{"$date":"2018-09-13T18:39:13.000Z"}},
{"firstName":"Raynor","lastName":"Pickvance","email":"rpickvance7r@wix.com","gender":"Male","department":"Finance","projects":["Project 9","Project 6"],"salary":6638,"createdAt":{"$date":"2017-10-02T07:10:47.000Z"}},
{"firstName":"Sinclair","lastName":"Ruffy","email":"sruffy7s@springer.com","gender":"Male","department":"R&D","projects":["Project 10","Project 1","Project 7"],"salary":3684,"createdAt":{"$date":"2016-09-15T22:54:10.000Z"}},
{"firstName":"Shoshanna","lastName":"Luker","email":"sluker7t@instagram.com","gender":"Female","department":"HR","projects":["Project 10","Project 1"],"salary":5007,"createdAt":{"$date":"2013-10-25T14:45:29.000Z"}},
{"firstName":"Francesco","lastName":"Orwin","email":"forwin7u@state.tx.us","gender":"Male","department":"Purchase","projects":["Project 5"],"salary":4464,"createdAt":{"$date":"2013-05-24T02:23:37.000Z"}},
{"firstName":"Kirbee","lastName":"Chicotti","email":"kchicotti7v@desdev.cn","gender":"Female","department":"R&D","projects":["Project 1"],"salary":3895,"createdAt":{"$date":"2016-04-29T11:51:27.000Z"}},
{"firstName":"Aidan","lastName":"Canet","email":"acanet7w@latimes.com","gender":"Female","department":"GM","projects":["Project 1","Project 3","Project 9","Project 4"],"salary":4328,"createdAt":{"$date":"2014-08-17T21:07:41.000Z"}},
{"firstName":"Ceciley","lastName":"Tewkesberrie","email":"ctewkesberrie7x@uiuc.edu","gender":"Female","department":"Purchase","projects":["Project 6","Project 10"],"salary":5783,"createdAt":{"$date":"2021-06-08T16:05:05.000Z"}},
{"firstName":"Marabel","lastName":"Pendered","email":"mpendered7y@umn.edu","gender":"Female","department":"HR","projects":["Project 1","Project 3","Project 8"],"salary":6446,"createdAt":{"$date":"2015-10-07T05:47:40.000Z"}},
{"firstName":"Ettore","lastName":"Rubin","email":"erubin7z@clickbank.net","gender":"Male","department":"IT","projects":["Project 6","Project 7","Project 1"],"salary":5175,"createdAt":{"$date":"2020-08-22T08:06:15.000Z"}},
{"firstName":"Loraine","lastName":"McMullen","email":"lmcmullen80@amazon.co.jp","gender":"Female","department":"GM","projects":["Project 1","Project 5"],"salary":6957,"createdAt":{"$date":"2013-09-24T22:24:14.000Z"}},
{"firstName":"Markos","lastName":"Flinders","email":"mflinders81@mozilla.org","gender":"Male","department":"Purchase","projects":["Project 3","Project 10","Project 2"],"salary":4242,"createdAt":{"$date":"2021-06-22T02:55:16.000Z"}},
{"firstName":"Trevar","lastName":"Hulland","email":"thulland82@youtube.com","gender":"Male","department":"Customer Service","projects":["Project 1","Project 8","Project 6","Project 10"],"salary":4234,"createdAt":{"$date":"2014-01-03T03:16:14.000Z"}},
{"firstName":"Filippo","lastName":"Marquiss","email":"fmarquiss83@feedburner.com","gender":"Male","department":"R&D","projects":["Project 6","Project 9","Project 1"],"salary":3563,"createdAt":{"$date":"2021-06-01T01:43:27.000Z"}},
{"firstName":"Salim","lastName":"Woodbridge","email":"swoodbridge84@etsy.com","gender":"Male","department":"IT","projects":["Project 1","Project 9"],"salary":3894,"createdAt":{"$date":"2014-02-19T11:03:51.000Z"}},
{"firstName":"Ardyth","lastName":"McGourty","email":"amcgourty85@wikipedia.org","gender":"Female","department":"Operations","projects":["Project 4"],"salary":4220,"createdAt":{"$date":"2010-10-25T00:08:14.000Z"}},
{"firstName":"Darcee","lastName":"Sommerscales","email":"dsommerscales86@1und1.de","gender":"Genderfluid","department":"Purchase","projects":["Project 10"],"salary":4519,"createdAt":{"$date":"2019-02-10T07:06:45.000Z"}},
{"firstName":"Berke","lastName":"Hearons","email":"bhearons87@a8.net","gender":"Male","department":"R&D","projects":["Project 5","Project 7","Project 1"],"salary":5039,"createdAt":{"$date":"2013-05-28T20:30:10.000Z"}},
{"firstName":"Leonerd","lastName":"Phelan","email":"lphelan88@liveinternet.ru","gender":"Male","department":"GM","projects":["Project 3","Project 10","Project 7"],"salary":5407,"createdAt":{"$date":"2015-06-09T09:59:23.000Z"}},
{"firstName":"Roslyn","lastName":"Gonzalez","email":"rgonzalez89@a8.net","gender":"Female","department":"Operations","projects":["Project 1","Project 3"],"salary":6454,"createdAt":{"$date":"2011-10-01T09:04:26.000Z"}},
{"firstName":"Udale","lastName":"Kittoe","email":"ukittoe8a@mapy.cz","gender":"Male","department":"Marketing","projects":["Project 3","Project 1","Project 5"],"salary":5486,"createdAt":{"$date":"2013-02-03T13:36:38.000Z"}},
{"firstName":"Karalynn","lastName":"Playdon","email":"kplaydon8b@weibo.com","gender":"Female","department":"Production","projects":["Project 10","Project 1","Project 6","Project 5"],"salary":3614,"createdAt":{"$date":"2020-06-02T05:46:23.000Z"}},
{"firstName":"Ardys","lastName":"Christall","email":"achristall8c@virginia.edu","gender":"Female","department":"IT","projects":["Project 3","Project 9","Project 7"],"salary":6964,"createdAt":{"$date":"2016-12-07T02:54:17.000Z"}},
{"firstName":"Jodie","lastName":"Kensit","email":"jkensit8d@com.com","gender":"Male","department":"IT","projects":["Project 9"],"salary":3734,"createdAt":{"$date":"2018-08-14T15:30:09.000Z"}},
{"firstName":"Anastasie","lastName":"Abbes","email":"aabbes8e@sphinn.com","gender":"Female","department":"R&D","projects":["Project 2","Project 9","Project 3","Project 4"],"salary":6471,"createdAt":{"$date":"2013-08-25T23:31:37.000Z"}},
{"firstName":"Shana","lastName":"Sach","email":"ssach8f@technorati.com","gender":"Female","department":"Operations","projects":["Project 9","Project 2"],"salary":3474,"createdAt":{"$date":"2019-08-15T00:55:06.000Z"}},
{"firstName":"Cyrillus","lastName":"Selly","email":"cselly8g@wikimedia.org","gender":"Male","department":"Customer Service","projects":["Project 6","Project 8","Project 5"],"salary":4628,"createdAt":{"$date":"2013-10-26T14:11:38.000Z"}},
{"firstName":"Leighton","lastName":"Juanes","email":"ljuanes8h@tiny.cc","gender":"Male","department":"Purchase","projects":["Project 4","Project 3","Project 10"],"salary":6442,"createdAt":{"$date":"2012-08-19T15:44:06.000Z"}},
{"firstName":"Karlen","lastName":"Borris","email":"kborris8i@4shared.com","gender":"Female","department":"IT","projects":["Project 1","Project 4"],"salary":3983,"createdAt":{"$date":"2012-09-26T12:53:01.000Z"}},
{"firstName":"Massimiliano","lastName":"Stansell","email":"mstansell8j@senate.gov","gender":"Male","department":"Operations","projects":["Project 10"],"salary":5519,"createdAt":{"$date":"2013-12-02T03:11:39.000Z"}},
{"firstName":"Ketti","lastName":"Landall","email":"klandall8k@google.ca","gender":"Female","department":"Sales","projects":["Project 9"],"salary":3582,"createdAt":{"$date":"2017-02-05T16:17:33.000Z"}},
{"firstName":"Jakob","lastName":"O'Hara","email":"johara8l@yahoo.com","gender":"Male","department":"Sales","projects":["Project 8","Project 6","Project 10"],"salary":5767,"createdAt":{"$date":"2020-10-03T08:19:40.000Z"}},
{"firstName":"Paul","lastName":"Bryden","email":"pbryden8m@elpais.com","gender":"Male","department":"IT","projects":["Project 5","Project 7","Project 9"],"salary":4297,"createdAt":{"$date":"2019-08-13T08:05:44.000Z"}},
{"firstName":"Roscoe","lastName":"McLeod","email":"rmcleod8n@washingtonpost.com","gender":"Male","department":"R&D","projects":["Project 3"],"salary":5598,"createdAt":{"$date":"2010-12-03T08:09:25.000Z"}},
{"firstName":"Caitlin","lastName":"Lynd","email":"clynd8o@bloglines.com","gender":"Female","department":"Customer Service","projects":["Project 1"],"salary":4132,"createdAt":{"$date":"2019-03-06T22:08:05.000Z"}},
{"firstName":"Francine","lastName":"Sedgemond","email":"fsedgemond8p@weather.com","gender":"Female","department":"Purchase","projects":["Project 3","Project 1","Project 10","Project 7"],"salary":5986,"createdAt":{"$date":"2011-05-18T15:43:18.000Z"}},
{"firstName":"Jorey","lastName":"McMurraya","email":"jmcmurraya8q@whitehouse.gov","gender":"Female","department":"HR","projects":["Project 5"],"salary":5190,"createdAt":{"$date":"2012-09-26T03:54:03.000Z"}},
{"firstName":"Edee","lastName":"Emmison","email":"eemmison8r@histats.com","gender":"Female","department":"Finance","projects":["Project 6"],"salary":3532,"createdAt":{"$date":"2012-07-04T10:24:58.000Z"}},
{"firstName":"Nata","lastName":"Dufour","email":"ndufour8s@baidu.com","gender":"Female","department":"Purchase","projects":["Project 8","Project 4","Project 3"],"salary":3984,"createdAt":{"$date":"2020-02-01T14:05:45.000Z"}},
{"firstName":"Charlie","lastName":"Domnick","email":"cdomnick8t@hc360.com","gender":"Male","department":"Marketing","projects":["Project 3","Project 5","Project 7","Project 10"],"salary":5587,"createdAt":{"$date":"2016-05-26T19:22:44.000Z"}},
{"firstName":"Jada","lastName":"Neligan","email":"jneligan8u@theguardian.com","gender":"Non-binary","department":"Production","projects":["Project 8","Project 1"],"salary":4664,"createdAt":{"$date":"2020-08-17T09:11:59.000Z"}},
{"firstName":"Meir","lastName":"Larchiere","email":"mlarchiere8v@baidu.com","gender":"Male","department":"HR","projects":["Project 4","Project 3","Project 6"],"salary":4182,"createdAt":{"$date":"2017-10-24T23:27:03.000Z"}},
{"firstName":"Tyne","lastName":"Garces","email":"tgarces8w@hibu.com","gender":"Female","department":"IT","projects":["Project 3","Project 5","Project 6","Project 7"],"salary":5154,"createdAt":{"$date":"2015-04-07T17:59:58.000Z"}},
{"firstName":"Philis","lastName":"Maulkin","email":"pmaulkin8x@plala.or.jp","gender":"Non-binary","department":"Customer Service","projects":["Project 1"],"salary":5835,"createdAt":{"$date":"2012-04-29T21:53:56.000Z"}},
{"firstName":"Kile","lastName":"Gough","email":"kgough8y@fastcompany.com","gender":"Male","department":"Purchase","projects":["Project 8","Project 7","Project 10"],"salary":5968,"createdAt":{"$date":"2019-10-08T15:03:51.000Z"}},
{"firstName":"Karl","lastName":"Addy","email":"kaddy8z@europa.eu","gender":"Male","department":"R&D","projects":["Project 1","Project 6","Project 5","Project 10"],"salary":5001,"createdAt":{"$date":"2021-08-07T07:11:43.000Z"}},
{"firstName":"Emmalynne","lastName":"More","email":"emore90@wordpress.com","gender":"Female","department":"Marketing","projects":["Project 3","Project 5"],"salary":6209,"createdAt":{"$date":"2010-04-13T20:16:08.000Z"}},
{"firstName":"Cullan","lastName":"Plumb","email":"cplumb91@typepad.com","gender":"Male","department":"Purchase","projects":["Project 9"],"salary":5199,"createdAt":{"$date":"2011-01-13T12:21:50.000Z"}},
{"firstName":"Leslie","lastName":"Allner","email":"lallner92@clickbank.net","gender":"Female","department":"Operations","projects":["Project 4"],"salary":6465,"createdAt":{"$date":"2018-01-26T03:44:19.000Z"}},
{"firstName":"Horton","lastName":"Ackroyd","email":"hackroyd93@sphinn.com","gender":"Male","department":"Customer Service","projects":["Project 8","Project 7","Project 5","Project 4"],"salary":6610,"createdAt":{"$date":"2019-02-23T04:28:58.000Z"}},
{"firstName":"Edithe","lastName":"Luggar","email":"eluggar94@gmpg.org","gender":"Female","department":"Operations","projects":["Project 3","Project 8","Project 4","Project 7"],"salary":6859,"createdAt":{"$date":"2020-01-02T06:15:25.000Z"}},
{"firstName":"Laurena","lastName":"Verzey","email":"lverzey95@virginia.edu","gender":"Non-binary","department":"Operations","projects":["Project 9","Project 3","Project 6"],"salary":3388,"createdAt":{"$date":"2019-01-16T09:18:14.000Z"}},
{"firstName":"Onida","lastName":"Troughton","email":"otroughton96@mediafire.com","gender":"Female","department":"Production","projects":["Project 5","Project 1","Project 10","Project 4"],"salary":4996,"createdAt":{"$date":"2010-05-31T13:29:37.000Z"}},
{"firstName":"Shaylyn","lastName":"Matkovic","email":"smatkovic97@uol.com.br","gender":"Female","department":"Customer Service","projects":["Project 4","Project 5","Project 1","Project 2"],"salary":6528,"createdAt":{"$date":"2020-04-10T08:04:34.000Z"}},
{"firstName":"Theo","lastName":"Beckford","email":"tbeckford98@sogou.com","gender":"Male","department":"Purchase","projects":["Project 8"],"salary":4930,"createdAt":{"$date":"2019-09-09T04:24:20.000Z"}},
{"firstName":"Marleen","lastName":"Sawyer","email":"msawyer99@go.com","gender":"Female","department":"R&D","projects":["Project 3"],"salary":4593,"createdAt":{"$date":"2017-06-22T11:40:56.000Z"}},
{"firstName":"Brit","lastName":"Cranston","email":"bcranston9a@nytimes.com","gender":"Female","department":"Sales","projects":["Project 8","Project 6"],"salary":4977,"createdAt":{"$date":"2019-03-03T10:24:06.000Z"}},
{"firstName":"Melesa","lastName":"Dutteridge","email":"mdutteridge9b@dot.gov","gender":"Female","department":"Customer Service","projects":["Project 6","Project 10","Project 1"],"salary":5207,"createdAt":{"$date":"2019-01-08T12:26:50.000Z"}},
{"firstName":"Wiley","lastName":"Busse","email":"wbusse9c@sun.com","gender":"Male","department":"Production","projects":["Project 2","Project 1"],"salary":6792,"createdAt":{"$date":"2012-08-22T00:26:07.000Z"}},
{"firstName":"Mata","lastName":"Cragg","email":"mcragg9d@rambler.ru","gender":"Male","department":"IT","projects":["Project 3","Project 2","Project 6"],"salary":5917,"createdAt":{"$date":"2019-10-19T05:46:57.000Z"}},
{"firstName":"Isidoro","lastName":"Jessen","email":"ijessen9e@rediff.com","gender":"Male","department":"Finance","projects":["Project 8","Project 1","Project 4"],"salary":6226,"createdAt":{"$date":"2012-03-26T10:48:36.000Z"}},
{"firstName":"Cliff","lastName":"Ambrois","email":"cambrois9f@dmoz.org","gender":"Male","department":"IT","projects":["Project 3","Project 7"],"salary":4717,"createdAt":{"$date":"2020-12-31T00:41:18.000Z"}},
{"firstName":"Cecilla","lastName":"MacCallum","email":"cmaccallum9g@weather.com","gender":"Female","department":"Sales","projects":["Project 4","Project 2"],"salary":6797,"createdAt":{"$date":"2021-12-03T06:17:22.000Z"}},
{"firstName":"Quinn","lastName":"Gherardi","email":"qgherardi9h@php.net","gender":"Female","department":"HR","projects":["Project 7"],"salary":6357,"createdAt":{"$date":"2021-08-21T16:34:30.000Z"}},
{"firstName":"Kiele","lastName":"Whitton","email":"kwhitton9i@nsw.gov.au","gender":"Genderfluid","department":"Operations","projects":["Project 3","Project 10","Project 6"],"salary":3735,"createdAt":{"$date":"2019-03-07T01:31:06.000Z"}},
{"firstName":"Katerina","lastName":"Lutas","email":"klutas9j@elegantthemes.com","gender":"Female","department":"Production","projects":["Project 10","Project 9"],"salary":4489,"createdAt":{"$date":"2014-01-15T08:55:27.000Z"}},
{"firstName":"Teodorico","lastName":"Sabin","email":"tsabin9k@whitehouse.gov","gender":"Bigender","department":"Purchase","projects":["Project 4","Project 10"],"salary":5437,"createdAt":{"$date":"2013-05-29T19:25:18.000Z"}},
{"firstName":"Lanny","lastName":"Maiklem","email":"lmaiklem9l@prlog.org","gender":"Female","department":"Marketing","projects":["Project 6"],"salary":6976,"createdAt":{"$date":"2012-04-16T19:27:45.000Z"}},
{"firstName":"Christel","lastName":"Eyckel","email":"ceyckel9m@hud.gov","gender":"Female","department":"IT","projects":["Project 1","Project 2"],"salary":3551,"createdAt":{"$date":"2015-02-03T11:29:01.000Z"}},
{"firstName":"Vachel","lastName":"Ready","email":"vready9n@163.com","gender":"Male","department":"R&D","projects":["Project 9"],"salary":3813,"createdAt":{"$date":"2014-02-08T05:39:11.000Z"}},
{"firstName":"Aurilia","lastName":"Sanbrooke","email":"asanbrooke9o@cloudflare.com","gender":"Female","department":"Operations","projects":["Project 9","Project 10","Project 2"],"salary":5651,"createdAt":{"$date":"2021-04-20T13:41:37.000Z"}},
{"firstName":"Chaddie","lastName":"Kealy","email":"ckealy9p@china.com.cn","gender":"Male","department":"Finance","projects":["Project 7"],"salary":5264,"createdAt":{"$date":"2019-09-26T12:44:01.000Z"}},
{"firstName":"Marianne","lastName":"Haswall","email":"mhaswall9q@51.la","gender":"Genderfluid","department":"GM","projects":["Project 6","Project 5","Project 10","Project 3"],"salary":3939,"createdAt":{"$date":"2019-11-19T07:39:26.000Z"}},
{"firstName":"Lucie","lastName":"Wolford","email":"lwolford9r@opensource.org","gender":"Female","department":"R&D","projects":["Project 10"],"salary":4862,"createdAt":{"$date":"2020-11-24T06:10:06.000Z"}},
{"firstName":"Mayer","lastName":"Janovsky","email":"mjanovsky9s@clickbank.net","gender":"Male","department":"Production","projects":["Project 8","Project 4","Project 9","Project 7"],"salary":3379,"createdAt":{"$date":"2011-01-10T06:57:56.000Z"}},
{"firstName":"Hubie","lastName":"Schubert","email":"hschubert9t@fastcompany.com","gender":"Male","department":"Purchase","projects":["Project 10","Project 2"],"salary":6047,"createdAt":{"$date":"2017-09-12T21:36:29.000Z"}},
{"firstName":"Tani","lastName":"Blasl","email":"tblasl9u@barnesandnoble.com","gender":"Female","department":"R&D","projects":["Project 5"],"salary":5505,"createdAt":{"$date":"2018-10-15T00:18:55.000Z"}},
{"firstName":"Arnold","lastName":"Stiebler","email":"astiebler9v@cam.ac.uk","gender":"Male","department":"GM","projects":["Project 5"],"salary":5512,"createdAt":{"$date":"2014-04-26T11:13:47.000Z"}},
{"firstName":"James","lastName":"Gunby","email":"jgunby9w@artisteer.com","gender":"Male","department":"Marketing","projects":["Project 5","Project 6","Project 10"],"salary":6987,"createdAt":{"$date":"2018-03-26T05:25:08.000Z"}},
{"firstName":"Cozmo","lastName":"Vidyapin","email":"cvidyapin9x@cdc.gov","gender":"Male","department":"R&D","projects":["Project 6","Project 9","Project 2"],"salary":3888,"createdAt":{"$date":"2019-11-24T06:55:16.000Z"}},
{"firstName":"Lorin","lastName":"Stoter","email":"lstoter9y@telegraph.co.uk","gender":"Male","department":"Purchase","projects":["Project 1","Project 10","Project 3","Project 9"],"salary":6993,"createdAt":{"$date":"2017-01-08T11:19:24.000Z"}},
{"firstName":"Claybourne","lastName":"Kitchenside","email":"ckitchenside9z@jugem.jp","gender":"Male","department":"Sales","projects":["Project 4","Project 5"],"salary":5019,"createdAt":{"$date":"2020-05-31T23:40:38.000Z"}},
{"firstName":"Heida","lastName":"Cridge","email":"hcridgea0@upenn.edu","gender":"Female","department":"Operations","projects":["Project 6","Project 2","Project 9","Project 10"],"salary":4044,"createdAt":{"$date":"2014-01-14T03:06:08.000Z"}},
{"firstName":"Elladine","lastName":"Anniwell","email":"eanniwella1@stumbleupon.com","gender":"Female","department":"Operations","projects":["Project 3"],"salary":6983,"createdAt":{"$date":"2010-01-24T12:50:53.000Z"}},
{"firstName":"Rosalynd","lastName":"Turrill","email":"rturrilla2@ow.ly","gender":"Female","department":"GM","projects":["Project 1","Project 3","Project 9"],"salary":6382,"createdAt":{"$date":"2021-03-31T22:15:20.000Z"}},
{"firstName":"Nolly","lastName":"Abraham","email":"nabrahama3@admin.ch","gender":"Male","department":"Marketing","projects":["Project 6","Project 3","Project 1","Project 8"],"salary":6206,"createdAt":{"$date":"2013-06-09T15:32:05.000Z"}},
{"firstName":"Andy","lastName":"Varfalameev","email":"avarfalameeva4@nhs.uk","gender":"Female","department":"Production","projects":["Project 5","Project 9"],"salary":3530,"createdAt":{"$date":"2015-06-20T12:55:46.000Z"}},
{"firstName":"Myrtle","lastName":"August","email":"maugusta5@google.es","gender":"Female","department":"Purchase","projects":["Project 8","Project 3"],"salary":5111,"createdAt":{"$date":"2018-11-08T20:52:01.000Z"}},
{"firstName":"Adan","lastName":"Dybbe","email":"adybbea6@github.io","gender":"Female","department":"Customer Service","projects":["Project 8","Project 5","Project 6","Project 4"],"salary":5722,"createdAt":{"$date":"2018-06-22T03:29:16.000Z"}},
{"firstName":"Hilliard","lastName":"Lafferty","email":"hlaffertya7@com.com","gender":"Male","department":"Customer Service","projects":["Project 8","Project 3","Project 7","Project 1"],"salary":6849,"createdAt":{"$date":"2019-12-17T09:40:42.000Z"}},
{"firstName":"Nevin","lastName":"Dalrymple","email":"ndalrymplea8@google.co.jp","gender":"Male","department":"Sales","projects":["Project 8","Project 5"],"salary":6392,"createdAt":{"$date":"2018-08-27T18:52:10.000Z"}},
{"firstName":"Beryle","lastName":"Brokenshaw","email":"bbrokenshawa9@marriott.com","gender":"Female","department":"Sales","projects":["Project 8","Project 6","Project 4"],"salary":6400,"createdAt":{"$date":"2018-02-18T08:55:33.000Z"}},
{"firstName":"Eda","lastName":"Darcey","email":"edarceyaa@privacy.gov.au","gender":"Female","department":"Production","projects":["Project 5","Project 9"],"salary":5500,"createdAt":{"$date":"2013-03-12T04:25:28.000Z"}},
{"firstName":"Vanya","lastName":"Cutteridge","email":"vcutteridgeab@smh.com.au","gender":"Female","department":"Operations","projects":["Project 5","Project 8","Project 7","Project 1"],"salary":5120,"createdAt":{"$date":"2014-11-30T15:09:45.000Z"}},
{"firstName":"Jade","lastName":"Burtenshaw","email":"jburtenshawac@studiopress.com","gender":"Female","department":"GM","projects":["Project 1"],"salary":3912,"createdAt":{"$date":"2017-05-12T22:13:50.000Z"}},
{"firstName":"Anthe","lastName":"Dybald","email":"adybaldad@slideshare.net","gender":"Female","department":"HR","projects":["Project 5","Project 10","Project 4"],"salary":6116,"createdAt":{"$date":"2020-04-07T08:55:53.000Z"}},
{"firstName":"Valdemar","lastName":"Gullefant","email":"vgullefantae@google.com.au","gender":"Male","department":"R&D","projects":["Project 9","Project 8","Project 10","Project 3"],"salary":5847,"createdAt":{"$date":"2021-07-23T06:16:16.000Z"}},
{"firstName":"Anett","lastName":"Biskupek","email":"abiskupekaf@thetimes.co.uk","gender":"Female","department":"Purchase","projects":["Project 1"],"salary":5400,"createdAt":{"$date":"2013-04-11T22:57:32.000Z"}},
{"firstName":"Fairlie","lastName":"Tomlin","email":"ftomlinag@microsoft.com","gender":"Male","department":"HR","projects":["Project 5"],"salary":4719,"createdAt":{"$date":"2017-02-16T04:24:51.000Z"}},
{"firstName":"Tedman","lastName":"Mosconi","email":"tmosconiah@issuu.com","gender":"Male","department":"Marketing","projects":["Project 6","Project 5"],"salary":5522,"createdAt":{"$date":"2016-06-03T11:55:11.000Z"}},
{"firstName":"Christan","lastName":"Purdey","email":"cpurdeyai@yandex.ru","gender":"Female","department":"Operations","projects":["Project 9"],"salary":5192,"createdAt":{"$date":"2011-09-09T22:53:20.000Z"}},
{"firstName":"Felecia","lastName":"Bolsteridge","email":"fbolsteridgeaj@eventbrite.com","gender":"Female","department":"HR","projects":["Project 4","Project 6","Project 5"],"salary":4765,"createdAt":{"$date":"2019-12-04T14:30:38.000Z"}},
{"firstName":"Prissie","lastName":"Lathbury","email":"plathburyak@ca.gov","gender":"Female","department":"Operations","projects":["Project 5"],"salary":6323,"createdAt":{"$date":"2016-10-18T14:46:08.000Z"}},
{"firstName":"Leola","lastName":"Libreros","email":"llibrerosal@tmall.com","gender":"Female","department":"Customer Service","projects":["Project 1","Project 6","Project 5"],"salary":3717,"createdAt":{"$date":"2014-06-10T13:22:54.000Z"}},
{"firstName":"Moore","lastName":"O'Heaney","email":"moheaneyam@engadget.com","gender":"Male","department":"Operations","projects":["Project 3"],"salary":4199,"createdAt":{"$date":"2022-01-30T14:44:48.000Z"}},
{"firstName":"Prissie","lastName":"Gunney","email":"pgunneyan@icq.com","gender":"Female","department":"IT","projects":["Project 8","Project 1"],"salary":5260,"createdAt":{"$date":"2018-02-27T02:00:07.000Z"}},
{"firstName":"Alyce","lastName":"Sambals","email":"asambalsao@bbc.co.uk","gender":"Female","department":"Purchase","projects":["Project 4","Project 3"],"salary":3767,"createdAt":{"$date":"2010-06-24T19:39:01.000Z"}},
{"firstName":"Annemarie","lastName":"Cattermoul","email":"acattermoulap@uiuc.edu","gender":"Agender","department":"HR","projects":["Project 6","Project 2","Project 8","Project 1"],"salary":5432,"createdAt":{"$date":"2011-08-25T15:42:39.000Z"}},
{"firstName":"Jacklin","lastName":"Mullord","email":"jmullordaq@auda.org.au","gender":"Female","department":"Purchase","projects":["Project 2","Project 10","Project 4","Project 7"],"salary":6853,"createdAt":{"$date":"2013-03-07T09:47:55.000Z"}},
{"firstName":"Cleo","lastName":"McRuvie","email":"cmcruviear@weibo.com","gender":"Female","department":"HR","projects":["Project 5"],"salary":6193,"createdAt":{"$date":"2015-07-28T16:59:59.000Z"}},
{"firstName":"Barby","lastName":"Pocke","email":"bpockeas@artisteer.com","gender":"Female","department":"IT","projects":["Project 9","Project 5"],"salary":6803,"createdAt":{"$date":"2011-05-11T19:38:46.000Z"}},
{"firstName":"Stanton","lastName":"Pethrick","email":"spethrickat@macromedia.com","gender":"Genderfluid","department":"Customer Service","projects":["Project 7","Project 6","Project 2","Project 8"],"salary":5297,"createdAt":{"$date":"2019-01-02T21:12:57.000Z"}},
{"firstName":"Egbert","lastName":"Poxon","email":"epoxonau@statcounter.com","gender":"Male","department":"HR","projects":["Project 8","Project 2"],"salary":5246,"createdAt":{"$date":"2019-11-19T07:22:43.000Z"}},
{"firstName":"Ambrose","lastName":"Roostan","email":"aroostanav@ftc.gov","gender":"Male","department":"R&D","projects":["Project 7","Project 5","Project 3"],"salary":4042,"createdAt":{"$date":"2017-12-05T08:40:28.000Z"}},
{"firstName":"Sasha","lastName":"Blackadder","email":"sblackadderaw@ca.gov","gender":"Female","department":"Finance","projects":["Project 8"],"salary":4008,"createdAt":{"$date":"2019-03-07T04:36:09.000Z"}},
{"firstName":"Calvin","lastName":"Yuryev","email":"cyuryevax@smugmug.com","gender":"Male","department":"Sales","projects":["Project 2","Project 10"],"salary":5267,"createdAt":{"$date":"2013-08-06T10:11:33.000Z"}},
{"firstName":"Alastair","lastName":"Larrat","email":"alarratay@nifty.com","gender":"Male","department":"Sales","projects":["Project 2","Project 10","Project 6"],"salary":6514,"createdAt":{"$date":"2015-07-19T12:32:33.000Z"}},
{"firstName":"Carlos","lastName":"Sparshatt","email":"csparshattaz@topsy.com","gender":"Male","department":"Sales","projects":["Project 7"],"salary":4401,"createdAt":{"$date":"2013-05-01T14:06:31.000Z"}},
{"firstName":"Antonio","lastName":"Billing","email":"abillingb0@furl.net","gender":"Male","department":"Purchase","projects":["Project 3","Project 1","Project 2"],"salary":3523,"createdAt":{"$date":"2014-07-12T10:31:55.000Z"}},
{"firstName":"Ericha","lastName":"Meneur","email":"emeneurb1@europa.eu","gender":"Female","department":"HR","projects":["Project 5","Project 6","Project 2"],"salary":5306,"createdAt":{"$date":"2020-12-04T11:05:39.000Z"}},
{"firstName":"Donnie","lastName":"Payley","email":"dpayleyb2@webnode.com","gender":"Female","department":"R&D","projects":["Project 2","Project 5","Project 8","Project 1"],"salary":6354,"createdAt":{"$date":"2015-10-22T04:21:01.000Z"}},
{"firstName":"Nelli","lastName":"Ullett","email":"nullettb3@mozilla.com","gender":"Female","department":"R&D","projects":["Project 2","Project 10","Project 7"],"salary":3366,"createdAt":{"$date":"2019-05-11T13:55:07.000Z"}},
{"firstName":"Murielle","lastName":"Cornes","email":"mcornesb4@wisc.edu","gender":"Female","department":"Marketing","projects":["Project 4"],"salary":5808,"createdAt":{"$date":"2014-12-04T15:33:13.000Z"}},
{"firstName":"Shamus","lastName":"Kelner","email":"skelnerb5@infoseek.co.jp","gender":"Male","department":"Finance","projects":["Project 10","Project 6","Project 8","Project 3"],"salary":4889,"createdAt":{"$date":"2012-10-21T20:19:11.000Z"}},
{"firstName":"Reynard","lastName":"Stanlake","email":"rstanlakeb6@state.tx.us","gender":"Male","department":"Finance","projects":["Project 5","Project 10","Project 7"],"salary":4677,"createdAt":{"$date":"2021-07-10T22:01:30.000Z"}},
{"firstName":"Palmer","lastName":"Littlemore","email":"plittlemoreb7@linkedin.com","gender":"Male","department":"Operations","projects":["Project 3","Project 8","Project 9","Project 6"],"salary":4048,"createdAt":{"$date":"2022-03-19T14:03:21.000Z"}},
{"firstName":"Ignacius","lastName":"Morkham","email":"imorkhamb8@digg.com","gender":"Male","department":"Finance","projects":["Project 3","Project 7"],"salary":4207,"createdAt":{"$date":"2018-09-01T17:36:51.000Z"}},
{"firstName":"Bonni","lastName":"Speeding","email":"bspeedingb9@ezinearticles.com","gender":"Female","department":"IT","projects":["Project 1","Project 8","Project 6","Project 4"],"salary":4280,"createdAt":{"$date":"2021-02-20T21:53:46.000Z"}},
{"firstName":"Lea","lastName":"Gagin","email":"lgaginba@ask.com","gender":"Female","department":"Marketing","projects":["Project 7","Project 10","Project 9","Project 2"],"salary":4162,"createdAt":{"$date":"2018-08-27T20:46:38.000Z"}},
{"firstName":"Davina","lastName":"Copestake","email":"dcopestakebb@smh.com.au","gender":"Female","department":"HR","projects":["Project 5","Project 6","Project 8","Project 1"],"salary":5742,"createdAt":{"$date":"2014-04-20T12:53:25.000Z"}},
{"firstName":"Mufi","lastName":"Francesch","email":"mfranceschbc@github.com","gender":"Polygender","department":"Purchase","projects":["Project 6"],"salary":5077,"createdAt":{"$date":"2010-05-22T17:04:01.000Z"}},
{"firstName":"Jordain","lastName":"Cuff","email":"jcuffbd@prlog.org","gender":"Agender","department":"Operations","projects":["Project 2"],"salary":4248,"createdAt":{"$date":"2018-02-09T03:32:16.000Z"}},
{"firstName":"Alis","lastName":"Redman","email":"aredmanbe@sun.com","gender":"Female","department":"Production","projects":["Project 9","Project 3","Project 4"],"salary":4363,"createdAt":{"$date":"2016-01-15T13:18:20.000Z"}},
{"firstName":"Erroll","lastName":"Mansion","email":"emansionbf@amazonaws.com","gender":"Male","department":"R&D","projects":["Project 10"],"salary":4564,"createdAt":{"$date":"2013-11-29T03:11:35.000Z"}},
{"firstName":"Carmine","lastName":"Augur","email":"caugurbg@gmpg.org","gender":"Male","department":"Marketing","projects":["Project 5","Project 10","Project 6"],"salary":5240,"createdAt":{"$date":"2019-03-17T10:31:52.000Z"}},
{"firstName":"Beau","lastName":"Desporte","email":"bdesportebh@tripadvisor.com","gender":"Male","department":"Finance","projects":["Project 8","Project 9"],"salary":4599,"createdAt":{"$date":"2015-07-11T10:59:30.000Z"}},
{"firstName":"Gregory","lastName":"Joreau","email":"gjoreaubi@ovh.net","gender":"Male","department":"Marketing","projects":["Project 4","Project 8","Project 6"],"salary":3507,"createdAt":{"$date":"2011-10-29T17:46:13.000Z"}},
{"firstName":"Shep","lastName":"Coale","email":"scoalebj@woothemes.com","gender":"Male","department":"R&D","projects":["Project 2"],"salary":4173,"createdAt":{"$date":"2014-08-24T22:37:22.000Z"}},
{"firstName":"Alison","lastName":"Hammersley","email":"ahammersleybk@huffingtonpost.com","gender":"Female","department":"Operations","projects":["Project 10","Project 5"],"salary":6171,"createdAt":{"$date":"2020-06-13T21:36:35.000Z"}},
{"firstName":"Hobard","lastName":"Ollie","email":"holliebl@tripadvisor.com","gender":"Male","department":"Finance","projects":["Project 2","Project 1"],"salary":3351,"createdAt":{"$date":"2019-11-28T01:17:06.000Z"}},
{"firstName":"Granville","lastName":"Gaize","email":"ggaizebm@wordpress.com","gender":"Male","department":"Operations","projects":["Project 10","Project 8"],"salary":5958,"createdAt":{"$date":"2020-08-11T00:52:28.000Z"}},
{"firstName":"Nanni","lastName":"Tinline","email":"ntinlinebn@phpbb.com","gender":"Female","department":"Purchase","projects":["Project 10"],"salary":5479,"createdAt":{"$date":"2015-02-26T16:37:34.000Z"}},
{"firstName":"Sid","lastName":"Haliday","email":"shalidaybo@twitpic.com","gender":"Male","department":"R&D","projects":["Project 3","Project 1"],"salary":6043,"createdAt":{"$date":"2020-09-03T13:21:45.000Z"}},
{"firstName":"Nichole","lastName":"Tookill","email":"ntookillbp@gizmodo.com","gender":"Male","department":"Sales","projects":["Project 10","Project 6","Project 2"],"salary":6749,"createdAt":{"$date":"2020-03-17T19:57:32.000Z"}},
{"firstName":"Julius","lastName":"Lathom","email":"jlathombq@apache.org","gender":"Male","department":"Purchase","projects":["Project 2","Project 9"],"salary":3642,"createdAt":{"$date":"2010-09-04T08:24:48.000Z"}},
{"firstName":"Deana","lastName":"Duprey","email":"ddupreybr@naver.com","gender":"Female","department":"IT","projects":["Project 10"],"salary":6053,"createdAt":{"$date":"2013-02-12T01:57:01.000Z"}},
{"firstName":"Paquito","lastName":"Celli","email":"pcellibs@home.pl","gender":"Male","department":"HR","projects":["Project 4"],"salary":4303,"createdAt":{"$date":"2021-03-26T00:56:16.000Z"}},
{"firstName":"Fletcher","lastName":"Monro","email":"fmonrobt@mit.edu","gender":"Genderqueer","department":"IT","projects":["Project 9"],"salary":6923,"createdAt":{"$date":"2019-02-11T17:45:09.000Z"}},
{"firstName":"Aveline","lastName":"Khotler","email":"akhotlerbu@xing.com","gender":"Female","department":"Customer Service","projects":["Project 8"],"salary":5033,"createdAt":{"$date":"2020-03-01T08:25:05.000Z"}},
{"firstName":"Thorstein","lastName":"Buntin","email":"tbuntinbv@constantcontact.com","gender":"Male","department":"Production","projects":["Project 10","Project 1","Project 8"],"salary":3967,"createdAt":{"$date":"2022-03-07T19:16:34.000Z"}},
{"firstName":"Chane","lastName":"McCleverty","email":"cmcclevertybw@istockphoto.com","gender":"Male","department":"R&D","projects":["Project 5","Project 2"],"salary":4814,"createdAt":{"$date":"2010-07-28T19:34:31.000Z"}},
{"firstName":"Nester","lastName":"Olfert","email":"nolfertbx@patch.com","gender":"Male","department":"Purchase","projects":["Project 1"],"salary":5800,"createdAt":{"$date":"2014-03-12T00:31:41.000Z"}},
{"firstName":"Vaclav","lastName":"Burmingham","email":"vburminghamby@discuz.net","gender":"Agender","department":"Sales","projects":["Project 7","Project 8","Project 10"],"salary":6810,"createdAt":{"$date":"2013-03-25T01:47:31.000Z"}},
{"firstName":"Fayina","lastName":"Haville","email":"fhavillebz@mtv.com","gender":"Female","department":"R&D","projects":["Project 5","Project 8"],"salary":3333,"createdAt":{"$date":"2019-11-21T19:23:28.000Z"}},
{"firstName":"Alvis","lastName":"Giddons","email":"agiddonsc0@uol.com.br","gender":"Male","department":"Sales","projects":["Project 1","Project 3","Project 8","Project 2"],"salary":3904,"createdAt":{"$date":"2015-01-30T03:06:35.000Z"}},
{"firstName":"Florri","lastName":"Utton","email":"futtonc1@webnode.com","gender":"Female","department":"Operations","projects":["Project 10"],"salary":6908,"createdAt":{"$date":"2019-05-19T09:30:59.000Z"}},
{"firstName":"Wilburt","lastName":"Shelsher","email":"wshelsherc2@booking.com","gender":"Male","department":"Sales","projects":["Project 5"],"salary":3732,"createdAt":{"$date":"2012-01-04T02:19:12.000Z"}},
{"firstName":"Skell","lastName":"Giovannilli","email":"sgiovannillic3@yelp.com","gender":"Male","department":"IT","projects":["Project 1"],"salary":4028,"createdAt":{"$date":"2018-09-14T17:32:30.000Z"}},
{"firstName":"Lorenzo","lastName":"Spaducci","email":"lspaduccic4@mtv.com","gender":"Male","department":"IT","projects":["Project 9","Project 4"],"salary":3960,"createdAt":{"$date":"2016-07-16T12:19:56.000Z"}},
{"firstName":"Lexie","lastName":"Enbury","email":"lenburyc5@skyrock.com","gender":"Female","department":"Sales","projects":["Project 4","Project 9","Project 2"],"salary":4846,"createdAt":{"$date":"2019-07-03T15:22:10.000Z"}},
{"firstName":"Genvieve","lastName":"Tarbet","email":"gtarbetc6@wordpress.org","gender":"Female","department":"IT","projects":["Project 8"],"salary":6092,"createdAt":{"$date":"2016-06-11T13:38:29.000Z"}},
{"firstName":"Lolita","lastName":"Housin","email":"lhousinc7@java.com","gender":"Female","department":"HR","projects":["Project 6","Project 2","Project 9"],"salary":4239,"createdAt":{"$date":"2022-02-12T19:35:42.000Z"}},
{"firstName":"Corny","lastName":"Busch","email":"cbuschc8@naver.com","gender":"Female","department":"Operations","projects":["Project 9","Project 10","Project 6","Project 4"],"salary":6085,"createdAt":{"$date":"2012-12-05T05:08:17.000Z"}},
{"firstName":"Vannie","lastName":"Loughead","email":"vlougheadc9@usatoday.com","gender":"Female","department":"Production","projects":["Project 10","Project 7"],"salary":4400,"createdAt":{"$date":"2014-02-09T00:52:09.000Z"}},
{"firstName":"Blinni","lastName":"Kenrack","email":"bkenrackca@paypal.com","gender":"Female","department":"Purchase","projects":["Project 7","Project 5","Project 4","Project 10"],"salary":4470,"createdAt":{"$date":"2020-05-16T03:54:06.000Z"}},
{"firstName":"Lenette","lastName":"Shambrooke","email":"lshambrookecb@domainmarket.com","gender":"Female","department":"Purchase","projects":["Project 8"],"salary":4413,"createdAt":{"$date":"2016-03-23T07:05:37.000Z"}},
{"firstName":"Devlen","lastName":"Fermoy","email":"dfermoycc@indiegogo.com","gender":"Male","department":"Sales","projects":["Project 7"],"salary":4896,"createdAt":{"$date":"2011-05-09T02:49:03.000Z"}},
{"firstName":"Dominique","lastName":"Caesman","email":"dcaesmancd@amazon.co.jp","gender":"Male","department":"IT","projects":["Project 7","Project 6"],"salary":5284,"createdAt":{"$date":"2014-08-19T04:21:13.000Z"}},
{"firstName":"Phaidra","lastName":"Sentance","email":"psentancece@sakura.ne.jp","gender":"Female","department":"IT","projects":["Project 3","Project 8","Project 5"],"salary":3777,"createdAt":{"$date":"2015-04-02T14:12:05.000Z"}},
{"firstName":"Guilbert","lastName":"McNuff","email":"gmcnuffcf@twitpic.com","gender":"Genderfluid","department":"Customer Service","projects":["Project 10","Project 6","Project 7","Project 8"],"salary":6268,"createdAt":{"$date":"2016-11-04T16:21:34.000Z"}},
{"firstName":"Merci","lastName":"Sevior","email":"mseviorcg@comcast.net","gender":"Female","department":"Finance","projects":["Project 7","Project 4"],"salary":5094,"createdAt":{"$date":"2017-05-12T10:59:15.000Z"}},
{"firstName":"Tersina","lastName":"Tonn","email":"ttonnch@domainmarket.com","gender":"Female","department":"GM","projects":["Project 7"],"salary":3480,"createdAt":{"$date":"2021-02-07T08:18:07.000Z"}},
{"firstName":"Vince","lastName":"Roke","email":"vrokeci@ehow.com","gender":"Male","department":"Finance","projects":["Project 6","Project 10","Project 2"],"salary":6425,"createdAt":{"$date":"2019-01-02T17:10:29.000Z"}},
{"firstName":"Irv","lastName":"Baskett","email":"ibaskettcj@techcrunch.com","gender":"Bigender","department":"Customer Service","projects":["Project 5","Project 1","Project 7","Project 9"],"salary":4160,"createdAt":{"$date":"2020-10-14T02:17:06.000Z"}},
{"firstName":"Elinor","lastName":"Osgerby","email":"eosgerbyck@scribd.com","gender":"Genderqueer","department":"HR","projects":["Project 1","Project 8","Project 4","Project 10"],"salary":4807,"createdAt":{"$date":"2017-08-04T23:53:06.000Z"}},
{"firstName":"Bill","lastName":"Thomerson","email":"bthomersoncl@sitemeter.com","gender":"Male","department":"IT","projects":["Project 3"],"salary":6580,"createdAt":{"$date":"2010-01-09T03:23:03.000Z"}},
{"firstName":"Janella","lastName":"Woodfield","email":"jwoodfieldcm@etsy.com","gender":"Female","department":"GM","projects":["Project 7"],"salary":3515,"createdAt":{"$date":"2012-04-01T14:16:05.000Z"}},
{"firstName":"Myron","lastName":"Pidgeley","email":"mpidgeleycn@xrea.com","gender":"Agender","department":"R&D","projects":["Project 3","Project 4","Project 2","Project 8"],"salary":5698,"createdAt":{"$date":"2020-04-09T19:16:04.000Z"}},
{"firstName":"Horten","lastName":"Behrendsen","email":"hbehrendsenco@i2i.jp","gender":"Non-binary","department":"HR","projects":["Project 9","Project 4","Project 8","Project 10"],"salary":5063,"createdAt":{"$date":"2015-08-21T04:28:08.000Z"}},
{"firstName":"Gerta","lastName":"Sands","email":"gsandscp@whitehouse.gov","gender":"Female","department":"Marketing","projects":["Project 3","Project 6","Project 5","Project 9"],"salary":4321,"createdAt":{"$date":"2016-02-17T20:29:54.000Z"}},
{"firstName":"Lionel","lastName":"Tonn","email":"ltonncq@cyberchimps.com","gender":"Male","department":"GM","projects":["Project 2","Project 6","Project 5","Project 9"],"salary":6286,"createdAt":{"$date":"2014-09-06T10:27:10.000Z"}},
{"firstName":"Lilia","lastName":"Harris","email":"lharriscr@marketwatch.com","gender":"Female","department":"IT","projects":["Project 7"],"salary":3441,"createdAt":{"$date":"2020-01-27T07:11:59.000Z"}},
{"firstName":"Magdalena","lastName":"Pieche","email":"mpiechecs@behance.net","gender":"Bigender","department":"Purchase","projects":["Project 9","Project 6"],"salary":4485,"createdAt":{"$date":"2013-06-18T02:20:23.000Z"}},
{"firstName":"Angy","lastName":"Coutthart","email":"acoutthartct@odnoklassniki.ru","gender":"Female","department":"Sales","projects":["Project 2","Project 5","Project 8","Project 3"],"salary":5764,"createdAt":{"$date":"2017-12-11T09:55:14.000Z"}},
{"firstName":"Ellette","lastName":"Rapson","email":"erapsoncu@redcross.org","gender":"Female","department":"Customer Service","projects":["Project 10"],"salary":4314,"createdAt":{"$date":"2019-10-17T19:33:36.000Z"}},
{"firstName":"Odey","lastName":"Stanman","email":"ostanmancv@t-online.de","gender":"Male","department":"Operations","projects":["Project 3","Project 6","Project 5","Project 10"],"salary":4708,"createdAt":{"$date":"2018-11-19T15:35:48.000Z"}},
{"firstName":"Timmie","lastName":"Wetherick","email":"twetherickcw@webeden.co.uk","gender":"Male","department":"Purchase","projects":["Project 10"],"salary":6870,"createdAt":{"$date":"2014-07-19T03:37:32.000Z"}},
{"firstName":"Romain","lastName":"MacCahey","email":"rmaccaheycx@miibeian.gov.cn","gender":"Male","department":"R&D","projects":["Project 3","Project 10","Project 8"],"salary":5971,"createdAt":{"$date":"2018-04-07T03:17:31.000Z"}},
{"firstName":"Jermaine","lastName":"Stain","email":"jstaincy@com.com","gender":"Female","department":"HR","projects":["Project 5","Project 9","Project 10","Project 7"],"salary":3656,"createdAt":{"$date":"2012-12-08T00:57:20.000Z"}},
{"firstName":"Missy","lastName":"Rollingson","email":"mrollingsoncz@reverbnation.com","gender":"Female","department":"Operations","projects":["Project 5","Project 2","Project 10"],"salary":5176,"createdAt":{"$date":"2020-12-15T05:21:23.000Z"}},
{"firstName":"Kristopher","lastName":"Matthaus","email":"kmatthausd0@friendfeed.com","gender":"Male","department":"R&D","projects":["Project 9","Project 5","Project 1","Project 7"],"salary":5732,"createdAt":{"$date":"2011-08-17T03:28:37.000Z"}},
{"firstName":"Lorenza","lastName":"Peoples","email":"lpeoplesd1@eventbrite.com","gender":"Bigender","department":"HR","projects":["Project 2","Project 4"],"salary":4861,"createdAt":{"$date":"2013-02-09T07:37:47.000Z"}},
{"firstName":"Mace","lastName":"Eixenberger","email":"meixenbergerd2@mail.ru","gender":"Male","department":"Finance","projects":["Project 8","Project 7","Project 5"],"salary":4614,"createdAt":{"$date":"2016-05-30T22:00:13.000Z"}},
{"firstName":"Rowena","lastName":"Instock","email":"rinstockd3@hao123.com","gender":"Female","department":"Purchase","projects":["Project 10","Project 8","Project 9"],"salary":6143,"createdAt":{"$date":"2020-01-16T12:05:57.000Z"}},
{"firstName":"Janice","lastName":"Ethridge","email":"jethridged4@weather.com","gender":"Female","department":"Marketing","projects":["Project 10","Project 4","Project 9","Project 3"],"salary":3453,"createdAt":{"$date":"2010-07-07T16:20:32.000Z"}},
{"firstName":"Donaugh","lastName":"Whapham","email":"dwhaphamd5@odnoklassniki.ru","gender":"Male","department":"Finance","projects":["Project 6","Project 7","Project 3"],"salary":6174,"createdAt":{"$date":"2020-09-16T16:42:22.000Z"}},
{"firstName":"Suzie","lastName":"O'Hannigan","email":"sohannigand6@theguardian.com","gender":"Female","department":"Customer Service","projects":["Project 6","Project 10","Project 7","Project 9"],"salary":4328,"createdAt":{"$date":"2013-12-19T23:33:51.000Z"}},
{"firstName":"Helli","lastName":"Preston","email":"hprestond7@cloudflare.com","gender":"Female","department":"Operations","projects":["Project 3","Project 7"],"salary":4515,"createdAt":{"$date":"2010-09-15T04:59:09.000Z"}},
{"firstName":"Dian","lastName":"Rickarsey","email":"drickarseyd8@google.de","gender":"Female","department":"Finance","projects":["Project 5","Project 4","Project 6"],"salary":5983,"createdAt":{"$date":"2020-10-18T21:41:18.000Z"}},
{"firstName":"Gerry","lastName":"Cammoile","email":"gcammoiled9@slashdot.org","gender":"Female","department":"Production","projects":["Project 8"],"salary":5979,"createdAt":{"$date":"2012-01-13T02:41:55.000Z"}},
{"firstName":"Joellen","lastName":"Shutte","email":"jshutteda@cyberchimps.com","gender":"Female","department":"Sales","projects":["Project 1","Project 5","Project 10","Project 9"],"salary":5865,"createdAt":{"$date":"2011-12-22T10:47:00.000Z"}},
{"firstName":"Fanchon","lastName":"Cleobury","email":"fcleoburydb@fc2.com","gender":"Female","department":"Production","projects":["Project 5","Project 8","Project 4","Project 2"],"salary":5382,"createdAt":{"$date":"2018-07-27T14:34:41.000Z"}},
{"firstName":"Alan","lastName":"Dransfield","email":"adransfielddc@illinois.edu","gender":"Male","department":"Marketing","projects":["Project 1","Project 5","Project 6","Project 3"],"salary":5892,"createdAt":{"$date":"2017-03-18T16:33:11.000Z"}},
{"firstName":"Meris","lastName":"Littledyke","email":"mlittledykedd@hibu.com","gender":"Female","department":"HR","projects":["Project 4"],"salary":5446,"createdAt":{"$date":"2012-04-20T15:27:01.000Z"}},
{"firstName":"Wolf","lastName":"Brugman","email":"wbrugmande@sina.com.cn","gender":"Male","department":"Operations","projects":["Project 9","Project 2"],"salary":6935,"createdAt":{"$date":"2016-07-14T16:55:00.000Z"}},
{"firstName":"Erasmus","lastName":"Mcwhinney","email":"emcwhinneydf@yale.edu","gender":"Male","department":"R&D","projects":["Project 1","Project 9","Project 6","Project 3"],"salary":4785,"createdAt":{"$date":"2016-06-08T06:39:34.000Z"}},
{"firstName":"Kipp","lastName":"Grigoriev","email":"kgrigorievdg@buzzfeed.com","gender":"Male","department":"R&D","projects":["Project 5"],"salary":4902,"createdAt":{"$date":"2011-07-17T11:51:40.000Z"}},
{"firstName":"Paten","lastName":"Sivell","email":"psivelldh@e-recht24.de","gender":"Male","department":"Finance","projects":["Project 8","Project 10"],"salary":6336,"createdAt":{"$date":"2017-12-02T12:22:28.000Z"}},
{"firstName":"Terra","lastName":"Pinar","email":"tpinardi@examiner.com","gender":"Female","department":"Production","projects":["Project 9","Project 3","Project 7","Project 8"],"salary":5168,"createdAt":{"$date":"2012-08-24T20:12:20.000Z"}},
{"firstName":"Colene","lastName":"Hinkley","email":"chinkleydj@ihg.com","gender":"Female","department":"IT","projects":["Project 9","Project 2","Project 1","Project 8"],"salary":5459,"createdAt":{"$date":"2020-01-22T09:54:57.000Z"}},
{"firstName":"Sybyl","lastName":"Joderli","email":"sjoderlidk@google.co.jp","gender":"Female","department":"Operations","projects":["Project 1","Project 2"],"salary":3674,"createdAt":{"$date":"2010-10-08T21:48:55.000Z"}},
{"firstName":"Veradis","lastName":"Benduhn","email":"vbenduhndl@ted.com","gender":"Female","department":"HR","projects":["Project 10","Project 5","Project 9","Project 3"],"salary":5114,"createdAt":{"$date":"2013-02-23T11:28:33.000Z"}},
{"firstName":"Bendix","lastName":"Brea","email":"bbreadm@drupal.org","gender":"Male","department":"HR","projects":["Project 1"],"salary":6931,"createdAt":{"$date":"2019-06-11T21:47:47.000Z"}},
{"firstName":"Lucien","lastName":"De Mars","email":"ldemarsdn@com.com","gender":"Bigender","department":"Finance","projects":["Project 2","Project 9","Project 6"],"salary":4289,"createdAt":{"$date":"2018-08-25T01:47:36.000Z"}},
{"firstName":"Thebault","lastName":"Iacomini","email":"tiacominido@edublogs.org","gender":"Male","department":"Purchase","projects":["Project 8"],"salary":3661,"createdAt":{"$date":"2018-07-03T22:35:48.000Z"}},
{"firstName":"Nancee","lastName":"Collumbell","email":"ncollumbelldp@sbwire.com","gender":"Female","department":"R&D","projects":["Project 6","Project 1"],"salary":3597,"createdAt":{"$date":"2019-12-16T11:09:25.000Z"}},
{"firstName":"Gillan","lastName":"Hughesdon","email":"ghughesdondq@usa.gov","gender":"Female","department":"GM","projects":["Project 2","Project 8"],"salary":3868,"createdAt":{"$date":"2012-07-14T13:29:00.000Z"}},
{"firstName":"Trescha","lastName":"Kingscote","email":"tkingscotedr@yahoo.co.jp","gender":"Female","department":"Sales","projects":["Project 9","Project 10","Project 1","Project 6"],"salary":6315,"createdAt":{"$date":"2019-05-23T12:36:49.000Z"}},
{"firstName":"Erik","lastName":"Craker","email":"ecrakerds@360.cn","gender":"Male","department":"Marketing","projects":["Project 8","Project 9","Project 3"],"salary":5887,"createdAt":{"$date":"2011-12-17T19:09:42.000Z"}},
{"firstName":"Freeland","lastName":"Freake","email":"ffreakedt@cdc.gov","gender":"Male","department":"HR","projects":["Project 10","Project 1"],"salary":6910,"createdAt":{"$date":"2010-06-16T00:43:33.000Z"}},
{"firstName":"Hillery","lastName":"Chicchetto","email":"hchicchettodu@admin.ch","gender":"Male","department":"Finance","projects":["Project 7","Project 4"],"salary":6306,"createdAt":{"$date":"2010-02-26T13:35:26.000Z"}},
{"firstName":"Maurizio","lastName":"Wonter","email":"mwonterdv@geocities.jp","gender":"Male","department":"Operations","projects":["Project 4","Project 10","Project 7","Project 9"],"salary":5416,"createdAt":{"$date":"2015-04-29T20:45:59.000Z"}}]
```


### create mongodb database employeesdb (win)
```
mongoimport --db=employeesdb --collection=employees mongodb://localhost:27017 --drop --file=C:\Users\<user>\Downloads\employees.json --jsonArray
```
#### (if we need to authenticate first)
```
mongoimport --authenticationDatabase admin --username=rootuser --password=rootpass --db=employeesdb --collection=employees mongodb://localhost:27017 --drop --file=C:\Users\<user>\Downloads\employees.json --jsonArray
```

### check if data imported
```
mongosh mongodb://localhost:27017/employeesdb
db.employees.find().count()
```
#### (if we need to authenticate first)
```mongosh "mongodb://rootuser:rootpass@localhost:27017/employeesdb?authSource=admin"```
```mongosh "mongodb://localhost:27017/employeesdb" --username rootuser --password rootpass --authenticationDatabase admin```

### create a package named com.example.mdbspringboot.model and add the class Employee.java
```java
package com.example.mdbspringboot.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data // for getters, setters, constructors...
@Document(value = "employees") // so that it can be stored as mongo document
public class Employee {
    @Id // will be autogenerated
    private String id;
    private String firstName;
    private String lastName;
    // @Indexed(unique = true) => will ensure that email is unique
    // to enable index, in application.properties we put:
    // spring.data.mongodb.auto-index-creation=true
    @Indexed(unique = true)
    private String email;
    private String gender;
    private String department;
    private List<String> projects;
    private Double salary;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt = LocalDateTime.now();
    private String mobile;

    public Employee(String firstName, String lastName, String email, String gender, String department,
            List<String> projects, Double salary, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.projects = projects;
        this.salary = salary;
        this.mobile = mobile;
    }
}
```

### create a package called com.example.mdbspringboot.dto
### create a file: dto/EmployeeDTO.java
```java
package com.example.mdbspringboot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeDTO {
    @NotBlank(message = "first name shouldn't be blank")
    private String firstName;
    @NotBlank(message = "last name shouldn't be blank")
    private String lastName;
    @Email(message = "invalid email address")
    @NotBlank(message = "email shouldn't be blank")
    private String email;
    private String gender;
    @NotBlank
    private String department;
    @NotEmpty
    private List<String> projects;
    @Min(2500)
    @Max(8000)
    @NotNull(message = "salary shouldn't be null")
    private Double salary;
    @NotBlank(message = "mobile shouldn't be blank")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$", message = "invalid mobile number entered ")
    private String mobile;
}
```

### create a package called com.example.mdbspringboot.repository

### create an EmployeeRepository public interface which extends the MongoRepository interface
```java
package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    
}

// to get the JSON converter to ignore null values - add this to the application.properties file:
// spring.jackson.default-property-inclusion=non-null
```

### define application properties - resources\application.properties
```
### Local MongoDB config
# spring.data.mongodb.authentication-database=admin
# spring.data.mongodb.username=rootuser
# spring.data.mongodb.password=rootpass
# spring.data.mongodb.database=employeesdb
# spring.data.mongodb.port=27017
# spring.data.mongodb.host=localhost

### Local MongoDB config - one-liners
# spring.data.mongodb.uri=mongodb://rootuser:rootpass@127.0.0.1:27017/employeesdb?authSource=admin&ssl=false
spring.data.mongodb.uri=mongodb://127.0.0.1:27017/employeesdb?ssl=false

spring.data.mongodb.auto-index-creation=true

### App config
#### the app will run on port 8102
server.port=8102 
spring.application.name=BootMongo
server.servlet.context-path=/mdb-spring-boot

### to get the JSON converter to ignore null values
spring.jackson.default-property-inclusion=non-null
```

### create a package called com.example.mdbspringboot.services

### create an interface: EmployeeService
```java
package com.example.mdbspringboot.services;

import java.util.List;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee findOne(String employeeId);

    Employee addEmployee(EmployeeDTO employee);

    List<Employee> addEmployees(List<Employee> employees);

    Employee update(Employee employee);

    Employee patch(Employee employeeUpdateRequest);

    void delete(String id);
}
```

### create the service implementation
```java
package com.example.mdbspringboot.services;

import java.util.List;
import java.util.Optional;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findOne(String employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee addEmployee(EmployeeDTO employeeRequest) {

        Employee employee = new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail(),
                employeeRequest.getGender(),
                employeeRequest.getDepartment(),
                employeeRequest.getProjects(),
                employeeRequest.getSalary(),
                employeeRequest.getMobile());

        return employeeRepository.insert(employee);
    }

    public List<Employee> addEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Employee update(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        }
        return employee;
    }

    public Employee patch(Employee employeeUpdateRequest) {
        if (employeeRepository.existsById(employeeUpdateRequest.getId())) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeUpdateRequest.getId());

            Employee employee = optionalEmployee.get();

            employee.setFirstName(employeeUpdateRequest.getFirstName());
            employee.setLastName(employeeUpdateRequest.getLastName());
            employee.setEmail(employeeUpdateRequest.getEmail());
            employee.setGender(employeeUpdateRequest.getGender());
            employee.setDepartment(employeeUpdateRequest.getDepartment());
            employee.setSalary(employeeUpdateRequest.getSalary());
            employee.setProjects(employeeUpdateRequest.getProjects());

            return employeeRepository.save(employee);
        }

        return employeeUpdateRequest;
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }
}
```


### create a package called com.example.mdbspringboot.controllers

### create a controller
```java
package com.example.mdbspringboot.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        LOG.info("\n>>>>> Getting all employees.\n");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId) {
        LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);
        return employeeService.findOne(employeeId);
    }

    // validation example
    @PostMapping("/create")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO employee) {
        LOG.info("\n>>>>> Saving employee.\n");
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/create-multiple")
    public List<Employee> addEmployees(@RequestBody final List<Employee> employees) {
        LOG.info("\n>>>>> Saving employees.\n");
        return employeeService.addEmployees(employees);
    }

    @PutMapping("/")
    public Employee update(@RequestBody Employee employee) {
        LOG.info("\n>>>>> Updating employee.\n");
        return employeeService.update(employee);
    }

    @PatchMapping("/")
    public Employee patch(@RequestBody Employee employee) {
        LOG.info("\n>>>>> Patching employee.\n");
        return employeeService.patch(employee);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    public String delete(@RequestParam String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public String deleteNo3(@PathParam("id") String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

}
```

### create a file requests.http (at the root level)
#### write a code to test GET "/" endpoint
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/


###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/625c6a85c131622dc1789587


### valid data
POST http://localhost:8102/mdb-spring-boot/api/v1/employees/create
Content-Type: application/json

{
  "firstName": "Tom",
  "lastName": "Gibson",
  "email": "tom@gibson.com",
  "gender": "Male",
  "department": "R&D",
  "projects": [
    "Project 2",
    "Project 10"
  ],
  "salary": 5600,
  "mobile": "123 345 6789"
}

### invalid data
POST http://localhost:8102/mdb-spring-boot/api/v1/employees/create
Content-Type: application/json

{
  "firstName": "",
  "lastName": null,
  "email": "tomgibson.com",
  "gender": "Male",
  "projects": [],
  "salary": 15600,
  "mobile": "123*345/6789"
}


###
POST http://localhost:8102/mdb-spring-boot/api/v1/employees/create-multiple
Content-Type: application/json

[
  {
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane@doe.com",
    "gender": "FEMALE",
    "department": "IT",
    "projects": [
      "Project 2",
      "Project 4",
      "Project 7"
    ],
    "salary": 3500
  },
  {
    "firstName": "Alex",
    "lastName": "Brown",
    "email": "alex@brown.com",
    "gender": "Male",
    "department": "HR",
    "projects": [
      "Project 1",
      "Project 9"
    ],
    "salary": 4200
  }
]


### not working as expected
###
PATCH http://localhost:8102/mdb-spring-boot/api/v1/employees/
Content-Type: application/json

{
  "id": "625c6a85c131622dc1789587",
  "department": "IT",
  "projects": [
    "Project 2",
    "Project 10"
  ]
}


###
PUT http://localhost:8102/mdb-spring-boot/api/v1/employees/ HTTP/1.1
content-type: application/json

{
  "id": "625c6a85c131622dc1789587",
  "firstName": "Rory",
  "lastName": "McGinty",
  "email": "rmcginty1@baidu.com",
  "gender": "Female",
  "department": "R&D",
  "projects": [
    "Project 2",
    "Project 4",
    "Project 5",
    "Project 10"
  ],
  "salary": 4900.0,
  "createdAt": "2016-11-09T22:10:20"
}


###
DELETE http://localhost:8102/mdb-spring-boot/api/v1/employees/delete/?id=625c6a85c131622dc1789592 HTTP/1.1


###
DELETE http://localhost:8102/mdb-spring-boot/api/v1/employees/delete-v2/625c6a85c131622dc1789593 HTTP/1.1


###
DELETE http://localhost:8102/mdb-spring-boot/api/v1/employees/delete-v3?id=625c6a85c131622dc1789594 HTTP/1.1

```


### validation exception handling - to return a proper error message
### create a package called com.example.mdbspringboot.advice
### create a file: advice\ApplicationExceptionHandler.java
```java
package com.example.mdbspringboot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler - used to handle the specific exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        // sending the custom responses to the client
        return errorMap;
    }
}
```

### throw an exception if an employee not found 
### create a package called com.example.mdbspringboot.exception
### create a file: exception\EmployeeNotFoundException.java
```java
package com.example.mdbspringboot.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
```
### EmployeeService - change findOne method signature
```java
Employee findOne(String employeeId) throws EmployeeNotFoundException;
```
#### EmployeeServiceImpl - refactor findOne method
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;

// ...

public Employee findOne(String employeeId) throws EmployeeNotFoundException {
    return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException("employee not found with id : " + employeeId));
}
```
### EmployeeController - getEmployee
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;

// ...

@GetMapping("/{employeeId}")
public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) throws EmployeeNotFoundException {
    LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);

    return ResponseEntity.ok(employeeService.findOne(employeeId));
}
```
### ApplicationExceptionHandler.java - add EmployeeNotFoundException handler
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;

// ...

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ExceptionHandler(EmployeeNotFoundException.class)
public Map<String, String> handleBusinessException(EmployeeNotFoundException ex) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("errorMessage", ex.getMessage());
    return errorMap;
}
```


### refactoring EmployeeServiceImpl DI
#### replace this
```java
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // the rest skipped for brevity

}
```
#### with this
```java
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    // the rest skipped for brevity

}
```

### refactoring EmployeeController DI
#### replace this
```java
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // the rest skipped for brevity

}
```
#### with this
```java
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    // the rest skipped for brevity

}
```


### refactor EmployeeController to use ResponseEntity


### refactoring EmployeeServiceImpl DI - another way
#### replace this
```java
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    // the rest skipped for brevity

}
```
#### with this
```java
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    // the rest skipped for brevity

}
```

### refactoring EmployeeController DI - another way
#### replace this
```java
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    // the rest skipped for brevity

}
```
#### with this
```java
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    // the rest skipped for brevity

}
```

### EmployeeService - get paged data
```java
import java.util.Map;

// ...

Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy);
```

### EmployeeServiceImpl - get paged data
```java
import java.util.Map;
import java.util.HashMap;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// the rest skipped for brevity

public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
    Map<String, Object> response = new HashMap<String, Object>();

    Sort sort = Sort.by(sortBy);
    Pageable page = PageRequest.of(pageNo, pageSize, sort);
    Page<Employee> employeePage = employeeRepository.findAll(page);

    response.put("data", employeePage.getContent());
    response.put("Total no of pages", employeePage.getTotalPages());
    response.put("Total no of elements", employeePage.getTotalElements());
    response.put("Current page no", employeePage.getNumber());

    return response;
}
```


### EmployeeController - get paged data
```java
import java.util.Map;

// the rest skipped for brevity

@GetMapping("/page")
public Map<String, Object> getAllPaged(@RequestParam(name = "pageno",defaultValue = "0") int pageNo,
        @RequestParam(name = "pagesize", defaultValue = "10") int pageSize,
        @RequestParam(name = "fields", defaultValue = "firstName,lastName,salary") String[] fields,
        @RequestParam(name = "sortby", defaultValue = "salary") String sortBy) {
    return employeeService.getAllPaged(pageNo, pageSize, fields, sortBy);
}
```


### requests.http
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/page?pagesize=20&fields=firstName,lastName,salary&sortby=firstName&pageno=0 HTTP/1.1
```

### EmployeeRepository - new methods
```java
import java.util.List;
import org.springframework.data.mongodb.repository.Query;

// the rest skipped for brevity

@Query(value = "{ 'projects': { $elemMatch : { $in: ?0 } } }", fields = "{'firstName': 1, 'lastName': 1, 'projects': 1}")
List<Employee> getBy(String[] projects);

// query by method names
List<Employee> findByFirstNameStartingWith(String firstName);

List<Employee> findByDepartment(String department);

// using @Query
@Query(value = "{'salary':{$gte:?0}}", fields = "{'salary':1, 'firstName':1, 'lastName':1, '_id':0}")
List<Employee> getAllBySalaryGTE(int salary);

```

### EmployeeService
```java
Map<String, Object> getByProjects(String[] projects);

// query by Example Executor
List<Employee> getAllByExample(Employee employee);

// query by method names
List<Employee> getAllByFirstNameStartingWith(String firstName);

// query by method names
List<Employee> getAllByDepartment(String department);

// using @Query
List<Employee> getAllBySalaryGTE(int salary);
```

### EmployeeServiceImpl
```java
import org.springframework.data.domain.Example;

// ...

public Map<String, Object> getByProjects(String[] projects) {
    Map<String, Object> response = new HashMap<String, Object>();

    List<Employee> listOfEmployees = employeeRepository.getBy(projects);

    response.put("data", listOfEmployees);
    response.put("Total no of employees", listOfEmployees.size());

    return response;
}

// query by Example Executor
public List<Employee> getAllByExample(Employee employee) {
    Example<Employee> example = Example.of(employee);

    // ExampleMatcher employeeMatcher =
    // ExampleMatcher.matchingAll().withIgnoreCase("lastName", "firstName")
    // .withIgnorePaths("id").withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
    // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    List<Employee> employees = employeeRepository.findAll(example);

    return employees;
}

// query by method names
public List<Employee> getAllByFirstNameStartingWith(String firstName) {
    return employeeRepository.findByFirstNameStartingWith(firstName);
}

// query by method names
public List<Employee> getAllByDepartment(String department) {
    return employeeRepository.findByDepartment(department);
}

// using @Query
public List<Employee> getAllBySalaryGTE(int salary) {
    return employeeRepository.getAllBySalaryGTE(salary);
}
```

### EmployeeController
```java
@GetMapping("/projects")
public Map<String, Object> getByProjects(@RequestParam(name = "projects", required = true) String[] projects) {
    return employeeService.getByProjects(projects);
}

// query by Example Executor
@GetMapping("/example")
public List<Employee> getAllByExample(@RequestBody Employee employee) {
    return employeeService.getAllByExample(employee);
}

// query by method names
@GetMapping("/first-name")
public List<Employee> getAllByFirstName(@RequestParam(name = "firstName") String firstName) {
    return employeeService.getAllByFirstNameStartingWith(firstName);
}

// query by method names
@GetMapping("/department")
public List<Employee> getAllByDepartment(@RequestParam(name = "department") String department) {
    return employeeService.getAllByDepartment(department);
}

// using @Query
@GetMapping("/salary")
public List<Employee> getAllBySalaryGTE(@RequestParam(name = "salary") int salary) {
    return employeeService.getAllBySalaryGTE(salary);
}
```

### requests.http
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/projects?projects=Project 9,Project 10 HTTP/1.1

## not working
### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/example HTTP/1.1
content-type: application/json

{

}

### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/first-name?firstName=Al HTTP/1.1


### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/department?department=IT HTTP/1.1

### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/salary?salary=4000 HTTP/1.1
```

### creating custom repository - interface CustomEmployeeRepository
```java
package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;

public interface CustomEmployeeRepository {
    void testMethod(String employeeId, Employee employee);
}
```

### custom repository implementation - CustomEmployeeRepositoryImpl
```java
package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void testMethod(String employeeId, Employee employee) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(employee.getEmail()));
        List<Employee> employees = mongoTemplate.find(query, Employee.class);

        if (employees.isEmpty()) {
			System.out.println("Employee does not exist");
		} else {
			System.out.println(employee + " already exists");
		}
    }
}
```

### make EmployeeRepository extend CustomEmployeeRepository
```java
// public interface EmployeeRepository extends MongoRepository<Employee, String>

public interface EmployeeRepository extends MongoRepository<Employee, String>, CustomEmployeeRepository
```

### create another interface: CustomEmployeeRepositoryTwo
```java
package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

public interface CustomEmployeeRepositoryTwo {
    List<Employee> findAll();

    void saveAll(final List<Employee> employees);

    Employee findById(final String employeeId);
}
```

### create the interface implementation: CustomEmployeeRepositoryTwoImpl
```java
package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

@Repository
public class CustomEmployeeRepositoryTwoImpl implements CustomEmployeeRepositoryTwo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Employee> findAll() {
        return mongoTemplate.findAll(Employee.class);
    }

    public void saveAll(final List<Employee> employees) {
        mongoTemplate.insertAll(employees);
    }

    public Employee findById(final String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employeeId));
        
		return mongoTemplate.findOne(query, Employee.class);
    }
}
```

### EmployeeService
```java
// here we are using our custom repository
void testMethod(String employeeId, Employee employee);

// here we are using MongoTemplate based repository
Employee findById(String id) throws EmployeeNotFoundException;
```

### EmployeeServiceImpl
#### importing CustomEmployeeRepositoryTwo
```java
import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;
```
#### handle CustomEmployeeRepositoryTwo DI
```java
@Autowired
CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;
```
#### add methods
```java
// here we are using our custom repository
public void testMethod(String employeeId, Employee employee) {
    employeeRepository.testMethod(employeeId, employee);
}

// here we are using MongoTemplate based repository
public Employee findById(String id) throws EmployeeNotFoundException {
    Employee employee = customEmployeeRepositoryTwo.findById(id);

    if (Objects.nonNull(employee)) {
        return employee;
    } else {
        throw new EmployeeNotFoundException("employee not found with id : " + id);
    }
}
```

### EmployeeController
#### add new method
```java
// here we are using our custom repository
@PatchMapping("/{employeeId}")
public void testMethod(@PathVariable final String employeeId, @RequestBody final Employee employee) {
    employeeService.testMethod(employeeId, employee);
}
```
#### modify getEmployee method
```java
@GetMapping("/{employeeId}")
public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) throws EmployeeNotFoundException {
    LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);

    // return ResponseEntity.ok(employeeService.findOne(employeeId)); // delete this

    // here we are using MongoTemplate based repository
    return ResponseEntity.ok(employeeService.findById(employeeId));
}
```

### requests.http
```
###
PATCH http://localhost:8102/mdb-spring-boot/api/v1/employees/625c6a85c131622dc1789587
Content-Type: application/json

{
  "id": "625c6a85c131622dc1789587",
  "department": "IT",
  "projects": [
    "Project 2",
    "Project 10"
  ]
}
```

### EmployeeRepository
```java
import java.util.Optional;

// the rest skipped for brevity

// queries from method names
Optional<Employee> findEmployeeByEmail(String email);
```

### MdbSpringBootApplication
```java
import java.util.List;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

// the rest skipped for brevity

@Bean
CommandLineRunner runner(EmployeeRepository repository, MongoTemplate mongoTemplate) {
	return args -> {
		String email = "john@smith.com";
		Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
				List.of("Project 3", "Project 5", "Project 6"), 5500.0, "123 345 6789");
		repository.findEmployeeByEmail(email).ifPresentOrElse(s -> {
			System.out.println("\n>>>>>> " + s + " already exists");
		}, () -> {
			System.out.println("\n>>>>>> Inserting employee " + employee);
			repository.insert(employee);
		});
	};
}
```

### MdbSpringBootApplication - another way
```java
package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MdbSpringBootApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String email = "john@smith.com";
		Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
				List.of("Project 3", "Project 5", "Project 6"), 5500.0, "123 345 6789");
		employeeRepository.findEmployeeByEmail(email).ifPresentOrElse(s -> {
			System.out.println("\n>>>>>> " + s + " already exists");
		}, () -> {
			System.out.println("\n>>>>>> Inserting employee " + employee);
			employeeRepository.insert(employee);
		});
	}
}
```

### you can build the JAR file with
```
mvn clean package
```

### run the JAR file
```
java -jar target/mdb-spring-boot-0.0.1-SNAPSHOT.jar
```


### using docker
- nvm use 14
- install Docker Desktop: https://www.docker.com/get-started/

### check the version of Docker
```
docker --version
```
### check the version of Docker Compose
```
docker-compose version
```
### create a file: docker-compose.yml in the project directory
```
touch docker-compose.yml
```
### docker-compose.yml
```yml
version: "3.8" # the version of the docker-compose file syntax
services:
# container 1 - mongodb
  mongodb:
    image: mongo # would get the latest version for mongodb
    container_name: mongodb
    ports: 
        - 27017:27017 # host:container
    volumes: 
        - data:/data
    environment: 
        - MONGO_INITDB_ROOT_USERNAME=rootuser
        - MONGO_INITDB_ROOT_PASSWORD=rootpass
# container 2 - mongo-express GUI
  mongo-express:
    image: mongo-express
    container_name: mongo-express
    depends_on:
      - mongodb
    restart: always
    # port 8081 is exposed to allow access to the web interface
    ports: 
        - 8081:8081 # host:container
    environment: 
        - ME_CONFIG_MONGODB_SERVER=mongodb
        - ME_CONFIG_MONGODB_ADMINUSERNAME=rootuser
        - ME_CONFIG_MONGODB_ADMINPASSWORD=rootpass
        - ME_CONFIG_MONGODB_ENABLE_ADMIN=true
        - ME_CONFIG_BASICAUTH_USERNAME=admin #
        - ME_CONFIG_BASICAUTH_PASSWORD=admin123 #
# for storing the data
volumes: 
    data: {}
# for communication between containers
networks:
    default:
        # overriding default name
        name: mongodb_network
```
### create and run docker containers
```
docker-compose -f docker-compose.yml up
```
### check if the containers are indeed up and running
```
docker ps
```
### stop the containers: ctrl C
### remove everything
```
docker-compose -f docker-compose.yml down
```
### create and run docker containers - detached mode
```
docker-compose -f docker-compose.yml up -d
```
### now visit this addres for Mongo Express GUI:
http://localhost:8081/

### start - stop the containers without removing them
```
docker-compose -f docker-compose.yml stop
docker-compose -f docker-compose.yml start
```
### using terminal - mongo shell
#### list running containers and check the CONTAINER ID for IMAGE: mongo, NAMES: mongodb
```
docker ps
```
#### get bash interactive shell for the IMAGE: mongo, NAMES: mongodb container
```
docker exec -it {mongo CONTAINER ID} bash
```
#### connect to a MongoDB server
```
mongo mongodb://localhost:27017 -u rootuser -p rootpass
```
#### list all databases
```
show dbs;
```
### importing data into db
#### open a new terminal
#### list running containers
```
docker ps
```
#### import the data
```
docker exec -i {mongo CONTAINER ID} sh -c 'mongoimport --authenticationDatabase admin --username=rootuser --password=rootpass -c employees -d employeesdb --drop --jsonArray' < /path/mdb-spring-boot/employees.json
```
### finally, modify application.properties
```
### Local MongoDB config - one-liners
spring.data.mongodb.uri=mongodb://rootuser:rootpass@127.0.0.1:27017/employeesdb?authSource=admin&ssl=false
# spring.data.mongodb.uri=mongodb://127.0.0.1:27017/employeesdb?ssl=false


```
### using docker END

## velocity
### pom.xml: add this dependency
```xml
<!-- Velocity dependencies -->
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>velocity-spring-boot-starter</artifactId>
    <version>1.0.4.RELEASE</version>
</dependency>
```

### application.properties: add this velocity configuration
```
## ======= Velocity configuration =======
spring.velocity.resource-loader-path=classpath:/velocity/views
spring.velocity.suffix=.vm
spring.velocity.layout-url=/layouts/layout-1.vm
spring.velocity.screen-content-key=body_content
## ======= Velocity configuration END =======
```

### inside src\main\resources folder create folders:
- velocity\views\fragments
- velocity\views\layouts
- velocity\views\home


### create a file: \velocity\views\fragments\footer.vm
```
<style>
    .footer-container {
        background: #63B175;
        padding: 5px;
    }
</style>

<div class="footer-container">
    <h1>FOOTER</h1>
</div>
```


### create a file: \velocity\views\fragments\header.vm
```
<style>
    .header-container {
        background: #63B175;
        padding: 5px;
    }

    .menu {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: SteelBlue;
    }

    .menu li {
        float: left;
    }

    .menu li a {
        display: block;
        color: white;
        text-align: center;
        padding: 16px;
        text-decoration: none;
    }

    .menu li a:hover {
        background-color: LightSteelBlue;
        color: black;
    }
</style>

<div class="header-container">
    <h1>HEADER</h1>
    <ul class="menu">
        <li>
            <a href="/mdb-spring-boot/home/">Home</a>
        </li>
        <li>
            <a href="/mdb-spring-boot/home/view-2">View 2</a>
        </li>
        <li>
            <a href="/mdb-spring-boot/home/view-3">View 3</a>
        </li>
    </ul>
</div>
```

### create a file: \velocity\views\home\view-1.vm
```
<style>
    .view-1-container {
        background-color: crimson;
        color: white;
        padding: 5px;
        margin: 5px auto;
    }
</style>

<div class="view-1-container">
    <h1>VIEW 1</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### create a file: \velocity\views\home\view-2.vm
```
<style>
    .view-2-container {
        background-color: DarkSlateGray;
        color: white;
        padding: 5px;
        margin: 5px auto;
    }
</style>

<div class="view-2-container">
    <h1>VIEW 2</h1>
    <div>$request.getRequestURI()</div>
</div> 
```


### create a file: \velocity\views\home\view-3.vm
```
<style>
    .view-3-container {
        background-color: SlateGray;
        color: white;
        padding: 5px;
        margin: 5px auto;
    }
</style>

<div class="view-3-container">
    <h1>VIEW 3</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### create a file: \velocity\views\layouts\layout-1.vm
```
<html>
    <head>
        <style>
            body {
                background-color: LightSkyBlue;
                font-family: "Arial";
            }
        </style>
    </head>
    <body>
        <div>
            <div>
                #parse("/fragments/header.vm")
            </div>

            <!-- see application.properties -->
            $body_content

            <div>
                #parse("/fragments/footer.vm")
            </div>
        </div>
    </body>
</html>
```

### create a controller: HomeController.java
```java
package com.example.mdbspringboot.controllers;

import com.alibaba.boot.velocity.annotation.VelocityLayout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/home")
@VelocityLayout("/layouts/layout-1.vm") // default layout page URL for this controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    // http://localhost:8102/mdb-spring-boot/home/view-1
    @RequestMapping(method = RequestMethod.GET, value = { "/", "/view-1" })
    public String method_1() {
        return "home/view-1";
    }

	@RequestMapping(method = RequestMethod.GET, value = "/view-2")
	public String method_2() {
		return "home/view-2";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view-3")
	public String method_3() {
		return "home/view-3";
	}
}
```

### modify method_1() in HomeController.java so that we can use a Model object for passing attributes to a view
```java
import org.springframework.ui.Model;

// ...

// http://localhost:8102/mdb-spring-boot/home/view-1
@RequestMapping(method = RequestMethod.GET, value = { "/", "/view-1" })
public String method_1(Model model) {
    model.addAttribute("pageTitle", "VIEW 1");
    return "home/view-1";
}
```

### modify view-1.vm file so that we use a model attribute sent by the HomeController
#### replace this
```
<h1>VIEW 1</h1>
```
### with this
```
<h1>$!pageTitle</h1>
```

### let's display an info about layout that we use:
#### application.properties: add this to velocity configuration
```
spring.velocity.layout-key=layout_key
```
#### layout-1.vm: 
```
<body>
    <div>
        <div>$!layout_key</div>
```

### create CustomErrorController
```java
package com.example.mdbspringboot.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    String error(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String message = exception == null ? "" : exception.getMessage();

        return "<div>" + HttpStatus.valueOf(status) + "</div>" + "<div>" + message + "</div>";
    }
}
```


### create a file: resources/static/styles/styles.css
#### put in it all styles from:
- header.vm
- footer.vm
- view-1.vm
- view-2.vm
- view-3.vm
```css
.header-container {
    background: #63B175;
    padding: 5px;
}

.menu {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: SteelBlue;
}

.menu li {
    float: left;
}

.menu li a {
    display: block;
    color: white;
    text-align: center;
    padding: 16px;
    text-decoration: none;
}

.menu li a:hover {
    background-color: LightSteelBlue;
    color: black;
}

.footer-container {
    background: #63B175;
    padding: 5px;
}

.view-1-container {
    background-color: crimson;
    color: white;
    padding: 5px;
    margin: 5px auto;
}

.view-2-container {
    background-color: DarkSlateGray;
    color: white;
    padding: 5px;
    margin: 5px auto;
}

.view-3-container {
    background-color: SlateGray;
    color: white;
    padding: 5px;
    margin: 5px auto;
}
```

### layout-1.vm - import styles.css file
```
<head>
    <link href="/mdb-spring-boot/styles/styles.css" rel="stylesheet" type="text/css">
```

### create a file: resources/velocity/views/macros/macros.vm
```
#macro(renderAlertMessage $message)

<div id="alert-div" class="alert alert-message-container closed">
    <div id="alert-msg" class="alert-item"></div>
    <div class="alert-item alert-item-close">
        &times;
    </div>
</div>

#end
```

### add new styles to: resources/static/styles/styles.css
```css
.alert-message-container {
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 48px;
    border-radius: 4px;
    background-color: DodgerBlue;
    color: white;
    padding: 0 10px;
}

.alert-message-container .alert-item {
    font-size: 15px;
}

.alert-message-container .alert-item.alert-item-close {
    cursor: pointer;
}

.alert-message-container.closed {
    display: none;
}
```

### create a file: resources/static/scripts/scripts.js
```js
var messageModule = (function () {
    var alertDiv = document.getElementById("alert-div");
    var messageElement = document.getElementById('alert-msg');
    var closeBtn = document.querySelector(".alert-item-close");

    function addEventListeners() {
        closeBtn.addEventListener("click", function () {
            resetAlertMessage();
        })
    }

    function resetAlertMessage() {
        alertDiv
            .classList
            .add("closed");

        messageElement.innerHTML = "";
    }

    function setMessage(message) {
        messageElement.innerHTML = message;
    }

    function setShowHideClass() {
        alertDiv.classList.remove("closed");
    }

    function init(message) {
        if (alertDiv && message) {
            setMessage(message);
            addEventListeners();
            setShowHideClass();
        }
    }

    return {
        init: init
    };
}());
```

### layout-1.vm - import scripts.js file
```
    <script src="/mdb-spring-boot/scripts/scripts.js"></script>
</body>
```

### macros.vm: add a script section to the renderAlertMessage macro
```
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function (event) {
        // note: "$!message"
        messageModule.init("$!message");
    });
</script>
#end
```

### view-1.vm
```
<div class="view-1-container">
    #renderAlertMessage()
    <h1>$!pageTitle</h1>
    <div>$request.getRequestURI()</div>
    <button onclick="javascript:messageModule.init('Lorem Ipsum Dolor Sit Amet!')">Show message</button>
</div>
```

### view-2.vm
```
<div class="view-2-container">
    #renderAlertMessage("View 2 - a special message")
    <h1>VIEW 2</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### view-3.vm
```
<div class="view-3-container">
    <h1>VIEW 3</h1>
    <div>$request.getRequestURI()</div>

    ## Quiet reference notation
    #set ($test = $null)
    <div>$test</div>
    <div>$!test</div>
    <script type="text/javascript">
        console.log("$test");
        console.log("$!test");
    </script>

    ## Formal notation - with curly braces
    #set ($fruit = "apple")
    <div>$fruit-juice</div>
    <div>${fruit}-juice</div>

    ## Booelan
    #set ($ok = false)
    #if($ok)
        <div>"OK"</div>
    #elseif(!$ok)
        <div>"NOK"</div>
    #end
</div>
```

### HomeController.java
```java
import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

// ...

@Autowired
EmployeeService employeeService;

// ...

@RequestMapping(method = RequestMethod.GET, value = "/view-2")
public String method_2(Model model) {
	List<Employee> employees = employeeService.getAllByFirstNameStartingWith("Al");
	model.addAttribute("employees", employees);
	return "home/view-2";
}
```

### view-2.vm: add this to the view-2-container
```
<table>
    <tr>
        <th>No</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gender</th>
        <th>Email</th>
        <th>Department</th>
        <th>Salary</th>
    </tr>
    #foreach($x in $employees)
        <tr>
            <td>$foreach.count</td>
            <td>$x.firstName</td>
            <td>$x.lastName</td>
            <td>$x.gender</td>
            <td>$x.email</td>
            <td>$x.department</td>
            <td>$x.salary</td>
        </tr>
    #end
</table>
```

### HomeController.java: new employee
```java
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.mdbspringboot.dto.EmployeeDTO;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

// ...

// validation example
@RequestMapping(value = "/save_employee", method = RequestMethod.POST)
public String addEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employee, BindingResult bindingResult) {
	if (!bindingResult.hasErrors()) {
		employeeService.addEmployee(employee);
	}
	return "redirect:/home/view-2";
}
```


### macros.vm: add new macro
```
#macro(renderValidationErrors $erros)
    #if($!erros && $erros.size() != 0)
        #foreach($error in $erros)
            <div style="color:red">
                <div style="float:left; margin-right:10px">$error.getField()</div>
                <div>$error.getDefaultMessage()</div>
            </div>
        #end
    #end
#end
```


### view-2.vm: add the form to the view-2-container
```
<form class="employee-form" method="post" action="/mdb-spring-boot/home/save_employee">

    <div>
        <label for="firstname">First name:</label>
        <input type="text" id="firstname" name="firstName">
    </div>

    <div>
        <label for="lastname">Last name:</label>
        <input type="text" id="lastname" name="lastName">
    </div>

    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">
    </div>

    <div>
        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender">
    </div>

    <div>
        <label for="department">Department:</label>
        <input type="text" id="department" name="department">
    </div>

    <div>
        <label for="projects">Projects:</label>
        <input type="text" id="projects" name="projects">
    </div>

    <div>
        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary">
    </div>

    <div>
        <label for="mobile">Mobile:</label>
        <input type="text" id="mobile" name="mobile">
    </div>

    <button type="submit">Add</button>
</form>
#renderValidationErrors($validationErros)

```

### add new styles to: resources/static/styles/styles.css
```css
.employees-table,
.employees-table td,
.employees-table th {
    border: 1px solid;
}

.employee-form {
    width: 300px;
}

.employee-form div {
    display: flex;
    justify-content: space-between;
}
```

### application.properties
```
# we will read this from within method_3 of HomeController
view-3-message=Hello there
```

### ### HomeController.java: modify method_3 so that it returns ModelAndView
```java
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import java.util.Locale;

// ...

// we insert the view-3-message property from the application.properties file 
// into the message attribute
@Value("${view-3-message}")
private String message;

// ...

@RequestMapping(method = RequestMethod.GET, value = "/view-3")
public ModelAndView method_3(Locale locale) {
	var mav = new ModelAndView("home/view-3");
	mav.addObject("message", message);

    logger.info("\n>>>>>> Welcome home! The client locale is {}.", locale);

	return mav;
}
```

### view-3.vm: add this to the view-3-container
```
<div>$message</div>
```

### ### HomeController.java: add this
```java
// see: footer.vm
@ModelAttribute("WikipediaLinkLabel")
public String wikipediaLinkLabel() {
	return "Wikipedia";
}

// see: footer.vm
@ModelAttribute("WikipediaLink")
public String wikipediaLink() {
	return "https://en.wikipedia.org/wiki/Java_(programming_language)";
}
```

### footer.vm
```
<div class="footer-container">
    <h1>FOOTER</h1>
    <a href="$!WikipediaLink" target="_blank">$!WikipediaLinkLabel</a>
</div>
```

### HomeController.java: delete wikipediaLinkLabel and wikipediaLink
```java
// see: footer.vm
@ModelAttribute
public void addAttributes(Model model) {
    model.addAttribute("WikipediaLinkLabel", "Wikipedia");
    model.addAttribute("WikipediaLink", "https://en.wikipedia.org/wiki/Java_(programming_language)");
}
```

### create a new file: resources/velocity/views/layouts/layout-2.vm
```
<html>
    <head>
        <link href="/mdb-spring-boot/styles/styles.css" rel="stylesheet" type="text/css">
        <style>
            body {
                background-color: lightgreen;
                font-family: arial;
            }
        </style>
    </head>
    <body>
        <div>
            <div>$!layout_key</div>

            #parse("/fragments/header.vm")

            <!-- see application.properties -->
            $body_content
        </div>
    </body>
</html>
```


### HomeController.java: add this decorator above method_3
```java
@VelocityLayout("/layouts/layout-2.vm") // overrides default layout
```

### testing
### pom.xml: add this dependency
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.7.0</version>
    <scope>test</scope>
</dependency>
```

### src/test/java/com/example/mdbspringboot/MdbSpringBootApplicationTests.java
```java
package com.example.mdbspringboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MdbSpringBootApplicationTests {

	// object underTest
	Calculator calculator = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		// given
		int numberOne = 20;
		int numberTwo = 30;

		// when
		int result = calculator.add(numberOne, numberTwo);

		// then
		int expected = 50;
		assertEquals(expected, result);
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}
}
```

### src/test/java/com/example/mdbspringboot/controllers/HomeControllerTest.java
```java
package com.example.mdbspringboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMethod_1() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(get("/home/view-1").contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(containsString("<div class=\"view-1-container\">")))
                .andReturn();
    }
}
```
























