package CoreJava.q3;

class Super {
    int num = 20;

    public void display() {
        System.out.println("super class method");
    }
}

public class ThisUse extends Super {
    int num;

    public ThisUse(int num) {
        this.num = num;
    }

    @Override
    public void display() {
        System.out.println("display method");
    }

    public void show() {
        this.display();
        display();
        System.out.println(this.num);
        System.out.println(super.num);
    }

    public static void main(String[] args) {
        ThisUse o = new ThisUse(10);
        o.show();
    }
}
