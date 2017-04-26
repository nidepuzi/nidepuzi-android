package com.danlai.nidepuzi.service.okhttp3;

import android.os.Environment;

import com.danlai.library.entities.FilePara;
import com.danlai.library.utils.CameraUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import okhttp3.Response;

/**
 * Created by wulei on 2016/2/4.
 */
public abstract class FileParaCallback extends Callback<FilePara> {

    @Override
    public FilePara parseNetworkResponse(Response response, int id) throws Exception {
        File Target_Location = null;
        try {
            CameraUtils.Create_MY_IMAGES_DIR();
            Target_Location = new File("" + Environment.getExternalStorageDirectory() +
                "/nidepuzi" + "/" + CameraUtils.Get_Random_File_Name() + ".jpg");
            Target_Location.createNewFile();
            OutputStream os = new FileOutputStream(Target_Location);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = response.body().byteStream().read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            response.body().byteStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilePara filePara = new FilePara();
        if (Target_Location != null) {
            filePara.setFilePath(Target_Location.getAbsolutePath());
        }
        return filePara;
    }
}
