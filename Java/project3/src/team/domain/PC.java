package team.domain;

public class PC implements Equipment{
    String model;
    String display;

    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }

    @Override
    public String getDescription() {
        return model+"("+display+")";
    }
}
