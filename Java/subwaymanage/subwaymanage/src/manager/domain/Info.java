package manager.domain;

public class Info {
    private String id;
    private String name;
    private String money;

    public Info() {
    }

    public Info(String id, String name, String money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return money;
    }

    public void setAge(String money) {
        this.money = money;
    }
}
