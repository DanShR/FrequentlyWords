package kz.dan;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            Node root = new Node(null);
            Node currentNode = root;
            int currentChar;
            while ((currentChar = reader.read()) >= 0) {
                if (Character.isLetter(currentChar)) {
                    char c = (char) Character.toLowerCase(currentChar);
                    Node childNode = currentNode.findChildNode(c);
                    if (childNode == null) {
                        Node newNode = new Node(c);
                        currentNode.add(newNode);
                        currentNode = newNode;
                    } else {
                        currentNode = childNode;
                    }
                } else {
                    if (currentNode != root) {
                        currentNode.setTerminal(true);
                        currentNode.incrementCount();
                        currentNode = root;
                    }
                }
            }
            if (currentNode != root) {
                currentNode.setTerminal(true);
                currentNode.incrementCount();
            }

            int resultCount = 20;
            Heap heap = new Heap();
            dfs(root, new LinkedList<>(), heap);
            int i = 0;
            while (!heap.isEmpty() && i < resultCount) {
                if (i != 0) {
                    writer.newLine();
                }
                i++;
                Item item = heap.get();
                writer.write(String.valueOf(item.getCount()));
                writer.write(" ");
                for (char c : item.getCharacters()) {
                    writer.write(c);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printUsage() {
        System.out.println("java -jar app <path to file>");
    }

    private static void dfs(Node node, LinkedList<Character> characters, Heap heap) {
        if (node.isTerminal()) {
            Item item = new Item(node.getCount(), new ArrayList<>(characters));
            heap.add(item);
        }
        for (Node curNode : node.getChildren()) {
            characters.addLast(curNode.getNodeValue());
            dfs(curNode, characters, heap);
            characters.removeLast();
        }
    }
}
