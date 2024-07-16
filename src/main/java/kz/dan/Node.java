package kz.dan;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node(Character nodeValue) {
        this.nodeValue = nodeValue;
        this.children = new ArrayList<>();
    }

    public Node findChildNode(char value) {
        for(Node node: children) {
            if (node.getNodeValue().equals(value)) {
               return node;
            }
        }
        return null;
    }

    public void add(Node node) {
        children.add(node);
    }

    public void incrementCount() {
        count++;
    }

    private final Character nodeValue;
    private final List<Node> children;
    private boolean terminal;
    private int count;

    public Character getNodeValue() {
        return nodeValue;
    }

    public List<Node> getChildren() {
        return children;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public int getCount() {
        return count;
    }

}
