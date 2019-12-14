# composite-terms
A light extensible <b>java</b> library for creating composites of terms which are evaluatable using a visitor pattern.

Serialization to json and deserialization from json are also supported by default using [gson](https://github.com/google/gson).

- [composite-terms](#composite-terms)
  * [JSON](#json)
  * [Deserialization](#deserialization)
  * [Create a Term](#create-a-term)
    + [Creating a Term using a TermBuilder](#creating-a-term-using-a-termbuilder)
    + [Creating the same Term in a basic way](#creating-the-same-term-in-a-basic-way)
  * [Evaluate a Term](#evaluate-a-term)
  * [Serialize a Term](#serialize-a-term)
  
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
// 1337 - (500 + x)
Term myTerm = new Operation(Operators.SUBTRACT,
	new Constant(1337),
	new Operation(Operators.ADD,
		new Constant(500),
		new Variable("x")));
```

## Evaluate a Term
A composite of Terms is evaluated using a visitor pattern.
The concrete Visitor must be able to provide values for all variable names in a term.
```java
/*
 * a SimpleEvaluationVisitor is an implementation of the EvaluationVisitor
 * which uses a java.util.Map to map concrete variable names to values.
 */
SimpleEvaluationVisitor evaluationVisitor = new SimpleEvaluationVisitor();

// the only variable in myTerm is "x" so lets apply the value -163 to it.
evaluationVisitor.getVariableValues().put("x", -163);

// an instance of the EvaluationContext is passed on visit to retrieve values
EvaluationContext evaluationContext = new EvaluationContext();

// lets evaluate our Term...
myTerm.accept(evaluationVisitor, evaluationContext);

// ...done: result == 1000
Number result = (Number) evaluationContext.getValue();
```

## Serialize a Term
Serialization is also done via [gson](https://github.com/google/gson).
```java
// we'll use myTerm from above in most of the following examples ;-)
String json = TermsGson.createGson().toJson(myTerm);
```
