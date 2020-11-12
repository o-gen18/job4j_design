package nonblocking.nonblockingcache;

public class Base {
    private int id;
    private int version;
    private String name;

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public Base(int version, String name) {
        this.version = version;
        this.name = name;
    }

    public static Base copyOf(Base base, int id) {
        return new Base(id, base.getVersion(), base.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
