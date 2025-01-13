# Java xAPI Library

This library is intended to be a toolkit for using xAPI in Java. Right now it has Jackson model objects for serialization and deserialization of xAPI statements, as well as a configured Jackson ObjectMapper to perform the operations.

## Jackson Serialization and Deserialization

This library comes with a model of xAPI Statements and components which should allow easy conversion between Java Objects and JSON. Currently it is tailored to support xAPI Version 1.0.0-1.0.3. Newer versions will be supported in the future.

### Deserialization

Deserialization can be performed on xAPI Strings or files or inputstreams using the provided ObjectMapper.

Take the following xAPI Statement:

```
{
  "id": "6fbd600f-b17c-4c74-801a-2ec2e53231c8",
  "actor": { "mbox": "mailto:student1@example.com" },
  "verb": {"id": "https://xapi.yetanalytics.com/profile/concepts/verbs/did" },
  "object": {"id": "https://xapi.yetanalytics.com/profile/concepts/activities/act1" },
  "timestamp": "2023-10-27T09:03:21.723Z",
  "stored": "2023-10-27T09:03:21.723Z"
}
```
You can turn it into a Statement Object like so:

```
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.util.Mapper;
...
Statement stmt = Mapper.getMapper().readValue(xApiString, Statement.class);
System.out.println(stmt.getVerb().getId()); 

//-> "https://xapi.yetanalytics.com/profile/concepts/verbs/did"

```

### Serialization

The process for creating xAPI JSON is very similar, just take one of the objects write it out.

```
String xapi = Mapper.getMapper().writeValueAsString(stmt);

```
Which produces a JSON statement identical to the one at the top.

### Use with Custom ObjectMapper

If you need to create your own ObjectMapper or prefer to use an existing one in your project, please refer to the additional configuration in [Mapper.java](src/main/java/com/yetanalytics/xapi/util/Mapper.java). The most important is adding the Java Time module to the Mapper because it does not ship with java.time support.

## LRS Client

Coming Soon...

## xAPI Validation

Coming Soon...

## License

Copyright © 2024-2025 Yet Analytics, Inc.

Distributed under the Apache License version 2.0.
