package cn.novate.realmdemo;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Email: 2185134304@qq.com
 * Created by JackChen 2018/4/20 17:39
 * Version 1.0
 * Params:
 * Description:
*/
public class User extends RealmObject {

    @Required
    private String id ;
    private String name ;

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
}
