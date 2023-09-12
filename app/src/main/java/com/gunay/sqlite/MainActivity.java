package com.gunay.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // try içinde hata alırsan uygulama çökmez .
        // try içinde kod denedir ve eğer hata alınırsa catch ile hata yakalanır.
        // catch içine hata yakalandığında ne yapılacağı yazılır
        // genellikle android ile çalışırken veritabanları try and catch içinde kullanılır.
        try {
            //SQLiteDatabase sınıfından bir obje türetilerek veri tabanı oluşturulur.
            //Database adı belirtilir
            // MODE_PRİVATE database 'e sadece bizim uygulamamızda kullanılaağını söyler.
            //factory ise imleç yapmak istediğimizi sorar (customcurser)
            // veriler yerelde kaydedilir
            SQLiteDatabase database = this.openOrCreateDatabase("Fishes",MODE_PRIVATE,null);

            // İçine SQL kodu yazmamızı sağlar
            // CREATE TABLE IF NOT EXİSTS fishes (fishes adından bir tablo yoksa oluştur)
            // (name VARCHAR, weight INTAGER) tablodaki kolonların adı ve veri tipi
            // PRIMARY KEY id nin biz girmesek bile otomatik olarak atanmasını sağlar (INTEGER) olarak belirtmek önemli
            database.execSQL("CREATE TABLE IF NOT EXISTS fishes (id INTEGER PRIMARY KEY, name VARCHAR, weight INT)");

            // fishes tablosuna veri girme
            // ikinci sanal makina çalıştırmada aynı veri tekrar kaydedilmesin diye yorum satırı yap

            database.execSQL("INSERT INTO fishes (name, weight) VALUES ('Turna',15)");
            database.execSQL("INSERT INTO fishes (name, weight) VALUES ('Orkinos',48)");
            database.execSQL("INSERT INTO fishes (name, weight) VALUES ('Levrek',3)");
            database.execSQL("INSERT INTO fishes (name, weight) VALUES ('Yayın',8)");



            //Güncelleme
            //database.execSQL("UPDATE fishes SET weight = 2 WHERE name = 'Levrek'");

            //Silme
            //database.execSQL("DELETE FROM fishes where id = 4 ");




            //cursor hücrelerin içerisinde gezip verileri alır ve bize okur(verir).
            // burada cursor'a gezeceği hücreler için yapacağı işlem yazılır
            // where kullanarak sonuçlar filtrelenebilir

            Cursor cursor = database.rawQuery("SELECT * FROM fishes",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM fishes WHERE name LIKE '%a'",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM fishes WHERE id = 2",null);


            // cursor'un gideceği hangi column'lara gideceği belirlenir
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int weightIndex = cursor.getColumnIndex("weight");

            // databas'e girilen verileri ekrana bastırma
            // cursor ilerlyebildiği kadar ilerlesin (cursor.moveToNext)
            while(cursor.moveToNext()){

                // cursor'un belirlenen column'lara gitmesi ve verinin ekrana bastırılması
                System.out.println("ID: " + cursor.getInt(idIndex));
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Weight: " + cursor.getInt(weightIndex));

            }

            cursor.close();

        } catch (Exception e){
            e.printStackTrace();   // hatanın detaylarını yazdırır.

        }

    }
}































