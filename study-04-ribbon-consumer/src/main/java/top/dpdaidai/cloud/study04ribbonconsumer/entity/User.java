package top.dpdaidai.cloud.study04ribbonconsumer.entity;

/**
 * @Author chenpantao
 * @Date 4/14/21 1:58 PM
 * @Version 1.0
 */
public class User {

    private Long id;

    private String name;


    public User() {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
