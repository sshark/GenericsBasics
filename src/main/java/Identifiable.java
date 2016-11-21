interface Identifiable<T> {
    default T  identity(T t) {
      return t;
    }
}

