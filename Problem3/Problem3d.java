import java.util.*;
import java.util.Random;

class Edge{
        public Node dest;
        public String weight;
        public Edge(Node d, String w){
                this.dest = d;
                this.weight = w;
        }
}

class Node{
        public String data;
        public List<Edge> neighbors;
        public Node(String d){
                this.data = d;
        }
}

class MazeGraph{
        List<Node> nodes;
}

class Graph{
        //usage of a int outside of the function so it remains the same per run
        //of the addNode. That way each node made has a new value. I'm giving
        //each node an empty list so add and remove will work as intended to
        //make edges between each
        HashSet<Node> graph = new HashSet<Node>();
        public int n = 0;
        Node addNode(final String nodeVal){
                Node temp = new Node(nodeVal);
                temp.neighbors = new ArrayList<Edge>();
                graph.add(temp);
                n++;
                return temp;
        }
        //Adds a new edge to the list each time this function is called since
        //.of is registering a new list each time of those elements instead of
        //adding to the list. Made both neighbors so they can access each other
        void addUndirectedEdge(final Node first, final Node second){
                first.neighbors.add(new Edge(second, second.data));
                second.neighbors.add(new Edge(first, first.data));
        }

        void removeUndirectedEdge(final Node first, final Node second){
                boolean check = false;
                for(int i = 0; i < first.neighbors.size(); i++){
                        if(first.neighbors.get(i).dest == second){
                                first.neighbors.remove(i);
                                check = true;
                                break;
                        }
                }
                for(int j = 0; j < second.neighbors.size(); j++){
                        if(second.neighbors.get(j).dest == first){
                                second.neighbors.remove(j);
                                break;
                        }
                }
                if(check == false){
                        System.out.println(first.data + " isn't connected to " + second.data);
                }
        }

        HashSet<Node> getAllNodes(){
                HashSet<Node> allNode = graph;
                System.out.println(allNode);
                return allNode;
        }
}

class Main{

        ArrayList<Node> DFSRec(final Node start, final Node end, int size, ArrayList<Node> visited){
                ArrayList<Node> check = new ArrayList<Node>();
                visited.add(start);
                for(int j = 0; j < start.neighbors.size(); j++){
                        if(start.data != end.data){
                                if(!visited.contains(start.neighbors.get(j).dest)){
                                        check = DFSRec(start.neighbors.get(j).dest, end, size, visited);
                                }
                        }
                }
                return visited;
        }

        ArrayList<Node> DFS(final Node start, final Node end, int size){
                ArrayList<Node> DFSc = new ArrayList<Node>();
                DFSc = DFSRec(start, end, size, visited);
                if(!visited.contains(end)){
                        System.out.println("Node " + end + " was not found.");
                        System.out.println(visited);
                }
                else{
                        System.out.println("Node " + end + " found");
                        System.out.println(visited);
                }
                return DFSc;
        }

        Graph createRandomUnweightedGraphIter(Node[] nodes, int n){
                Random rand = new Random();
                Graph undirGraph = new Graph();
                //String alp = "abcdefghijklmnopqrstuvxyz";
                //int size = alp.length();
                for(int i = 0; i < n; i++){
                        //char tempS = alp.charAt(rand.nextInt(size));
                        //String temp = String.valueOf(tempS);
                        int tempS = rand.nextInt(1000);
                        String temp = Integer.toString(tempS);
                        nodes[i] = undirGraph.addNode(temp);
                }
                for(int k = 0; k < n; k++){
                        int firstNode = rand.nextInt(nodes.length);
                        int secondNode = rand.nextInt(nodes.length);
                        undirGraph.addUndirectedEdge(nodes[firstNode], nodes[secondNode]);
                }

                ArrayList<Node> check = new ArrayList<Node>();
                check = DFS(nodes[0], nodes[3], n);

                return graphing;
        }
        
        public static void main(String[] args){
                int nodeSize = 5;
                Node[] nodes = new Node[nodeSize];
                Random rand = new Random();
                Graph undirGraph = new Graph();
                Main test = new Main();
                undirGraph = test.createRandomUnweightedGraphIter(nodes, nodeSize);

        }
}

