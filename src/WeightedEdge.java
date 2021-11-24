import java.util.*;

public class WeightedEdge implements Comparable<WeightedEdge>
{
    int v1;
    int v2;
    double weight;
    public WeightedEdge() {
        v1 =0;
        v2=0;
        weight=0;

    }

    public WeightedEdge (int i, int j, double w) {

        v1=i;
        v2=j;
        weight=w;

    }

    public double getWeight(){
        return weight;

    }

    public int compareTo(WeightedEdge other){

        return (Double.valueOf(weight).compareTo(Double.valueOf(other.weight)));

    }

    public String toString(){

        return ("("+v1+","+v2+")"+weight);

    }



    public static void main(String[] args) {
        List<WeightedEdge> alw = new ArrayList<>();
        alw.add(new WeightedEdge(0,1,12));
        alw.add(new WeightedEdge(0,2,1));
        alw.add(new WeightedEdge(0,3,7));
        alw.add(new WeightedEdge(0,4,4));
        alw.add(new WeightedEdge(0,5,22));
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(alw);
        System.out.println(pq.peek());
        
       /* Comparator<WeightedEdge> crp = Comparator.comparingDouble(WeightedEdge::getWeight);
        Collections.sort(alw,crp);
        System.out.println(alw);
        
        crp= crp.reversed();
        Collections.sort(alw,crp);
        System.out.println(alw);*/













    }

}