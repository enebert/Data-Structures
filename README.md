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

and invoking it: ```thisTest.doThis( "aa",  "bb");``` or ```thisTest.doThis(2, 3);```

Using ```Function``` or ```BiFunction``` from the ```java.util.function``` package are also very useful.

Here's another example:

```java
public static <T> T myTest(T v, T w, BiFunction<T, T, T> method){
    return method.apply(v, w);
}

static BiFunction<Integer, Integer, Integer> test = (c,d) -> {return c+d;};
```

which was invoked with ```myTest(2,3,test);```

In the implementation of Dijkstra's algorithm, I never really liked having another static method that
produced the least cost vertex. It is such a specific helper to this method (and this implementation) it would
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
they are associated with. The Utilities class is now gone. The static methods have been moved to their
appropriate class. The ```Pair``` class has been moved to the Graph class and marked protected. This has
resulted in better organization and better resuability of the methods.

* 6/16/2023 - Read the chapter in Stanley's *Algebraic Combinatorics* on the signed Laplacian and the Matrix-Tree
theorem. His sketch of the Cauchy-Binet theorem is somewhat vague (even for a sketch) but a good reminder of the
result. He uses this to prove the major theorem in the chapter which states that the number of spanning trees is
given by the determinant of a modified signed Laplacian.

* 6/17/2023 - Worked through examples of Kruskal's algorithm and Prim's algorithm for obtaining a MST. I used
Cormen, et. al. as the source for this. These are both greedy algorithms that are proven to be correct via an
application of the Cut Property. I have many hand-written notes that need to be added to the LA Notes.

**Remark:** I have probably accomplished what I'm going to accomplish before leaving for my summer job. 

Many of the vocab notes are from *Algorithms* Cormen, Leiserson, Rivest, Stein.
