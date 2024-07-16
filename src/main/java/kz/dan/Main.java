/*
Описание решения
Решение основано на ограничении, что не разрешено использовать такие классы как Map и String.
Для решения я реализовал структуру "Префиксное дерево" (класс Node),
в котором дополнительно в терминальной ноде создал поле "count" для подсчета количества слов.
Посимвольно читается входной файл и формируется дерево.
Для вывода 20-ти часто встречающихся слов я реализовал структуру данных "Куча"(также известная как "Приоритетная очередь" и "Пирамида").

После построения дерева обходим его в глубину попутно собирая слова и в терминальных нодах сбрасываем их в кучу.

В конце проходим по куче до конца, но не более 20-ти раз и выводим элементы.

Временная сложность:
   Посимвольная обработка файла и построение префиксного дерева O(n), где n-это количество символов
   Обход в глубину O(n), где n-это количество узлов дерева
   Вставка и удаление из кучи O(log n), где n-это количество элементов в куче, то есть слов в тексте
   Таким образом общая временная сложность всего решения равна O(n), где n-это количество символов

Пространственная сложность:
    Префиксное дерево стоит O(n), где n-это суммарная длина уникальных слов в тексте
    Куча стоит O(m), где m-это количество уникальных слов в тексте
     Таким образом общая пространственная сложность всего решения равна:
    O(m) + O(n), где n-это суммарная длина уникальных слов в тексте, m-это количество уникальных слов в тексте

Причечание:
Если решать без ограничений, то я бы сделал через HashMap<String,Integer> и PriorityQueue

 */

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
                } else if (currentNode != root) {
                    currentNode.setTerminal(true);
                    currentNode.incrementCount();
                    currentNode = root;
                }
            }
            //если файл заканчивается значащим символом, то нужно про него не забыть
            if (currentNode != root) {
                currentNode.setTerminal(true);
                currentNode.incrementCount();
            }

            int resultCount = 20;
            Heap heap = new Heap();
            //в связанном списке будем накапливать слова при обходе дерева
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

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("java -jar FrequentlyWords.jar <path to file>");
    }
}
