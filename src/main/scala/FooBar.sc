class FooBar[+T](t: T) {
  def get = t
  def apply[U >: T](u2: U): U = u2
}

val foo = new FooBar(new Fruit)
println(foo.get)
println(foo.apply(new Plant))
println(foo.apply(new Apple))

class Plant
class Fruit extends Plant
class Apple extends Fruit

class Bar[-U]

def ident(bar: Bar[Fruit]): Bar[Fruit] = bar

ident(new Bar[Plant])
ident(new Bar[Fruit])
// ident(new Bar[Apple])


def map(l: List[Fruit], func: Fruit => Fruit) = l.map(func)

def foo(l: List[Fruit], func: Fruit => Fruit) = l.map(func)

val fruits: List[Fruit] = List(new Fruit)

val f: Fruit => Fruit = _ => new Apple()
val g: Plant => Apple = _ => new Apple()
val h: Apple => Plant = _ => new Apple()

foo(fruits, f)
foo(fruits, g)   // g <: f
//foo(fruits, h)   // error


def pair[T](a: T, b: T) = (a, b)
def epair[T, U](a: T, b: U)(implicit ev: T =:= U) = (a,b)
def subpair[T, U](a: T, b: U)(implicit ev: T <:< U) = (a,b)


println(pair(new Fruit, new Plant))
println(pair(new Fruit, 1))

abstract class Acceptable[T]

object Acceptable {
  implicit object IntOk extends Acceptable[Int]
  implicit object LongOk extends Acceptable[Long]
}

def f[T: Acceptable](t: T) = t

f(1)
f(1l)
f(1f)

def triplet[T,U,V](a: T, b: U, c: V)(implicit ev1: T =:= U, ev2: U =:= V) = (a,b,c)
