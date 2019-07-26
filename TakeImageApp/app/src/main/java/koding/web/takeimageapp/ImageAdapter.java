package koding.web.takeimageapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> itemlist = new ArrayList<String>();

    public ImageAdapter(Context c){mContext = c;}
    void add (String path){itemlist.add(path);}
    @Override
    public int getCount(){
        //Jumlah total gambar
        return itemlist.size();
    }
    @Override
    public Object getItem(int position){return position;}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Mengambil Gambar Dari Gallery
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView) convertView;
        }
        Bitmap bitmap = decodeSampleBitmap(itemlist.get(position), 200, 200);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }

    private Bitmap decodeSampleBitmap(String path, int reqWith, int reqHeight) {
        Bitmap bm = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, reqWith, reqHeight);
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);
        return  bm;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWith, int reqHeight) {
        final  int height = options.outHeight;
        final  int width =options.outWidth;
        int inSampleSize = 1;

        if (height> reqHeight || width> reqWith){
            if (width>height){
                inSampleSize = Math.round((float)height/(float)reqHeight);
            }else {
                inSampleSize = Math.round((float)width/(float)reqWith);
            }
        }
        return  inSampleSize;
    }
}
