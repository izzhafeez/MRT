class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
  
  @SuppressWarnings("unchecked")
  private static final Node<? extends Comparable> EMPTY_NODE = new Node<>(null);
  private final T content;
  private int height;
  private Node<T> left;
  private Node<T> right;

  private Node(T content) {
    this.content = content;
    if (content == null) {
      this.height = -1;
    } else {
      this.height = 0;
      this.left = Node.<T>create(null);
      this.right = Node.<T>create(null);
    }
  }

  public static <T extends Comparable<T>> Node<T> create(T content) {
    if (content == null) {
      @SuppressWarnings("unchecked")
      Node<T> toReturn = (Node<T>) Node.EMPTY_NODE;
      return toReturn;
    }
    return new Node<T>(content);
  }

  public Node<T> getLeft() { return this.left; }
  public void setLeft(Node<T> node) { this.left = node; }
  
  public Node<T> setLeft() {
    Node<T> left = this.left;
    this.left = Node.<T>create(null);
    return left;
  }
  
  public Node<T> getRight() { return this.right; }
  public void setRight(Node<T> node) { this.right = node; }

  public Node<T> popRight() { 
    Node<T> right = this.right;
    this.right = Node.<T>create(null);
    return right;
  }
  
  public T getContent() { return this.content; }
  public int getHeight() { return this.height; }
  public boolean isEmpty() { return this.content == null; }

  public void setHeight() {
    int left = this.left.getHeight();
    int right = this.right.getHeight();
    this.height = left > right ? left+1 : right+1;
  }

  @Override
  public int compareTo(Node<T> node) {
    return node.getContent().compareTo(this.content);
  }

  @Override
  public String toString() {
    return String.format("%s", this.content);
  }

}
