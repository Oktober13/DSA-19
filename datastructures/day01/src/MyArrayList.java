public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(N)
    public MyArrayList() {
        elems = new Cow[]{};
    }

    // TODO: Runtime: O(N)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(?)
    public void add(Cow c) {
        for(int iter = 0; iter < elems.length; iter++){
            if(elems[iter] == null){
                elems[iter] = c;
                break;
            }
        }
    }

    // TODO: Runtime: O(?)
    public int size() {
        // TODO
        return -1;
    }

    // TODO: Runtime: O(?)
    public Cow get(int index) {
        // TODO
        return null;
    }

    // TODO: Runtime: O(?)
    public Cow remove(int index) {
        // TODO
        return null;
    }

    // TODO: Runtime: O(?)
    public void add(int index, Cow c) {
        // TODO
    }

    public static void main(){
        System.out.println("Hello World!");
    }
}