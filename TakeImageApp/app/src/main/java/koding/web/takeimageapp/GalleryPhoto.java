package koding.web.takeimageapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;

public class GalleryPhoto extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ImageAdapter myImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo2);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        myImageAdapter = new ImageAdapter(this);
        gridView.setAdapter(myImageAdapter);
        gridView.setOnItemClickListener(this);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera/";
        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_SHORT).show();
        File targetDirector = new File(targetPath);
        File[] files = targetDirector.listFiles();
        for (File file : files){
            myImageAdapter.add(file.getAbsolutePath());
        }
    }
    @Override
    public void onClick(View v){

    }

    @Override
    public void  onItemClick(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(GalleryPhoto.this, "Gambar Dipillih" + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, SinglePhoto.class);
        Bundle b = new Bundle();
    b.putInt("Posisi",position);
    i.putExtras(b);
    startActivity(i);
    }
}
