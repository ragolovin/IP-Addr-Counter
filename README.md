# Unique IP Address Counter


A simple text file with IPv4 addresses is given. One line – one address, something like this:

```
145.67.23.4
8.34.5.23
89.54.3.124
89.54.3.124
3.45.71.5
...
```

The application count the number of __unique addresses__ in this file.

First application argument is a path to the file.

Second argument defines count algorithm:

* b -> the best. Uses bitmap data structure.
* h -> effective for addresses from same subnets.
* e -> (default) effective for random addresses. Will be broken for huge amount of addresses.

All implementations are not threadsafe.

#### Compilation and start
Java 17 and Maven are required.
```bash
$> mvn package && java -jar .\target\id-addr-counter-1.0-SNAPSHOT.jar ".\src\test\resources\ip.txt b"
```