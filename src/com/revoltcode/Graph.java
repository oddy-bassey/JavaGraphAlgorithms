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

    public static void main(String[] args){
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Breadth First Traversal");

        g.breadthFirstSearch(0);

        System.out.println("Depth First Traversal");

        g.depthFirstSearch(2);

        System.out.println("Depth First Traversal (Recursively)");

        g.depthFirstSearchRecursively(2);
    }
}
