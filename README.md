# composite-terms
A light extensible <b>java 8+</b> library for creating composites of terms which are evaluatable using a visitor pattern.
Serialization to json and deserialization from json are supported by default using [gson](https://github.com/google/gson).

Next to gson no other libraries are needed.

## Table of Contents
- [JSON](#json)
- [Deserialize a Term](#deserialize-a-term)
- [Create a Term](#create-a-term)
  * [Creating a Term using a TermBuilder](#creating-a-term-using-a-termbuilder)
  * [Creating the same Term in a basic way](#creating-the-same-term-in-a-basic-way)
- [Evaluate a Term](#evaluate-a-term)
- [Serialize a Term](#serialize-a-term)
- [Add a custom Operator](#add-a-custom-operator)
- [Add a custom Constant Datatype](#add-a-custom-constant-datatype)
  
## Maven Dependency
```xml
<dependency>
  <groupId>com.github.hakenadu</groupId>
  <artifactId>composite-terms</artifactId>
  <version>0.0.3</version>
</dependency>
```

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

## Deserialize a Term
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
// again we'll use myTerm from above ;-)
String json = TermsGson.createGson().toJson(myTerm);
```

## Add a custom Operator
Multiple basic operators are already implemented and accessible via the [Operators class](https://github.com/Hakenadu/composite-terms/blob/master/src/main/java/de/hakenadu/terms/visitor/eval/op/Operators.java).

Own operators only have to registered if evaluations have to be performed.
In this case an implementation of [EvaluationVisitor](https://github.com/Hakenadu/composite-terms/blob/master/src/main/java/de/hakenadu/terms/visitor/eval/EvaluationVisitor.java) is used and evaluation rules for custom operators are then added as follows:

Creating a custom OperationEvaluator:
```java
/**
 * an OperationEvaluator for our custom operator "foo" which  operates on any type of operands and returns
 * "foo" if at least one {@link Number} operand is passed and "bar" otherwise.
 */
public class FooOperationEvaluator implements OperationEvaluator {

	@Override
	public String evaluate(final List<Object> operandValues) {
		final boolean foo = operandValues.stream().anyMatch(Number.class::isInstance);
		return foo ? "foo" : "bar";
	}
}
```

Registering the custom OperationEvaluator:
```java
EvaluationVisitor visitor = new SimpleEvaluationVisitor();
// ...
visitor.getOperationEvaluators().put("foo", new FooOperationEvaluator());
```

This example is contained in the [CustomOperatorTest](https://github.com/Hakenadu/composite-terms/blob/master/src/test/java/de/hakenadu/terms/visitor/eval/CustomOperatorTest.java).

## Add a custom Constant Datatype
Instances of [Constant](https://github.com/Hakenadu/composite-terms/blob/master/src/main/java/de/hakenadu/terms/Constant.java) are serialized to JSON with an additional "datatype" member. This is necessary to enable the correct deserialization using the gson [ConstantTypeHierarchyAdapter](https://github.com/Hakenadu/composite-terms/blob/master/src/main/java/de/hakenadu/terms/gson/ConstantTypeHierarchyAdapter.java).

The registration is done as follows:
```java
/** create a custom datatype class */
public class Foo {
	private String bar;
	private String bas;
}

/** create a ConstantValueConverter for the custom datatype */
public class FooConstantValueConverter implements ConstantValueConverter<Foo> {

	@Override
	public String getTypeName() {
		return "foo"; // is contained in json as value of the "datatype" member
	}

	@Override
	public Class<Foo> getTypeClass() {
		return Foo.class; // for typesafe deserialization
	}

	@Override
	public JsonElement toJson(Foo foo) {
		/*
		 * It is also possible to create a JsonObject by hand. In this case we want to
		 * rely on Gson's default serialization.
		 */
		return new Gson().toJsonTree(foo, Foo.class);
	}

	@Override
	public Foo fromJson(JsonElement jsonElement) {
		// for our custom deserialization we also want to use Gson's default mechanism
		return new Gson().fromJson(jsonElement, Foo.class);
	}
}
```

Then register the custom ConstantValueConverter on Gson-Creation:
```java
ConstantTypeHierarchyAdapter customConstantTypeHierarchyAdapter = new ConstantTypeHierarchyAdapter()
		.withValueConverter(new FooConstantValueConverter());

Gson mainGson = TermsGson.createGson(customConstantTypeHierarchyAdapter);
```

The [CustomConstantValueConverterTest](https://github.com/Hakenadu/composite-terms/blob/master/src/test/java/de/hakenadu/terms/gson/converter/CustomConstantValueConverterTest.java) contains the above explained example with more details.
