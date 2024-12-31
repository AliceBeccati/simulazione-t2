package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers{

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new Cursor<>(){
            private X currentElement = list.getFirst();
            private int index = 0;

            @Override
            public X getElement() {
                return this.currentElement;
            }

            @Override
            public boolean advance() {
                try{
                    index++;
                    this.currentElement = list.get(index);
                    return true;
                }
                catch(Exception e){
                    return false;
                }
            }

        };
    }

    @Override
    public Cursor<Integer> naturals() {
        return new Cursor<Integer>() {
            private int currentValue = 0;
            @Override
            public Integer getElement() {
                return this.currentValue;
            }

            @Override
            public boolean advance() {
                currentValue++;
                return true;
            }
            
        };
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return new Cursor<X>(){
            private int countElem = 1;
            @Override
            public X getElement() {
                return input.getElement();
            }

            @Override
            public boolean advance() {
                countElem++;
                return this.countElem <= max && input.advance();
            }

        };
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        do{
            consumer.accept(input.getElement());
        }while(input.advance());
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> l = new ArrayList<>();
        l.add(input.getElement());
        for(int i = 0; i < max-1 && input.advance(); i++){
            l.add(input.getElement());
        }
        return l;
    }
    
}
