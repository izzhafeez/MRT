class DLLNode<T extends Comparable<T>> {
  
  private final T item;
  @SuppressWarnings("unchecked")
  private static final <? extends Comparable> EMPTY_NODE = new DLLNode<>(null);
  private DLLNode<T> previous;
  private DLLNode<T> next;

  public DLLNode<T>(T item) {
    this.item = item;
  }

}
