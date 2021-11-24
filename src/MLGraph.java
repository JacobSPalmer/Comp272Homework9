import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;

public class MLGraph {

    int[] findSet;
    ArrayList<MyLinkedList<Integer>> vertexSets;
    static int totalChanges;
    int[] setArr;
    int[] height;
    int count;

    int numVertices;
    int numEdges;
    ArrayList<WeightedEdge> edgeSet;

    public MLGraph() {
        this.numVertices = 0;
        this.numEdges = 0;
        this.edgeSet = new ArrayList<>();
    }

    public MLGraph(int vertexCount) {
        this.numVertices = vertexCount;
        this.edgeSet = new ArrayList<>();
    }

    public MLGraph(int vertexCount, ArrayList<WeightedEdge> edgeSetX) {
        this.numVertices = vertexCount;
        this.edgeSet = edgeSetX;
        this.numEdges = edgeSetX.size();
        totalChanges = 0;
        count = 0;
        findSet = new int[numVertices];
        setArr = new int[numVertices];
        height = new int[numVertices];
        vertexSets = new ArrayList<>(numVertices);

        for(int i=0;i<numVertices; i++){
            findSet[i] = i;
            setArr[i] = i;

            vertexSets.add(new MyLinkedList<Integer>());
            vertexSets.get(i).addFirst(i);
        }
    }

    public static MLGraph readAndStoreGraph(String fileName, String weightFileName) {
        int maxV = 0;
        ArrayList<WeightedEdge> edgeSet = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(fileName));
            String s;
            Scanner wg = new Scanner(new File(weightFileName));
            String w;

            // graph.add(new ArrayList<Integer>());
            while (sc.hasNextLine()) {
                s = sc.nextLine();
                w = wg.nextLine();
                if (s.isEmpty()) continue;
                String[] line = s.split("\\s");
                // String[] line= s.split(",");
                int v1 = Integer.parseInt(line[0]);
                int v2 = Integer.parseInt(line[1]);
                double weight = Double.parseDouble(w);
                int p = Math.max(v1, v2);
                if (p > maxV) maxV = p;
                edgeSet.add(new WeightedEdge(v1, v2, weight));
            }
        } catch (FileNotFoundException e) {
        }

        MLGraph gr = new MLGraph(maxV + 1, edgeSet);
//numEdges = edgeSet.size();
        return gr;
    }

    public double getWeight(int u, int v) {
        for(WeightedEdge e: edgeSet){
            if((e.v1 == u && e.v2 == v) || (e.v1 == v && e.v2 == u)){
                return e.getWeight();
            }
            else{
                continue;
            }
        }
        return Double.MAX_VALUE;
    }

//    class VertexPair{
//        int u;
//        int v;
//
//        VertexPair(int u, int v){
//            this.u = u;
//            this.v = v;
//        }
//
//        public boolean
//    }


//    public MinSpanTree priorityQueueMinSpanTree(){
//        int n = this.numVertices;
//
//        HashSet<Integer> vertexSetPQ = new HashSet<>();
//        PriorityQueue<WeightedEdge> pqWEdges = new PriorityQueue<>(this.edgeSet);
//
//        double weightTotal = 0.0;
//        ArrayList<WeightedEdge> minSpanTreeEdges = new ArrayList<>();
//
//        int treeEdges = 0;
//        while(minSpanTreeEdges.size() < n - 1){
//            this.count++;
//            WeightedEdge cEdge = pqWEdges.poll();
//            if(cEdge != null) {
//                int v1 = cEdge.v1;
//                int v2 = cEdge.v2;
//                double weight = cEdge.getWeight();
//
//                if(((vertexSetPQ.contains(v1) && !vertexSetPQ.contains(v2)) || (!vertexSetPQ.contains(v1) && vertexSetPQ.contains(v2)))){
//                    continue;
//                } else {
//                    vertexSetPQ.add(v1);
//                    vertexSetPQ.add(v2);
//                    minSpanTreeEdges.add(cEdge);
//                    weightTotal += weight;
//                    treeEdges++;
//                }
//            }
//            if(count%10000 == 0){
//                int c = count + treeEdges;
//            }
//        }
//        MinSpanTree mst = new MinSpanTree(weightTotal, minSpanTreeEdges);
//        return mst;
//    }

//    public MinSpanTree priorityQueueMinSpanTree(){
//        int n = this.numVertices;
//
//        ArrayList<IntPair> vertexSetPQ = new ArrayList<>();
//        PriorityQueue<WeightedEdge> pqWEdges = new PriorityQueue<>(this.edgeSet);
//
//        double weightTotal = 0.0;
//        ArrayList<WeightedEdge> minSpanTreeEdges = new ArrayList<>();
//
//        int treeEdges = 0;
//        while(treeEdges < n - 1){
//            this.count++;
//            WeightedEdge cEdge = pqWEdges.poll();
//            if(cEdge != null) {
//                int v1 = cEdge.v1;
//                int v2 = cEdge.v2;
//                double weight = cEdge.getWeight();
//
//                IntPair temp = new IntPair(v1, v2);
//
//                if (!(vertexSetPQ.contains(temp))) {
//                    vertexSetPQ.add(temp);
//                    minSpanTreeEdges.add(cEdge);
//                    weightTotal += weight;
//                    treeEdges++;
//                } else {
//                    continue;
//                }
//            }
//            if(count%1000 == 0){
//                int c = count + treeEdges;
//            }
//        }
//
//        MinSpanTree mst = new MinSpanTree(weightTotal, minSpanTreeEdges);
//        return mst;
//    }

    //Find - Merge Methods

    public int findComponent(int u) {
        return findSet[u];
    }

    public int getComponentSize(int u){
        return vertexSets.get(u).size();
    }

    public void changeComponent(MyLinkedList<Integer> my, int max){
        totalChanges+=my.size();
        if (!my.isEmpty()){
            Node<Integer> temp = my.getFirst();
            do {
                findSet[temp.getInfo()]=max;
                temp = temp.getNext();
            } while (temp!=null);
        }
        else {
            System.out.println("empty list ...");
            throw new NoSuchElementException();
        }

    }

    public void mergeComponents (int u, int v) {
        int p = findComponent(u);
        int q = findComponent(v);
        int min=0;
        int max=0;
        if (p!=q) {
            if (getComponentSize(p)>getComponentSize(q)){
                min =q;
                max=p;
            }
            else {
                min=p;
                max=q;
            }
            // merge min with the max
            MyLinkedList<Integer> myl = vertexSets.get(min);
            vertexSets.get(max).appendList(myl);
            changeComponent(myl,max);

        }
    }

    public MinSpanTree kruskalMinSpanTree(){
        int n = this.numVertices;
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(this.edgeSet);

        ArrayList<WeightedEdge> treeEdgeList = new ArrayList<WeightedEdge>();
        double totalWeights = 0.0;

        int treeEdges = 0;

        while(treeEdgeList.size() < n - 1){
            this.count++;
            WeightedEdge edge = queue.poll();
            if(edge != null){
                int p1 = findComponent(edge.v1);
                int p2 = findComponent(edge.v2);

                if(p1 != p2){
                    treeEdgeList.add(edge);
                    totalWeights += edge.getWeight();
                    mergeComponents(p1, p2);
                    treeEdges++;
                }
            }
        }
        return new MinSpanTree(totalWeights, treeEdgeList);
    }

    //Path Compression Method

    class subset {
        int parent;
        int rank;
    };

    public int findSubset(subset subsets[], int u){
        if(subsets[u].parent != u){
            subsets[u].parent = findSubset(subsets, subsets[u].parent);
        }
        return subsets[u].parent;
    }

    public void unionSet(subset subsets[], int u, int v){
        int u_root = findSubset(subsets, u);
        int v_root = findSubset(subsets, v);

        if(subsets[u_root].rank < subsets[v_root].rank){
            subsets[u_root].parent = v_root;
        }
        else if(subsets[v_root].rank < subsets[u_root].rank){
            subsets[v_root].parent = u_root;
        }
        else{
            subsets[v_root].parent = u_root;
            subsets[u_root].rank++;
        }
    }

    public MinSpanTree kruskalMinSpanTreePathCompression() {
        int n = this.numVertices;
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(this.edgeSet);

        ArrayList<WeightedEdge> treeEdgeList = new ArrayList<WeightedEdge>();
        double totalWeights = 0.0;

        int treeEdges = 0;

        subset subsets[] = new subset[n];
        for (int i = 0; i < n; ++i) {
            subsets[i] = new subset();
        }

        for (int v = 0; v < n; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while(treeEdgeList.size() < n - 1){
            this.count++;
            WeightedEdge edge = queue.poll();
            if(edge != null){

                int p1 = findSubset(subsets, edge.v1);
                int p2 = findSubset(subsets, edge.v2);

                if(p1 != p2){
                    treeEdgeList.add(edge);
                    totalWeights += edge.getWeight();
                    unionSet(subsets, p1, p2);
                    treeEdges++;
                }
            }
        }
        System.out.println("Tree edges: " + treeEdges);
        return new MinSpanTree(totalWeights, treeEdgeList);

    }


    public static void main(String[] args) {
        String file = "artist_edges.txt";
        String fileWeight = "Weights.txt";
        MLGraph gr1 = readAndStoreGraph(file, fileWeight);

        System.out.println("#Pre-Checks#");
        System.out.println("# of vertices: " + gr1.numVertices);
        System.out.println("# of edges: " + gr1.numEdges);
        System.out.println("Weight at 0	9667 is: " + gr1.getWeight(0, 9667));
        System.out.println("edge weights: " + gr1.edgeSet.get(11));

        System.out.println("Weight at 50442 50493 is: " + gr1.getWeight(50442, 50493));
        System.out.println("edge weights: " + gr1.edgeSet.get(819305) + "\n");

        System.out.println("#Kruskal Min Span Tree w/o Path Compression#");
        System.out.println("\nKruskalMinSpanTree Results:");
        long t1 = System.currentTimeMillis();
        MinSpanTree mst1 = gr1.kruskalMinSpanTree();
        long t2 = System.currentTimeMillis();
        System.out.println("Time to complete: " + (t2-t1)*1.0/1000);
        System.out.println("Edges in consideration: " + gr1.count);
        System.out.println("Min Edge Weight: " + mst1.totalWeight);
        System.out.println("Edges in Min Span Tree: " + mst1.minEdgeList.size() + "\n");

        System.out.println("#Kruskal Min Span Tree w/ Path Compression#");
        MLGraph gr2 = readAndStoreGraph(file, fileWeight);
        System.out.println("\nKruskalMinSpanTree w/ Path Compression Results:");
        long t3 = System.currentTimeMillis();
        MinSpanTree mst2 = gr2.kruskalMinSpanTreePathCompression();
        long t4 = System.currentTimeMillis();
        System.out.println("Time to complete: " + (t4-t3)*1.0/1000);
        System.out.println("Edges in consideration: " + gr2.count);
        System.out.println("Min Edge Weight: " + mst2.totalWeight);
        System.out.println("Edges in Min Span Tree: " + mst2.minEdgeList.size() + "\n");

//        MLGraph gr3 = readAndStoreGraph(file, fileWeight);
//        System.out.println("\nKruskalMinSpanTree w/ Path Compression Results:");
//        long t5 = System.currentTimeMillis();
//        MinSpanTree mst3 = gr3.priorityQueueMinSpanTree();
//        long t6 = System.currentTimeMillis();
//        System.out.println("Time to complete: " + (t6-t5)*1.0/1000);
//        System.out.println("Edges in consideration: " + gr3.count);
//        System.out.println("Min Edge Weight: " + mst3.totalWeight);
//        System.out.println("Edges in Min Span Tree: " + mst3.minEdgeList.size() + "\n");

    }
}
