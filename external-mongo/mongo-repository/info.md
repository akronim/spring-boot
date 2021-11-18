
### docker container
https://www.bmc.com/blogs/mongodb-docker-container/

```
sudo apt update && sudo apt upgrade -y
```
#### Install prerequisite packages
```
sudo apt install apt-transport-https ca-certificates curl software-properties-common
```
#### Add the GPG key from the official Docker repository
```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```
#### Add the official docker repository to APT sources
```
sudo add-apt-repository \
"deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
```

```
sudo apt update
```
#### Verify the docker repository
```
apt-cache policy docker-ce
```
#### Install the Docker community edition
```
sudo apt install docker-ce
```
#### Check the status
```
sudo systemctl status docker
```
#### Docker Compose
```
sudo curl -L "https://github.com/docker/compose/releases/download/v2.1.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```
#### Apply executable permissions for the downloaded binary
```
sudo chmod +x /usr/local/bin/docker-compose
```
#### Verify the Docker Compose installation
```
docker-compose --version
```



### docker version
```
docker --version
```


```
$ touch docker-compose.yml
```

```
$ docker-compose -f docker-compose.yml up
```

```
$ docker-compose down
```

```
$ docker-compose stop
```

```
$ docker-compose start
```

```
$ docker-compose up -d
```

# Mongo Express:
http://localhost:8081/


### importing:
```
$ mongoimport --host=localhost:27017 --username=rootuser --password=rootpass --authenticationDatabase admin --db=bookdb --collection=books --drop --file=books.json --jsonArray
```
```
mongoimport --uri "mongodb://rootuser:rootpass@localhost:27017/bookdb?authSource=admin" --collection books --drop --file books.json --jsonArray
```

### importing - 2 (from mongo shell): 
```
mongo --host=localhost:27017 --username=rootuser --password=rootpass --authenticationDatabase admin
```
```
var file = cat('./books.json');   # file name
use bookdb                        # db name
var f = JSON.parse(file);         # convert string to JSON
db.books.insert(f)          	  # collection name
```

### connect - 1
```
mongo --host=localhost:27017 --username=rootuser --password=rootpass --authenticationDatabase admin
```

# Mongo Shell
$ docker ps
$ docker exec -it {mongo container id} bash
root@5aac69feef04:/# mongo mongodb://localhost:27017 -u rootuser -p rootpass

> show dbs;

# data types
# bson

{
	string: "String of text",
	int: 405,
	double: 3.565,
	boolean: true,
	array: [1,2,3],
	object: { attr1: "attr1", attr2: "attr2" },
	date: new Date("<YYYY-mm-dd>"),
	object_id: <ObjectId>,
	no_value: null
}

# create a db
> use amigoscode;
> db.getName();
> db.createCollection("hello");
> db.dropDatabase();

> db.help();

# collection/table
> db.createCollection("person");
> show collections
> db.person.stats();
> db.person.drop();

> db.createCollection("person", { capped: true, size: 6142800, max: 3000 });

# another way of creating collection
> student = {
    "firstName": "John",
        "lastName": "Doe",
        "email": "john@sql.com",
        "gender": "M",
        "country": "UK",
        "isStudentActive": false,
        "favouriteSubjects": ["maths", "english", "it"],
        "totalSpentInBooks": 0.00
    }
> db.student.insert(student);
> db.student.insertOne(student);

> db.student.count();
> db.student.find().pretty();

# insert many
> db.student.insertMany(students);

# querying
> db.student.find({}).pretty();
> db.student.find({}, {_id: 0}).pretty(); # do not show id
> db.student.find({}).limit(3).pretty();
> db.student.find({}, { totalSpentInBooks: 1 }).limit(5).pretty();
> db.student.find({}).limit(10).sort({name:1}).pretty();
> db.student.find({ firstName: 'Lon'  }).pretty();
# or
> db.student.find({ $or: [{firstName: 'Lon'}, {favouriteSubjects: 'Romance'}]  }).pretty();

# where array size is ...
> db.student.find({ favouriteSubjects: { $size: 3 }})
> db.student.find({ favouriteSubjects: { $not: { $size: 3 } }})

# where first item in array is ...
> db.student.find({ "favouriteSubjects.0": "Horror"})

# get if array contains elements gte 80
> db.student.find({ grades: {$elemMatch: {$gte: 80 } } })

# distinct values
> db.student.distinct('country')

# gte lte
> db.student.find( { totalSpentInBooks: { $gte: 35 } } ).count()
> db.student.find( {favouriteSubjects : {$exists:true}, $where:'this.favouriteSubjects.length>=3'} )

# in
> db.student.find({ firstName: { $in: ['Lon', 'Gabriell', 'Eddie'] }})

# exists - if a field exists
> db.student.find( { asdf: { $exists: true } } ).count()

# check type (here, 2 stands for string)
> db.student.find( { firstName: { $type: 2 } } ).count()

# get only firstName and lastName
> db.student.find( { firstName: 'Lon' }, { firstName: 1, lastName: 1 } ).pretty(); 
# exclude firstName and lastName
> db.student.find( { firstName: 'Lon' }, { firstName: 0, lastName: 0 } ).pretty();

# update
> db.student.update({ _id: ObjectId("6104517a09b144e5ac651d9b") }, { $set: { firstName: 'Mark' } });
# update + increment
> db.student.update({ _id: ObjectId("6104517a09b144e5ac651d9b") }, { $inc: { totalSpentInBooks: 999 } });

> db.student.update({ _id: ObjectId("6104517a09b144e5ac651d9b") }, { $pull: { favouriteSubjects: "Adventure|Romance" } });
> db.student.update({ _id: ObjectId("6104517a09b144e5ac651d9b") }, { $push: { favouriteSubjects: "Adventure|Romance" } });

# update many
> db.student.updateMany({country: 'China'}, {$set: {country: 'Italy'}})

# replace a record where ... with ...
> db.student.replaceOne({firstName: 'Lon'}, {continent: 'Europe', country: 'Italy'})

# get rid of property
> db.student.update({ _id: ObjectId("6104517a09b144e5ac651d9b") }, { $unset: { lastName: 1 } });

# delete
> db.student.deleteOne({ _id: ObjectId("61044da209b144e5ac651d9a") });
> db.student.deleteMany( {gender: 'M' });

# bulk actions
> db.student.bulkWrite(
	[
		{ insertOne: 
			{ 
				"document": { name: "Andrew", major: "Architecture", gpa: 3.2 } 
			} 
		},
		{ insertOne: 
			{
				"document": {name: "Terry", major: "Math", gpa: 3.4 } 
			} 
		},
		{ updateOne: 
				{
					filter: { firstName:  "Isador" }, 
					update: { $set: { totalSpentInBooks: 30 } }
				}
		},
		{ deleteOne:
			{ filter: { firstName: "Fredia" } }
		},
		{ replaceOne:
			{
				filter: { firstName: "Filbert" },
				replacement: { firstName: "Genny", major: "Counsling", gpa: 2.4 }
			}
		}
	]
);

# text indexing
db.stores.insert(
   [
     { _id: 1, name: "Java Hut", description: "Coffee and cakes" },
     { _id: 2, name: "Burger Buns", description: "Gourmet hamburgers" },
     { _id: 3, name: "Coffee Shop", description: "Just coffee" },
     { _id: 4, name: "Clothes Clothes Clothes", description: "Discount clothing" },
     { _id: 5, name: "Java Shopping", description: "Indonesian goods" }
   ]
)
> db.stores.createIndex( { name: "text", description: "text" })
> db.stores.find({ $text: { $search: "Coffee" } })
> db.stores.find({ $text: { $search: "Java Hut Coffee" } })
> db.stores.find({ $text: { $search: "Java Hut Coffee" } }, 
	{ score: { $meta: "textScore" } }).sort( { score: { $meta: "textScore" } } )


# aggregation

db.purchase_orders.insertMany(
     [
          {product: "toothbrush", total: 4.75, customer: "Mike"},
          {product: "guitar", total: 199.99, customer: "Tom"},
          {product: "milk", total: 11.33, customer: "Mike"},
          {product: "pizza", total: 8.50, customer: "Karen"},
          {product: "toothbrush", total: 4.75, customer: "Karen"},
          {product: "pizza", total: 4.75, customer: "Dave"},
          {product: "toothbrush", total: 4.75, customer: "Mike"},
     ]
)

# count - find out how many toothbrushes were sold
db.purchase_orders.count({product: "toothbrush"})

# distinct - Find list of all products sold
db.purchase_orders.distinct("product")

# Find the total amount of money spent by each customer
db.purchase_orders.aggregate(
     [
		  // filter
          { $match: {} },
		  // group information together 
		  // _id => group by field
          { $group: {_id: "$customer", total: { $sum: "$total"} } }
     ]
)

# Find how much has been spent on each product and sort it by price
db.purchase_orders.aggregate(
     [
          {$match: {} },
          {$group: {_id: "$product", total: { $sum: "$total"} } },
          {$sort: {total: -1}}
     ]
)

# Find how much money each customer has spent on toothbrushes and pizza
db.purchase_orders.aggregate(
     [
          //{$match: {customer: {$in: ["Mike", "Karen"]}}},
		  {$match: {product: {$in: ["toothbrush", "pizza"]} } },
          {$group: {_id: "$product", total: { $sum: "$total"} } },
     ]
)

// https://docs.mongodb.com/manual/reference/operator/aggregation/
// Show the list of all pipeline operators




#### docker: remove any dangling resources
```
docker system prune
```

#### docker: remove any stopped containers and all unused images
```
docker system prune -a
```

#### all docker containers:
docker container ls -a
# or:
docker ps -a

#### stopping a container:
docker stop {CONTAINER ID}

#### remove all stopped containers:
docker container prune

#### see all images
docker image ls
docker images -a
docker images -f dangling=true
docker images -f dangling=true

#### removing images according to a pattern
docker images -a | grep "pattern" | awk '{print $3}' | xargs docker rmi

#### remove all unused images:
docker image prune

#### see all volumes:
docker volume ls

#### remove all unused volumes:
docker volume prune

# find out what is using port 80
sudo netstat -tulpn | grep :80
# or:
sudo fuser 80/tcp





