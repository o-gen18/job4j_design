package ru.job4j.collections.exam;

import java.util.*;

public class Emails {

    private Map<User, Set<String>> data;

    public static class User { // Модель данных User будет выступать вершиной графа
        private boolean wasVisited; //флаг для алгоритма обхода

        private String name;

        private Set<String> emails;

        public User(String name, Set<String> emails) {
            this.name = name;
            this.emails = emails;
            wasVisited = false;
        }

        public void setName(String newName) {
            name = newName;
        }

        public String getName() {
            return name;
        }

        public Set<String> getEmails() {
            return emails;
        }

        public void addEmails(Collection<String> urls) {
            emails.addAll(urls);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    class Vertex { //класс опиывающий вершину графа
        private Map.Entry<?, ?> entry;

        private boolean wasVisited;

        public Vertex(Map.Entry<?, ?> entry) {
            this.entry = entry;
            wasVisited = false;
        }
    }

    class Graph { //модель графа с вершинами Map<email, Set<User>> ,
        // рёбрами будут наличие общих User
        private int verts; //всего вершин
        private Vertex[] vertexList; //массив вершин
        private boolean[][] matchMatrix; //матрица смежности
        private int nVerts; //текущее количество вершин
        private Queue queue;

        public Graph(int verts) {
            vertexList = new Vertex[verts];
            this.verts = verts;
            matchMatrix = new boolean[verts][verts];
            nVerts = 0;
            queue = new Queue(verts);
        }

        public void addVertex(Map.Entry<?, ?> entry) {
            vertexList[nVerts++] = new Vertex(entry);
        }

        public void addEdge(int start, int end) {
            matchMatrix[start][end] = true;
            matchMatrix[end][start] = true;
        }

        public Map.Entry<?, ?> showMap(int v) {
            return vertexList[v].entry;
        }

        public int getUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++) {
                if (matchMatrix[v][j] && !vertexList[j].wasVisited) {
                    return j; //возвращает первую найденную вершину
                }
            }
            return -1; //непосещённых вершин нет
        }
    }

    class Queue {
        private int size;
        private int[] queueArray;
        private int front;
        private int rear;

        public Queue(int size) {
            this.size = size;
            queueArray = new int[size];
            front = 0;
            rear = -1;
        }

        public void insert(int j) { //вставка в конец очереди
            if (rear == size - 1) {
                rear = -1;
            }
            queueArray[++rear] = j;
        }

        public int remove() {
            int temp = queueArray[front++];
            if (front == size) {
                front = 0;
            }
            return temp;
        }

        public boolean isEmpty() {
            return (rear + 1 == front || (front + size - 1 == rear));
        }
    }

    public Map<User, Set<String>> mergeIfCommonURL(Map<User, Set<String>> map) {
        Map<User, Set<String>> copyOfMap = new HashMap<>(map);
        Map<User, Set<String>> newMap = new HashMap<>();
        Set<String> emails = new HashSet<>();
        Map<String, Set<User>> vertexes = new HashMap<>();
        for (Map.Entry<User, Set<String>> entry : map.entrySet()) {
            emails.addAll(entry.getValue());
        }
        for (String email : emails) {
            Set<User> sameUsers = new HashSet<>();
            for (User user : map.keySet()) {
                if (map.get(user).contains(email)) {
                    sameUsers.add(user);
                }
            }
            vertexes.put(email, sameUsers);
        }
        for (Map.Entry<User, Set<String>> entry : map.entrySet()) {
            Set<String> hisEmails = entry.getValue();
            Set<User> sameUser = new HashSet<>();
            for (String email : hisEmails) {
                sameUser.addAll(vertexes.get(email));
            }
        }

//        Set<User> addedUsers = new HashSet<>();
//       for (Map.Entry<String, Set<User>> entry : connectionsOfVertexes.entrySet()) {
//           Iterator<User> userIt = entry.getValue().iterator();
//           User firstUser = userIt.next();
//           while(addedUsers.contains(firstUser) && userIt.hasNext()) {
//               firstUser = userIt.next();
//           }
//           Set<String> hisEmails = new HashSet<>();
//           while(userIt.hasNext()) {
//               User next = userIt.next();
//               hisEmails.addAll(map.get(next));
//               copyOfMap.remove(next);
//           }
//           hisEmails.addAll(map.get(firstUser));
//           addedUsers.addAll(entry.getValue());
//           newMap.put(firstUser, hisEmails);
//           copyOfMap.remove(firstUser);
//           if(copyOfMap.isEmpty()) {
//               break;
//           }
//       }
        return newMap;
    }

//    Map<User, Set<String>> newMap = new HashMap<>();
//    Graph graph = new Graph(map);
//        for (int i = 0; i < map.size(); i++) {
//        graph.addVertex(map.keySet().iterator().next());
//    }


//    public Map<User, Set<String>> mergeIfCommonURL(Map<User, Set<String>> map) {
//        data = map;
//
//        Map<User, Set<String>> newMap = new HashMap<>();
//        Set<String> emails = new HashSet<>();
//        for (Map.Entry<User, Set<String>> entry : map.entrySet()) {
//            emails.addAll(entry.getValue());
//        }
//        Set<User> users = map.keySet();
//        int row = map.size();
//        int column = emails.size();
//        boolean[][] matches = new boolean[row][column];
//        int indexRow = 0;
//        int indexColumn = 0;
//        for (String email : emails) {
//            Set<User> sameUsers = new HashSet<>();
//            for (User user : users) {
//                if (map.get(user).contains(email)) {
//                    matches[indexRow][indexColumn] = true;
//                    sameUsers.add(user);
//                }
//                indexRow++;
//            }
//            if (sameUsers.size() > 1){
//
//            }
//            indexRow = 0;
//            indexColumn++;
//        }
//
//    }


//    public Map<User, Set<String>> mergeIfCommonURL(Map<User, Set<String>> map) {
//        Map<User, Set<String>> newMap = new HashMap<>();
//        Map<String, List<User>> commonEmails = new HashMap<>();
//        Iterator<Set<String>> itOfSets = map.values().iterator();
//        List<User> sameUsersPerEmail = new ArrayList<>();
//        List<User> usersTotal = new ArrayList<>();
//        List<Integer> emailsPerUserAdded = new ArrayList<>();
//        int elementsAddedPerIteration = 0;
//        List<String> emails = new ArrayList<>();
//        for (Map.Entry<User, Set<String>> entry : map.entrySet()) {
//            emails.addAll(entry.getValue());
//            emailsPerUserAdded.add(entry.getValue().size());
////            elementsAddedPerIteration = emails.size() - elementsAddedPerIteration;
//            sameUsersPerEmail.add(emails.size() - 1, entry.getKey());
//            usersTotal.add(entry.getKey());
//        }
//        for (int i = 0; i < emails.size(); i++) {
//            if (sameUsersPerEmail.get(0) == null) {
//
//            }
//        }
//
//        Iterator<User> itOfUsers = sameUsersPerEmail.iterator();
//
//        for (int i = 0; i < emails.size(); i++) {
//            int index1 =
//            List<String> subListOfEmails = emails.subList(
//            commonEmails.put(emails.get(i),
//        }
//    }
}

//    public Map<User, Set<String>> merge(Map<User, Set<String>> map) {
//        Map<User, Set<String>> newMap = new HashMap<>();
//        List<Set<String>> listOfEmails = (List<Set<String>>) map.values();
//        for (int i = 0; i < map.size() - 1; i++) {
//            for (int k = i; k < map.size() - 1; k++) {
//
//                if (listOfEmails.get(i).retainAll(listOfEmails.get(i + 1))) {
//
//                }
//            }
//
//        }
//    }
//}
