package util;

import android.os.Environment;

/**
 * Created by Westchennn on 17/3/17.
 */
public class Tools {
    public static boolean hasSdcard(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }
}
