package a02b.e2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogicsImpl implements Logics{
    public int goPressed;
    public Map<Pair<Integer,Integer>,Boolean> map;

    public LogicsImpl(Collection<Pair<Integer,Integer>> cells){
        this.map = new LinkedHashMap<>();
        this.goPressed = 0;
        cells.forEach(c -> map.put(c, false));
    }

    @Override
    public boolean quit() {
        if(this.goPressed == 2){
            this.goPressed = 0;
            return true;
        }
        return false;
    }

    private List<Pair<Integer, Integer>> selectDiago(int diag, int size) {
        List<Pair<Integer, Integer>> l = new ArrayList<>();
        if (diag < size) {
            for (int i = 0; i <= diag; i++) {
                l.add(new Pair<>(i, size - diag - 1 + i));
            }
        } else {
            for (int i = ((diag+1) % size); i < size; i++) {
                l.add(new Pair<>(i, i-((diag+1) % size)));
            }
        }
        return l;
    }
    

    @Override
    public List<Pair<Integer,Integer>> check(int size) {
        for(int i = 0; i < (size*2)-1; i++){
            List<Pair<Integer,Integer>> l = selectDiago(i, size);
            if(l.stream().filter(p -> map.get(p) == true).count() == 3){
                return l;
            }
        }
        return null;
    }

    @Override
    public void setPressedGo() {
        this.goPressed++;
    }

    @Override
    public void setSelected(Pair<Integer,Integer> p) {
        map.entrySet().stream()
                .filter(e -> e.getKey().equals(p))
                .forEach(e -> e.setValue(true));
    }

    @Override
    public void restart() {
        map.entrySet().stream().forEach(e -> e.setValue(false));
    }
    
}
