package com.revoltcode;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    /*
     * N/B
     * where:
     * Vertex = Node
     * Edge = link || connection between nodes/vertex
     */
    private int numberOfVertexes;
    private LinkedList<Integer> adjacencyList[];

    public Graph(int numberOfVertexes) {
        this.numberOfVertexes = numberOfVertexes;
        adjacencyList = new LinkedList[numberOfVertexes];

        // initialize the lists with empty lists
        for(int i=0; i<numberOfVertexes; i++){
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int vertex, int edge){
        adjacencyList[vertex].add(edge);
    }

    public void breadthFirstSearch(int startingVertex){

        // keep track of nodes visited
        boolean[] visitedNodes = new boolean[this.numberOfVertexes];
        Queue<Integer> queue = new LinkedList<>();

        visitedNodes[startingVertex] = true;
        queue.add(startingVertex);

        while(queue.size() > 0){

            // get the current vertex visited
            int currentVertex = queue.poll();
            System.out.println("current node: "+currentVertex);

            // find neighbours of current visited vertex
            ListIterator<Integer> iterator = adjacencyList[currentVertex].listIterator();
            while(iterator.hasNext()){
                // get neighbour vertex
                int neighbourVertex = iterator.next();

                // check if neighbour vertex is already visited or not
                if(!visitedNodes[neighbourVertex]){

                    // add to queue and update as visited
                    queue.add(neighbourVertex);
                    visitedNodes[neighbourVertex] = true;
                }
            }
        }
    }

    public void depthFirstSearch(int startingVertex){

        boolean[] visitedNodes = new boolean[this.numberOfVertexes];
        Stack<Integer> stack = new Stack<>();

        stack.push(startingVertex);
        visitedNodes[startingVertex] = true;

        while(stack.size() > 0){
            int currentVertex = stack.pop();
            System.out.println("current node: "+currentVertex);

            ListIterator<Integer> iterator = adjacencyList[currentVertex].listIterator();

            while(iterator.hasNext()) {
                int neighbourVertex = iterator.next();

                if(!visitedNodes[neighbourVertex]) {
                    stack.push(neighbourVertex);
                    visitedNodes[neighbourVertex] = true;
                }
            }
        }
    }

    public void dFSRecursiveUtil(int startingVertex, boolean visiteNodes[]){
        visiteNodes[startingVertex] = true;
        System.out.println("current node: "+startingVertex);

        ListIterator<Integer> iterator = adjacencyList[startingVertex].listIterator();
        while(iterator.hasNext()){
            int neighbourVertex = iterator.next();

            if(!visiteNodes[neighbourVertex]){
                dFSRecursiveUtil(neighbourVertex, visiteNodes);
            }
        }
    }

    public void depthFirstSearchRecursively(int startingVertex){

        boolean[] visitedNodes = new boolean[this.numberOfVertexes];
        dFSRecursiveUtil(startingVertex, visitedNodes);
    }

    public boolean depthFirstSearchHasPath(int startingVertex, int destinationVertex){

        boolean[] visitedNodes = new boolean[this.numberOfVertexes];
        Stack<Integer> stack = new Stack<>();

        stack.push(startingVertex);
        visitedNodes[startingVertex] = true;

        while(stack.size() > 0){
            int currentVertex = stack.pop();

            if(currentVertex == destinationVertex) return true;

            ListIterator<Integer> iterator = adjacencyList[currentVertex].listIterator();

            while(iterator.hasNext()) {
                int neighbourVertex = iterator.next();

                if(!visitedNodes[neighbourVertex]) {
                    stack.push(neighbourVertex);
                    visitedNodes[neighbourVertex] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Breadth First Traversal");
        graph.breadthFirstSearch(0);

        System.out.println("Depth First Traversal");
        graph.depthFirstSearch(2);

        System.out.println("Depth First Traversal (Recursively)");
        graph.depthFirstSearchRecursively(2);

        System.out.println("Depth First Traversal (hasPath)");
        System.out.println(graph.depthFirstSearchHasPath(2,1));
    }
}
