# Start Mysql DB:

```docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root-pwd -p 3306:3306 -d mysql:5.7```

# Connect to DB:
```mysql -h 127.0.0.1 -uroot -proot-pwd```
# Create DB:

```sh
CREATE DATABASE dev;
USE dev;
CREATE TABLE IF NOT EXISTS score (
    name VARCHAR(30),
    game VARCHAR(30),
    score INT,
    timestamp TIMESTAMP,
    UNIQUE (name, game)
);
```
