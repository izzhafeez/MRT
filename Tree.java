import java.util.StringJoiner;
import java.util.ArrayList;

class Tree<T extends Comparable<T>> {
  
  private Node<T> root;
  private int numOfNodes;

  public Tree() {
    this.root = Node.create(null);
    this.numOfNodes = 0;
  }

  private Node<T>[] emptyArray() { 
    @SuppressWarnings("unchecked")
    Node<T>[] nodes = (Node<T>[]) new Node[this.numOfNodes];
    return nodes;
  }

  public void insert(T toInsert) {
    //Node<T> node = Node.<T>create(toInsert);
    //ArrayList<T> path = new ArrayList<T>();
    this.numOfNodes++;
    this.root = this.insert(this.root, toInsert);
  }

  private Node<T> insert(Node<T> node, T toInsert) throws IllegalArgumentException {
    if (toInsert == null) {
      throw new IllegalArgumentException("Cannot insert null node");
    }
    if (node.isEmpty()) {
      return Node.<T>create(toInsert);
    }
    int comparison = node.getContent().compareTo(toInsert);
    if (comparison == 0) {
      System.out.println("Node already exists");
      return node;
    } else if (comparison == 1) {
      node.setLeft(this.insert(node.getLeft(), toInsert));
    } else {
      node.setRight(this.insert(node.getRight(), toInsert));
    }
    node.setHeight();
    int heightDiff = this.getHeightDiff(node);
    if (heightDiff < -1) {
      if (this.getHeightDiff(node.getRight()) > 0) {
        node.setRight(this.rightRotate(node.getRight()));
        return this.leftRotate(node);
      } else {
        return this.leftRotate(node);
      }
    } else if (heightDiff > 1) {
      if (this.getHeightDiff(node.getLeft()) < 0) {
        node.setLeft(this.leftRotate(node.getLeft()));
        return this.rightRotate(node);
      } else {
        return this.rightRotate(node);
      }
    } else {;}
    return node;
  }

  public Node<T> getRoot() { return this.root; }

  public int getHeightDiff(Node<T> node) {
    if (node.isEmpty()) {
      return 0;
    }
    return node.getLeft().getHeight() - node.getRight().getHeight();
  }

  private Node<T> leftRotate(Node<T> node) {
    Node<T> right = node.getRight();
    node.setRight(right.getLeft());
    right.setLeft(node);
    node.setHeight();
    right.setHeight();
    return right;
  }

  private Node<T> rightRotate(Node<T> node) {
    Node<T> left = node.getLeft();
    node.setLeft(left.getRight());
    left.setRight(node);
    node.setHeight();
    left.setHeight();
    return left;
  }

  private void traverseHelper(
      Node<T> node,
      Node<T>[] nodes,
      int[] i) {
    if (node.isEmpty()) {
      return;
    } else {
      traverseHelper(node.getLeft(), nodes, i);
      nodes[i[0]] = node;
      i[0]++;
      traverseHelper(node.getRight(), nodes, i);
    }
  }

  public Node<T>[] traverse() {
    Node<T>[] nodes = this.emptyArray();
    int[] i = new int[] { 0 };
    traverseHelper(this.root, nodes, i);
    return nodes;
  }
  
  private void getPreviousHelper(Node<T>[] info, T toGet) {
    if (info[0].isEmpty()) {
      return;
    }
    int comparison = info[0].getContent().compareTo(toGet);
    if (comparison == 0) {
      return;
    } else if (comparison == 1) {
      info[0] = info[0].getLeft();
      getPreviousHelper(info, toGet);
    } else {
      info[1] = info[0];
      info[0] = info[0].getRight();
      getPreviousHelper(info, toGet);
    }
  }

  public Node<T> getPrevious(T toGet) throws NotFoundException {
    @SuppressWarnings("unchecked")
    Node<T>[] startInfo = (Node<T>[]) new Node[] { this.root, Node.<T>create(null) };
    this.getPreviousHelper(startInfo, toGet);
    Node<T> node = startInfo[0];
    Node<T> ancestor = startInfo[1];
    if (node.isEmpty()) {
      throw new NotFoundException("Node not found");
    } else if (node.getLeft().isEmpty()) {
      return ancestor;
    } else {    
      node = node.getLeft();
      while (!node.getRight().isEmpty()) {
        node = node.getRight();
      }
      return node;
    }
  }

  private void getNextHelper(Node<T>[] info, T toGet) {
    if (info[0].isEmpty()) {
      return;
    }
    int comparison = info[0].getContent().compareTo(toGet);
    if (comparison == 0) {
      return;
    } else if (comparison == 1) {
      info[1] = info[0];
      info[0] = info[0].getLeft();
      getNextHelper(info, toGet);
    } else {
      info[0] = info[0].getRight();
      getNextHelper(info, toGet);
    }
  }

  public Node<T> getNext(T toGet) throws NotFoundException {
    @SuppressWarnings("unchecked")
    Node<T>[] startInfo = (Node<T>[]) new Node[] { this.root, Node.<T>create(null) };
    this.getNextHelper(startInfo, toGet);
    Node<T> node = startInfo[0];
    Node<T> ancestor = startInfo[1];
    if (node.isEmpty()) {
      throw new NotFoundException("Node not found");
    } else if (node.getRight().isEmpty()) {
      return ancestor;
    } else {
      node = node.getRight();
      while (!node.getLeft().isEmpty()) {
        node = node.getLeft();
      }
      return node;
    }
  }

  @Override
  public String toString() {
    Node<T>[] nodes = this.traverse();
    StringJoiner joiner = new StringJoiner(", ", "[", "]");
    for (Node<T> node : nodes) {
      joiner.add(node.toString());
    }
    return joiner.toString();
  }
  
}
