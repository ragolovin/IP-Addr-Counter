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
The first application argument is a path to the file.

#### Compilation and start
Java 17 and Maven are required.
```bash
$> mvn package && java -jar .\target\id-addr-counter-1.0-SNAPSHOT.jar ".\src\test\resources\ip.txt"
```