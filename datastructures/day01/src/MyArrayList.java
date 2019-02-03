public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(N)

    public MyArrayList() {
        elems = new Cow[]{};
    }

    // Runtime: O(N)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
    }

    // Runtime: O(N) originally
    public void add(Cow c) {
//      Runtime: O(N) originally
        for (int iter = 0; iter < elems.length; iter++) {
            if(elems[iter] == null){
                elems[iter] = c;
                break;
            }
        }

//        int cycles = elems.length; // Original size of array
//
//        for (int index = 0; index < cycles; index++) {
//            if (size >= elems.length) {
//                Cow[] tmp = new Cow[(int) Math.round(elems.length * 2.0)];
//                System.arraycopy(elems, 0, tmp, 0, tmp.length);
//                elems = tmp;
//            }
//            else if ()
//            if((index < elems.length) && (elems[index] == null)) { // Index is within array; space is available
//                elems[index] = c; // Set blank cell to cow
//
//                if (index < (int) Math.round(0.25 * elems.length)) { // Reduce array size if under 25% of array is used
//                    System.out.println(index);
//                    System.out.println(elems.length);
//                    Cow[] tmp = new Cow[(int) Math.round(elems.length / 2.0)];
//                    System.arraycopy(elems, 0, tmp, 0, tmp.length);
//                    elems = tmp;
//                }
//                break;
//            } else { // Not enough space available
//                Cow[] tmp = new Cow[elems.length * 2]; // Alloc array of original elems size squared
//                System.arraycopy(elems, 0, tmp, 0, cycles); // Transfer to larger array
//                elems = tmp;


//                if (elems[index] == null) { // Case where "natural" array overflow occurred
//                    elems[index] = c;
//                } else {
//                    elems[index + 1] = c;
//                }

//                System.out.print(index);
//                System.out.print(", ");
//                System.out.print(cycles);
//                System.out.print(", ");
//                System.out.println(tmp.length);
//            }
//        }
    }

    // Runtime: O(N)
    public int size() {
        for (int index = 0; index < elems.length; index++) {
            if(elems[index] == null){
                return index;
            }
        }
        return elems.length;
    }

    // Runtime: O(1)
    public Cow get(int index) throws IndexOutOfBoundsException {
        if((elems[index] != null) && (index < elems.length)){
            return elems[index];
        }
        throw new IndexOutOfBoundsException();
    }

    // Runtime: O(N)
    public Cow remove(int index) {
        if((index > (elems.length - 1)) || (elems[index] == null)){
            throw new IndexOutOfBoundsException();
        } else {
            Cow[] tmp = new Cow[elems.length - 1];
            Cow lostCow = elems[index];
            for (int i = 0; i < (elems.length); i++){
                if (i < index) {
                    tmp[i] = elems[i];
                } else if (i > index) {
                    tmp[i-1] = elems[i];
                }
            }
            elems = tmp;
            return lostCow;
        }
    }

    // Runtime: O(N)
    public void add(int index, Cow c) {
        if (index > (elems.length + 1)) {
            throw new IndexOutOfBoundsException();
        }
        Cow[] tmp = new Cow[elems.length + 1];
        for (int i = 0; i < (elems.length); i++) {
            if (i < index) {
                tmp[i] = elems[i];
            } else {
                tmp[i+1] = elems[i];
            }
        }
        tmp[index] = c;
        elems = tmp;
    }
}