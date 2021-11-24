import java.util.ArrayList;

public class MinSpanTree {

    double totalWeight;
    ArrayList<WeightedEdge> minEdgeList;

    public MinSpanTree(){
        this.totalWeight = 0.0;
        this.minEdgeList = new ArrayList<>();
    }

    public MinSpanTree(double weight, ArrayList<WeightedEdge> minEdgeList){
        this.totalWeight = weight;
        this.minEdgeList = minEdgeList;
    }
}
