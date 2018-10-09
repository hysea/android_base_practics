package com.hysea.media.preview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.Toast;

import com.hysea.media.R;

import java.util.concurrent.Semaphore;

/**
 *
 */
public class PreViewActivity extends AppCompatActivity {
    private static final String TAG = PreViewActivity.class.getSimpleName();

    private SurfaceView mSurfaceView;

    private CameraDevice mCameraDevice;
    private CameraManager mCameraManager;
    private String mCameraId;
    private Context mContext;
    private ImageReader mImageReader;
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);//以防止在关闭相机之前应用程序退出


    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);
        mContext = this;
        mSurfaceView = findViewById(R.id.texture_view);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                initCameraAndPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
        initCamera();
    }

    private void initCameraAndPreview() {
//        startBackgroundThread();
//        try {
//            mCameraId = "" + CameraCharacteristics.LENS_FACING_FRONT;
//            mImageReader = ImageReader.newInstance(mSurfaceView.getWidth(), mSurfaceView.getHeight(),
//                    ImageFormat.JPEG, 7);
//            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mHandler);
//
//            mCameraManager.openCamera(mCameraId, DeviceStateCallback, mHandler);
//        } catch (CameraAccessException e) {
//            Log.e("linc", "open camera failed." + e.getMessage());
//        }
    }

    private void initCamera() {
        mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        if (mCameraManager == null) return;
        try {
            for (String cameraId : mCameraManager.getCameraIdList()) {
                //获取到每个相机的参数对象，包含前后摄像头，分辨率等
                CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(cameraId);
                //摄像头的方向
                Integer facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing == null) {
                    continue;
                }

                // 匹配方向
                if (facing != CameraCharacteristics.LENS_FACING_BACK) {
                    continue;
                }
                //打开指定的摄像头
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mCameraManager.openCamera(mCameraId, stateCallback, mBackgroundHandler);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化并启动HandlerThread
     * 因为创建会话是耗时的操作，不宜放在主线程
     */
    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    private final TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
//            startPreview(width, height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };


    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraOpenCloseLock.release();
            mCameraDevice = cameraDevice;
            // 要想预览、拍照等操作都是需要通过会话来实现，所以创建会话用于预览
//            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
        }
    };

//    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
//    }

//    private void createCameraPreviewSession() {
//        // 将CaptureRequest的构建器与Surface对象绑定在一起
//        mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//
//        // 为相机预览，创建一个CameraCaptureSession对象
//        mCameraDevice.createCaptureSession(Arrays.asList(surface, imageReader.getSurface()), stateCallback, null);
//    }

    private void startPreview(int width, int height) {

    }

    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (mBackgroundThread != null) {
            mBackgroundThread.quit();
        }
        super.onDestroy();
    }
}
