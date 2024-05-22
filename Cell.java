
// 1 = digit 
// 0 = empty
// -1 = NO VAL SUPPSED TO BE HERE error!!
// -2 = X
// -3 = hSum
// -4 = vSum
// -5 = hvSum

public class Cell {
private int type;
private int val1, val2; 

    //informational cell, has a \ somewhere
    public Cell(int t, int v)
    {
        type = t;
        val1 = v;
        val2 = -1;
    }

    //digit or 0 or X
    public Cell(int v)
    {
        if (v == 0){
            type = 0; 
            val1 = 0;
        }

        else if (v > 0)
        {
            type = 1;
            val1 = v;
        }
        
        else{
            type = v;
            val1 = 0;
        }
        
        val2 = -1;
    }
    
    //for sum (two types)
    public Cell(int t, int v1, int v2)
    {
        type = t;
        val1 = v1;
        val2 = v2;

    }

    public Cell()
    {
        type = -1;
        val1 = -1;
        val2 = -1; 


    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    

}
