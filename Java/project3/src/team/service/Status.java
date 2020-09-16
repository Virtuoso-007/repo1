package team.service;

//public class Status {
////    FREE-空闲
////    BUSY-已加入开发团队
////    VOCATION-正在休假
//    private final String NAME;
//    private Status(String name) {
//        this.NAME = name;
//    }
//    public static final Status FREE = new Status("FREE");
//    public static final Status VOCATION = new Status("VOCATION");
//    public static final Status BUSY = new Status("BUSY");
//    public String getNAME() {
//        return NAME;
//    }
//    @Override
//    public String toString() {
//        return NAME;
//    }
//
//}
public enum Status{
    FREE,BUSY,VOCATION;
}