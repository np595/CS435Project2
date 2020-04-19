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
        int currNode = 0;
        ArrayList<Node> visited = new ArrayList<Node>();

        ArrayList<Node> BFTRec(final Graph graph){
                HashSet<Node> node = graph.getAllNodes();
                ArrayList<Node> nodes = new ArrayList<Node>(node);
                Node curr = nodes.get(currNode);
                //Ensures that the start node is added as visited
                if(!visited.contains(curr)){
                        visited.add(curr);
                }
                //Visits all neighbors of the current node in the set
                for(int i = 0; i < curr.neighbors.size(); i++){
                        if(!visited.contains(curr.neighbors.get(i).dest)){
                                visited.add(curr.neighbors.get(i).dest);
                        }
                }
                if(currNode != (nodes.size() - 1)){
                        currNode++;
                        ArrayList<Node> visitNe = BFTRec(graph);
                }
                return visited;
        }

        ArrayList<Node> BFT(final Graph graph){
                ArrayList<Node> visited = new ArrayList<Node>();
                ArrayList<Node> BFTc = new ArrayList<Node>();
                BFTc = BFTRec(graph);
                System.out.println(BFTc);
                return BFTc;
        }

        Graph createRandomUnweightedGraphIter(Node[] nodes, int n){
                Random rand = new Random();
                Graph graphing = new Graph();
                String alp = "abcdefghijklmnopqrstuvxyz";
                int size = alp.length();
                for(int i = 0; i < n; i++){
                        char tempS = alp.charAt(rand.nextInt(size));
                        String temp = String.valueOf(tempS);
                        nodes[i] = graphing.addNode(temp);
                }
                for(int k = 0; k < n; k++){
                        int nm = rand.nextInt(nodes.length);
                        int m = rand.nextInt(nodes.length);
                        graphing.addUndirectedEdge(nodes[nm], nodes[m]);
                }

                ArrayList<Node> check = new ArrayList<Node>();
                check = BFT(graphing);

                return graphing;
        }

        public static void main(String[] args){
                int nodeSize = 5;
                Node[] nodes = new Node[nodeSize];
                Random rand = new Random();
                Graph graphing = new Graph();
                Main test = new Main();
                graphing = test.createRandomUnweightedGraphIter(nodes, nodeSize);
        }
}
