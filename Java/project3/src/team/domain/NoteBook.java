package team.domain;

public class NoteBook implements Equipment {
    String model;
    double price;

    public NoteBook(String model, double price) {
        this.model = model;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return model+"("+price+")";
    }
}
