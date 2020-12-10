package kr.hs.emirim.s2019s38.mirimidolmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDBHelper myHelper;
    EditText editName,editCount,editSelectName,editSelectCount;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("아이돌 그룹 관리 DB");

        editName=findViewById(R.id.edit_name);
        editCount=findViewById(R.id.edit_count);
        editSelectName=findViewById(R.id.edit_select_name);
        editSelectCount=findViewById(R.id.edit_select_count);
        Button btnInit=findViewById(R.id.btn_init);
        Button btnInput=findViewById(R.id.btn_input);
        Button btnSelect=findViewById(R.id.btn_select);

        myHelper=new MyDBHelper(this);
        btnInit.setOnClickListener(btnListener);
        btnInput.setOnClickListener(btnListener);
        btnSelect.setOnClickListener(btnListener);
    }
    View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_init:
                    sqlDB=myHelper.getWritableDatabase();
                    myHelper.onUpgrade(sqlDB,1,2);
                    sqlDB.close();
                    break;
                case R.id.btn_input:
                    sqlDB=myHelper.getWritableDatabase();
                    sqlDB.execSQL("insert into groupTBL values('"+editName.getText().toString()+"',"+editCount.getText().toString()+");");
                    sqlDB.close();
                    Toast.makeText(getApplicationContext(),"정상적으로 저장됨",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_select:
                    sqlDB=myHelper.getReadableDatabase();
                    Cursor cursor=sqlDB.rawQuery("select * from groupTBL;",null);
                    String strNames="아이돌 이름\r\n------\r\n";
                    String strNumbers="인원수\r\n------\r\n";
                    while (cursor.moveToNext()){
                        strNames+=cursor.getString(0)+"\r\n";
                        strNumbers+=cursor.getInt(1)+"\r\n";
                    }
                    editSelectName.setText(strNames);
                    editSelectCount.setText(strNumbers);
                    cursor.close();
                    sqlDB.close();
                    break;
            }
        }
    };
}