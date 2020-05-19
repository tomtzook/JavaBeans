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

JavaBeans introduces the `Property` interface, which is a mutable value. 
There are also specializations for the interface for primitive types: `long`, `int`, `boolean`, `double`.

### Implementations

JavaBeans provides the following implementations for `Property`, each implementation exists for the specializations as well:
- Simple: a basic variable value which can be read from and written to.
- Atomic: a thread-safe implementation.

### Observables

An `ObservableValue` is a value which can be observed for changes using listeners. Based
on the `java.util.Supplier` interface.

An `ObservableProperty` is a property which can be observed for changed using listeners.
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
similar types. There are 2 types of bindings.

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
    
