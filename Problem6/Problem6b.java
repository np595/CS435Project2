import java.util.*;
import java.util.Random;

class Edge{
        public Node dest;
        public String path;
        public int weight;
        public Edge(Node d, String p, int w){
                this.dest = d;
                this.path = p;
                this.weight = w;
        }
}

class Node{
        public String data;
        public int color = -1;
        public List<Edge> neighbors;
        public Node(String d){
                this.data = d;
        }
}

class MazeGraph{
        List<Node> nodes;
}

class WeightedGraph{
        HashSet<Node> graph = new HashSet<Node>();
        public int n = 0;

        Node addNode(final String nodeVal){
                Node temp = new Node(nodeVal);
                temp.neighbors = new ArrayList<Edge>();
                graph.add(temp);
                n++;
                return temp;
        }

        void addWeightedEdge(final Node first, final Node second){
                Random rand = new Random();
                int n = rand.nextInt(10 + 1);
                first.neighbors.add(new Edge(second, second.data, n));
        }

        void removeWeightedEdge(final Node first, final Node second){
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

class TreadmillMazeSolver{
        ArrayList<Node> astar(final WeightedGraph graph){
                HashSet<Node> temp = graph.getAllNodes();
                ArrayList<Node> nodes = new ArrayList<Node>(temp);
                for(int i = 0; i < nodes.size(); i++){
                //This is important since if there's a node disconnected, it
                //needs to be colored
                        if(nodes.get(i).color == -1)
                                nodes.get(i).color = 0;
                        for(int j = 0; j < nodes.get(i).neighbors.size(); j++){
                                if(nodes.get(i).color <= nodes.get(i).neighbors.get(j).dest.color){
                                        nodes.get(i).color = nodes.get(i).neighbors.get(j).dest.color + 1;
                                }
                        }

                }
                for(int k = 0; k < nodes.size(); k++){
                        System.out.println(nodes.get(k) + " " + nodes.get(k).color);
                }
                return nodes;
        }
}

class Main{
        WeightedGraph createRandomCompleteWeightedGraph(Node[] nodes, final int n){
                TreadmillMazeSolver tread = new TreadmillMazeSolver();
                Random rand = new Random();
                WeightedGraph weiGraph = new WeightedGraph();
                ArrayList<Node> test = new ArrayList<Node>();
                //String alp = "abcdefghijklmnopqrstuvxyz";
                //int size = alp.length();
                for(int i = 0; i < n; i++){
                        //char tempS = alp.charAt(rand.nextInt(size));
                        //String temp = String.valueOf(tempS);
                        int tempS = rand.nextInt(1000);
                        String temp = Integer.toString(tempS);
                        nodes[i] = weiGraph.addNode(temp);
                }
                for(int k = 0; k < (n*2); k++){
                        int firstNode = rand.nextInt(nodes.length);
                        int secondNode = rand.nextInt(nodes.length);
                        weiGraph.addWeightedEdge(nodes[firstNode], nodes[secondNode]);
                }
                test = tread.astar(weiGraph);
                return weiGraph;
        }

        public static void main(String[] args){
                int nodeSize = 5;
                Node[] nodes = new Node[nodeSize];
                Random rand = new Random();
                WeightedGraph weiGraph = new WeightedGraph();
                Main test = new Main();

                weiGraph = test.createRandomCompleteWeightedGraph(nodes, nodeSize);
        }
}
