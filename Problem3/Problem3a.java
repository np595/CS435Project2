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
        public static void main(String[] args){
                int nodeSize = 5;
                Node[] nodes = new Node[nodeSize];
                Random rand = new Random();
                Graph graphing = new Graph();
                Main test = new Main();
                nodes[0] = graphing.addNode("a");
                nodes[1] = graphing.addNode("b");
                nodes[2] = graphing.addNode("c");
                nodes[3] = graphing.addNode("d");
                nodes[4] = graphing.addNode("e");

                for(int i = 0; i < 5; i++){
                        int nm = rand.nextInt(5);
                        int m = rand.nextInt(5);
                        graphing.addUndirectedEdge(nodes[nm], nodes[m]);
                }
                graphing.addUndirectedEdge(nodes[2], nodes[4]);
                System.out.println(nodes[0].data + " " + nodes[0].neighbors.get(0).dest.data);
                HashSet<Node> testing = graphing.getAllNodes();
                System.out.println(nodes[2].data + "\n " + nodes[2].neighbors);
                graphing.removeUndirectedEdge(nodes[2], nodes[4]);
                System.out.println(nodes[2].data + "\n " + nodes[2].neighbors);
        }
}
