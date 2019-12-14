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
	Term term = TermsGson.createGson().fromJson(json, Term.class);
```