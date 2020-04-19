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

class DirectedGraph{
        HashSet<Node> graph = new HashSet<Node>();
        public int n = 0;

        Node addNode(final String nodeVal){
                Node temp = new Node(nodeVal);
                temp.neighbors = new ArrayList<Edge>();
                graph.add(temp);
                n++;
                return temp;
        }

        void addDirectedEdge(final Node first, final Node second){
                first.neighbors.add(new Edge(second, second.data));
        }

        void removeDirectedEdge(final Node first, final Node second){
                boolean check = false;
                for(int i = 0; i < first.neighbors.size(); i++){
                        if(first.neighbors.get(i).dest == second){
                                first.neighbors.remove(i);
                                check = true;
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
                DirectedGraph dirGraph = new DirectedGraph();
                Main test = new Main();

                nodes[0] = dirGraph.addNode("a");
                nodes[1] = dirGraph.addNode("b");
                nodes[2] = dirGraph.addNode("c");
                nodes[3] = dirGraph.addNode("d");
                nodes[4] = dirGraph.addNode("e");

                for(int i = 0; i < 10; i++){
                        int nm = rand.nextInt(5);
                        int m = rand.nextInt(5);
                        dirGraph.addDirectedEdge(nodes[nm], nodes[m]);
                }
                dirGraph.addDirectedEdge(nodes[2], nodes[4]);
                System.out.println(nodes[0].data + " " + nodes[0].neighbors.get(0).dest.data);
                HashSet<Node> testing = dirGraph.getAllNodes();
                System.out.println(nodes[2].data + "\n" + nodes[2].neighbors);
                dirGraph.removeDirectedEdge(nodes[2], nodes[4]);
                System.out.println(nodes[2].data + "\n" + nodes[2].neighbors);
        }
}
