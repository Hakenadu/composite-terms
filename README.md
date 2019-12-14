# composite-terms
A light extensible <b>java</b> library for creating composites of terms which are evaluatable using a visitor pattern.

Serialization to json and deserialization from json are also supported by default using [gson](https://github.com/google/gson).

## JSON
This is an example term:

```json
{
	"type": "operation",
	"operator": "subtract",
	"operands": [
		{
			"type": "constant",
			"datatype": "number",
			"value": 1337
		},
		{
			"type": "operation",
			"operator": "add",
			"operands": [
				{
					"type": "constant",
					"datatype": "number",
					"value": 500
				},
				{
					"type": "variable",
					"name": "x"
				}
			]
		}
	]
}
```

## Deserialization
The term mentioned above is deserialized as follows (let json be a string constant containing the mentioned structure):
```java
Term deserializedTerm = TermsGson.createGson().fromJson(json, Term.class);
```

## Create a Term
Terms may be created directly as json but also from java.

### Creating a Term using a TermBuilder
```java
// 1337 - (500 + x)
Term myTerm = PrefixTermBuilder.create()
	.beginOperation(Operators.SUBTRACT)
	.constant(1337)
	.beginOperation(Operators.ADD)
	.constant(500)
	.variable("x")
	.endOperation()
	.endOperation()
	.build();
```
### Creating the same Term in a basic way
```java
Term myTerm = new Operation(Operators.SUBTRACT,
	new Constant(1337),
	new Operation(Operators.ADD,
		new Constant(500),
		new Variable("x")));
```
