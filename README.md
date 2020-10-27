# JavaBeans
![Maven Central](https://img.shields.io/maven-central/v/com.github.tomtzook/javabeans)
![Travis (.org) branch](https://img.shields.io/travis/tomtzook/JavaBeans/master.svg)
![GitHub](https://img.shields.io/github/license/tomtzook/JavaBeans.svg)


Library providing java beans utilities and observables.
Provides interfaces and several implementations for properties, observable data and such.

## Building

Simply run `./gradlew build` from the main directory.
This will build the library and export it along with a sources jar and javadoc archive into `build/libs`.

## Properties

JavaBeans introduces the `Property` interface, which is a mutable value, extending upon
_Java_'s `java.uti.function.Supplier` interface.

Basic properties, which implement `Property` expose `set` and `get` to modify the internal
their value:
```Java
Property<String> prop = ...

prop.set("Hello World");
System.out.println(prop.get()); // returns "Hello World"
```

There is no restrictions on how implementations store and access values, so make
sure to check which implementation you use and whether or not it fits your needs.

### Specialized Properties

In addition to the basic interface, there are also specializations for primitive 
types: `long`, `int`, `boolean`, `double`. Each specialization still exports `set` and `get`
as it extends the base `Property` interface, however those methods return a wrapper class.
To get a primitive type, use `getAsType` and `setAsType` where `Type` is replaced by the 
primitive type, e.g. `getAsBoolean`, `getAsInt` etc. It is recommended to use those methods.

```Java
IntProperty prop = ...

prop.setAsInt(12);
System.out.println(prop.getAsInt()); // returns 12 
```

The `set` methods do not accept `null` values in specialization implementations.

### Implementations

JavaBeans provides the following implementations for `Property`, each implementation exists for 
the specializations as well:
- Simple: an in-memory implementation which is not thread-safe. Under `com.beans.properties`
- Atomic: an in-memory thread-safe implementation. Under `com.beans.properties.atomic`

### Observables

An `ObservableValue` is a value which can be observed for changes using listeners. Based
on the `java.util.function.Supplier` interface.

An `ObservableProperty` is a property which can be observed for changes using listeners.
Based on the `Property` and `ObservableValue` interfaces.

Both have primitive specializations for types: `long`, `int`, `boolean`, `double`.

Creation of such properties should be done using `ObservableFactory`.

#### Listeners

Users may register `ChangeListener`s to an _observable_.
Any changes to that _observable_'s value will
cause an invocation of the listener with `ChangeEvent`.

```Java
ObservableIntProperty prop = ....
prop.addChangeListener((e)-> {
    System.out.println("New value: " + e.getNewValue())
});
```

#### Binding

Binding `Observable`s will connect them. It is only possible between 2 `Observable`s with 
similar types. There are 2 types of bindings:

- Single-Directional binding, `o1.bind(o2)` will connect the value of observable
`o1` to `o2`, such that any changes to `o2` will change the value of `o1`. However,
changing the value of `o1` directly, with `setValue` will not be allowed, causing
a `RuntimeException`.
```Java
ObservableIntProperty prop1 = ...;
prop1.set(2);
ObservableIntProperty prop2 = ...;
prop2.set(10);

System.out.println(prop1.get()); // returns 2

prop1.bind(prop2);
System.out.println(prop1.get()); // returns 10

prop2.set(100);
System.out.println(prop1.get()); // returns 100
System.out.println(prop2.get()); // returns 100

prop1.set(5); // throws IllegalStateException
```

- Bi-Directional binding, `o1.bindBidirectional(o2)` will connect the value of observable
`o1` to `o2`, such that any changes to `o2` will change the value of `o1`, and changes to `o1`
will change `o2`. 
```Java
ObservableIntProperty prop1 = ...;
prop1.set(2);
ObservableIntProperty prop2 = ...;
prop2.set(10);

System.out.println(prop1.get()); // returns 2

prop1.bindBidirectional(prop2);
System.out.println(prop1.get()); // returns 10

prop2.set(100);
System.out.println(prop1.get()); // returns 100
System.out.println(prop2.get()); // returns 100

prop1.set(5);
System.out.println(prop1.get()); // returns 5
System.out.println(prop2.get()); // returns 5
```

While bound, properties will still invoke listeners on changes.

#### Observable From Supplier

Using `PollingObserableFactory`, it is possible to create `ObservableValue`s out of `Supplier`s 
(including specializations).

Create the factory first:
```Java
ObserableFactory observableFactory = ...;
ScheduledExecutorService executorService = ...;
int pollingTimeMs = 25;

PollingObserableFactory factory = new PollingObserableFactory(observableFactory, executorService, pollingTimeMs); 
```

And simply use `factory.from` to create the `ObservableValue`. Now, it will be possible to listen
to changes of the `Supplier` and bind it to other observables.

```Java
Supplier<String> supplier = ...;
ObservableValue<String> observable = factory.from(supplier);
```

The `ScheduledExecutorService` will be used to poll updates from the supplier and test for changes in the value,
making it observable. The `pollingTimeMs` is the period for polling the supplier.

#### Global

For easier work with the observable factories, use the `Observables` class. This class will
automatically create the factories and provide access to them:

```Java
ObservableIntProperty prop = Observables.factory().newIntProperty();

Supplier<Object> supplier = ...;
ObserableValue<Object> observable = Observables.pollingFactory().from(supplier);
```

The created factories will use a `ScheduledExecutorService` created specifically for dispatching events
and for polling suppliers. This executor service will be terminated automatically using a shutdown hook.

It is also possible to configure the factories manually instead of using the automatically created ones. This should 
be done before any usage or access to the factories:
```Java
ObservableFactory factory = ...;
Obserables.setFactory(factory);

ObservablePollingFactory pollingFactory = ...;
Obserables.setPollingFactory(pollingFactory);
```

It is also possible to configure the `ScheduledExecutorService` that will be used by the factories,
using `setExecutorService`. Doing so will make the factories use that executor service, but should be done
before any usage/access to the factories. The executor service will not be terminated automatically, and
this must be the responsibility of the user providing the instance.
