package a02b.e2;

import java.util.List;

public interface  Logics {
    public boolean quit();
    public void setPressedGo();
    public void setSelected(Pair<Integer,Integer> p);
    public List<Pair<Integer,Integer>> check(int size);
    public void restart(); 
}
