# Data-Structures

A place to hold implementations of data structures for future reference.

* 4/5/2023 - Implemented Linked List
* 5/20/2023 - Finished implementing BST
* 5/26/2023 - Finished refactoring BST to include generics and functional interfaces.
* 6/2/2023 - A basic graph ADT is functional. Transferred LA Notes to this repository. The notes contain an intersection
of graph theory with linear algebra and algorithms broadly defined.
* 6/4/2023 - Sparse Graph class is finshed for now. Plan on looking at minimal spanning trees next.
* 6/5/2023 - Implemented dfs and bfs in a utilities class. To use a type variable for a static method do
something like this:

```java
public static <T> boolean bfSearch(Graph<T> graph, T key)
```
* 6/6/2023 - Implemented shortest path using bfs in utilities class. Still needs to be tested. Cormen, et.al.
has the best explanation of how to do this. Worked through the first chapter of Stanley's book *Algebraic
Combinatorics*. Will add some of those notes to the Notes later. Finding spectral graph theory particularly
interesting right now.

* 6/8/2023 - Worked through examples of Dijkstra's algorithms from Sedgewick and the grokking book. Working through
the three different implementations was enlightening.

* 6/9/2023 - Changed the abstraction. Graph is now an abstract class instead of an interface. Member variables
moved to Graph also. Since Graph is abstract, it has no constructor. Methods that work for both SparseGraph and
WeightedGraph have been moved to Graph. Graph now takes two type variables to account for using adjacent edge
lists in WeightedGraph.

* 6/9/2023 - Worked out pencil-and-paper examples of topological sort of a DAG. Read through the chapter on
actions of the symmetric group on a boolean algebra, from Stanley's *Algebraic Combinatorics*.

* 6/10/2023 - Created the abstract graph interface.

* 6/11/2023 - Created an inner class Pair in utilities to pair a vertex with a weight or cost.

```java
private static class Pair<T>
```
The private keyword is used to keep access restricted to the class it is defined in. The keyword static allows
use without an instance of Utilities. Static classes can't implement an interfaces. Implemented Dijkstra's
algorithm as presented in *grokking algorithms*. A very inefficient coding day.

* 6/12/2023 - I was unaware that overriding equals also requires overriding ```java hashCode()```. Changed member
variable name in pair to reflect a more abstract use. Playing with lambda expressions and anonymous functions.
Here's and example with no return value:

```java
interface Test<T>{
    void doThis(T c, T p);
}

public static Test thisTest = (c,p) -> {System.out.println(p); System.out.println(c);};
```

and invoking it: ```java thisTest.doThis( "aa",  "bb");``` or ```java thisTest.doThis(2, 3);```

Using ```java Function``` or ```java BiFunction``` from the ```java.util.function``` are also very useful.

Here's another example:

```java
public static <T> T myTest(T v, T w, BiFunction<T, T, T> method){
    return method.apply(v, w);
}

static BiFunction<Integer, Integer, Integer> test = (c,d) -> {return c+d;};
```

which was invoked with ```java myTest(2,3,test);```

In the implementation of Dijkstra's algorithm, I never really liked having another static method that
produced the least cost vertex. It is such a specific helper to this method and this implementation it would
never be used for any other purpose. I created a ```Supplier``` inside of the method to make this into a
lambda expression. Lambda expressions can "capture" local variables. Using the lambda expression inside the
```dijkstra``` method also saves us from needlessly passing the ```costs``` hashtable and the ```processed```
list.

It may seem strange to "define" a function inside of a method. However, assigning a lambda expression to a
variable does two things:

1. We get to treat code as if it were data. This is a large part of what lambda expressions are meant to do.
2. We are binding a name to a piece of data just like any other variable declaration. It just happens that in
this case our data is a block of code.

* 6/13/2023 - Considering taking the static methods in Utilities and placing them back into the classes that
they are associated with. 

Many of the vocab notes are from *Algorithms* Cormen, Leiserson, Rivest, Stein.
