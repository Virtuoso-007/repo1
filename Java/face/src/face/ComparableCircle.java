package face;

public class ComparableCircle extends Circle implements CompareObject {
    @Override
    public int compareTo(Object o) {
        if(o instanceof ComparableCircle){
            ComparableCircle c1=(ComparableCircle)o;
            if(this.redius>c1.redius){
                return 1;
            }
            else return -1;
        }
        else return 0;
    }
}
