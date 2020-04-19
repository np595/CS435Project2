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
        public boolean visited;
        public Node(String d){
                this.data = d;
                this.visited = false;
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
                return allNode;
        }
}

class TopSort{
        ArrayList<Node> mDFS(final DirectedGraph graph){
                HashSet<Node> nodes = graph.getAllNodes();
                ArrayList<Node> node = new ArrayList<Node>(nodes);
                //In a sense, yes, HashSet was easier to use overall and would've saved more time than constructing a brand new list.
                //However, while this was occurring I was constantly using lists and wanted to continue to use them over sets.
                //It was simply a decision I made back then which I will debate to change before the due date to transform all the nodes
                //from the hashset instead of the arraylist.
                final Stack<Node> stack = new Stack<Node>();

                for(int i = 0; i < node.size(); i++){
                        if(node.get(i).visited != true)
                                modifiedDfsHelper(node.get(i), stack);
                }
                //When using StringBuilder, it creates a stringbuilder to construct a variety of variables into it,
                //in thise case I'm using the nodes values from the stack, to form into the stringbuilder to then convert it into a 
                //string to show off the end output after mDFS has completed and shows the path it took.
                final StringBuilder string = new StringBuilder("Output of Modified DFS: ");
                while(!stack.empty()){
                        final Node curr = (Node) stack.pop();
                        string.append("->"+curr.data);
                }
                //No, I didn't override the toString method, I was able to use the toString method in this way since it was a part of
                //the StringBuilder class to make it an easy way to transfer over the stringbuilder into a string by the developers.
                System.out.println(string.toString());
                return node;
        }

        void modifiedDfsHelper(final Node node, Stack stack){
                node.visited = true;
                for(int i = 0; i < node.neighbors.size(); i++){
                        if(node.neighbors.get(i).dest.visited != true)
                                modifiedDfsHelper(node.neighbors.get(i).dest, stack);
                }
                stack.push(node);
        }
}

class Main{
        DirectedGraph createRandomDAGIter(Node[] nodes, final int n){
                ArrayList<Node> testing = new ArrayList<Node>();
                TopSort top = new TopSort();
                Random rand = new Random();
                DirectedGraph dirGraph = new DirectedGraph();
                //String alp = "abcdefghijklmnopqrstuvxyz";
                //int size = alp.length();
                for(int i = 0; i < n; i++){
                        //char tempS = alp.charAt(rand.nextInt(size));
                        //String temp = String.valueOf(tempS);
                        int tempS = rand.nextInt(1000);
                        String temp = Integer.toString(tempS);
                        nodes[i] = dirGraph.addNode(temp);
                }
                for(int k = 0; k < (n*2); k++){
                        int firstNode = rand.nextInt(nodes.length);
                        int secondNode = rand.nextInt(nodes.length);
                        dirGraph.addDirectedEdge(nodes[firstNode], nodes[secondNode]);
                }

                testing = top.mDFS(dirGraph);

                return dirGraph;
        }

        public static void main(String[] args){
                int nodeSize = 5;
                Node[] nodes = new Node[nodeSize];
                Random rand = new Random();
                DirectedGraph dirGraph = new DirectedGraph();
                Main test = new Main();

                dirGraph = test.createRandomDAGIter(nodes, nodeSize);
        }
}
