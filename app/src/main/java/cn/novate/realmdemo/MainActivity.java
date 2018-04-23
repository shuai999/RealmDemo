package cn.novate.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
/**
 * Email: 2185134304@qq.com
 * Created by JackChen 2018/4/23 9:05
 * Version 1.0
 * Params:
 * Description:    增删改查如下
*/

public class MainActivity extends AppCompatActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRealm() ;

    }


    /**
     * 初始化数据库
     */
    private void initRealm() {
        Realm.init(this);
        //mRealm = Realm.getDefaultInstance();
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("test.realm")  // 数据库名
                .schemaVersion(2)  // 版本号
//                .migration(new CustomMigration())//升级数据库
                //.deleteRealmIfMigrationNeeded()
                .build();

        mRealm = Realm.getInstance(configuration);
        String str = mRealm.getPath();
        Log.e("TAG", str);
    }


    /**
     * 添加
     */
    public void add(View view){
        // 插入一条数据
        final User user = new User() ;
        user.setId("1");
        user.setName("Novate");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(user) ;
            }
        });
    }
    /**
     * 删除
     */
    public void delete(View view){

    }
    /**
     * 修改
     */
    public void update(View view){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 先查找得到User对象
                User user = mRealm.where(User.class).findFirst() ;
                user.setName("北京-Novate");
            }
        });
    }
    /**
     * 查询
     */
    public void query(View view){
        // RealmResults -> 基类是List集合
        RealmResults<User> userList = mRealm.where(User.class).findAll() ;
        for (User user : userList) {
            Toast.makeText(MainActivity.this , "size ->" + userList.size() + "id ->"+ user.getId() +"name ->"+user.getName() , Toast.LENGTH_SHORT).show(); ;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        mRealm.close();
    }
}
