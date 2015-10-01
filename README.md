# play2-scala-todo
Todo Application on Play2 / Scala

## Usage
```
$ JAVA_OPTS=-Dhttps.port=9443 activator run
```

## API
index
```
$ curl http://localhost:9000/api/todos
```

show
```
$ curl http://localhost:9000/api/todos/:id
```

create
```
$ curl http://localhost:9000/api/todos -H "Content-type: application/json" \
 -X POST -d '{"status":true, "title":"Shopping"}'
```

update
```
$ curl http://localhost:9000/api/todos/:id -H "Content-type: application/json" \
 -X PUT -d '{"status":true, "title":"Meeting"}'
```

delete
```
$ curl http://localhost:9000/api/todos/:id -X DELETE
```

or ``https://localhost:9443/<PATH>``

## License
MIT