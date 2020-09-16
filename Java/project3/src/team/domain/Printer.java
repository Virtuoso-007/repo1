package team.domain;

public class Printer implements Equipment {
    String name;
    String type;

    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getDescription() {
        return name+"("+type+")";
    }
}
