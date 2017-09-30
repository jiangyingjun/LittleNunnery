//package com.shuai;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.shuai.base.BaseActivity;
//import com.wilddog.video.CallStatus;
//import com.wilddog.video.Conversation;
//import com.wilddog.video.LocalStream;
//import com.wilddog.video.RemoteStream;
//import com.wilddog.video.WilddogVideo;
//import com.wilddog.video.WilddogVideoError;
//import com.wilddog.video.WilddogVideoView;
//import com.wilddog.video.WilddogVideoViewLayout;
//import com.wilddog.video.core.stats.LocalStreamStatsReport;
//import com.wilddog.video.core.stats.RemoteStreamStatsReport;
//
//import java.text.DecimalFormat;
//import java.util.Map;
//
//public class ConverationActivity extends BaseActivity implements View.OnClickListener {
//
//
//    // Local preview screen position after call is connected.
//    private static final int LOCAL_X_CONNECTED = 0;
//    private static final int LOCAL_Y_CONNECTED = 0;
//    private static final int LOCAL_WIDTH_CONNECTED = 50;
//    private static final int LOCAL_HEIGHT_CONNECTED = 100;
//    // Remote video screen position
//    private static final int REMOTE_X = 50;
//    private static final int REMOTE_Y = 0;
//    private static final int REMOTE_WIDTH = 50;
//    private static final int REMOTE_HEIGHT = 100;
//
//    WilddogVideoViewLayout localViewLayout;
//
//    WilddogVideoView localView;
//
//    WilddogVideoViewLayout remoteViewLayout;
//
//    WilddogVideoView remoteView;
//
//    private LocalStream localStream;
//
//    private Conversation mConversation;
//
//    private boolean isInConversation = false;
//
//    private Map<Conversation, AlertDialog> conversationAlertDialogMap;
//
//    private AlertDialog alertDialog;
//
//
//    private Button conver_call;
//
//    private WilddogVideo.Listener inviteListener = new WilddogVideo.Listener() {
//        @Override
//        public void onCalled(final Conversation conversation, String s) {
//            if(!TextUtils.isEmpty(s)){
//                Toast.makeText(ConverationActivity.this,"对方邀请时候携带的信息是:"+s,Toast.LENGTH_SHORT).show();
////                tvData.setText("对方携带数据为:"+s);
//            }
//            mConversation = conversation;
//            mConversation.setConversationListener(conversationListener);
//            mConversation.setStatsListener(statsListener);
//            AlertDialog.Builder builder = new AlertDialog.Builder(ConverationActivity.this);
//            builder.setMessage("邀请你加入会话");
//            builder.setTitle("加入邀请");
//            builder.setNegativeButton("拒绝邀请", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    mConversation.reject();
//                }
//            });
//            builder.setPositiveButton("确认加入", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    conversationAlertDialogMap.remove(conversation);
//                    mConversation.accept(localStream);
//                    isInConversation = true;
//
//                }
//            });
//
//            alertDialog = builder.create();
//            alertDialog.setCanceledOnTouchOutside(false);
//            alertDialog.show();
//            conversationAlertDialogMap.put(conversation, alertDialog);
//        }
//
//        @Override
//        public void onTokenError(WilddogVideoError wilddogVideoError) {
//
//        }
//
//    };
//
//    private Conversation.StatsListener statsListener = new Conversation.StatsListener() {
//        @Override
//        public void onLocalStreamStatsReport(LocalStreamStatsReport localStreamStatsReport) {
//            changeLocalData(localStreamStatsReport);
//        }
//
//        @Override
//        public void onRemoteStreamStatsReport(RemoteStreamStatsReport remoteStreamStatsReport) {
//            changeRemoteData(remoteStreamStatsReport);
//        }
//    };
//
//
//    public void changeLocalData(final LocalStreamStatsReport localStats) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
////                tvLocalDimensions.setText("dimension:" + localStats.getWidth() + "x" + localStats.getHeight());
////                tvLocalFps.setText("fps:" + localStats.getFps());
////                tvLocalRate.setText("rate:" + localStats.getBitsSentRate() + "Kb/s");
////                tvLocalSendBytes.setText("sent:" + convertToMB(localStats.getBytesSent()) + "MB");
//            }
//        });
//
//    }
//
//
//    public void changeRemoteData(final RemoteStreamStatsReport remoteStats) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
////                tvRemoteDimensions.setText("dimension:" + remoteStats.getWidth() + "x" + remoteStats.getHeight());
////                tvRemoteFps.setText("fps:" + remoteStats.getFps());
////                tvRemoteRecBytes.setText("received:" + convertToMB(remoteStats.getBytesReceived()) + "MB");
////                tvRemoteRate.setText("rate:" + remoteStats.getBitsReceivedRate() + "Kb/s" + " delay" + remoteStats.getDelay() + "ms");
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams
//                .FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View
//                .SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//
//        setContentView(R.layout.activity_converation);
//        initView();
//
////        WilddogUtil.getInstance().initVideoSDK();
////        initVideoRender();
////        createAndShowLocalStream();
////        conversationAlertDialogMap = new HashMap<>();
////
////        //在使用inviteToConversation方法前需要先设置会话邀请监听，否则使用邀请功能会抛出IllegalStateException异常
////        WilddogUtil.getInstance().video.setListener(inviteListener);
//
//
//    }
//
//    private void initView() {
//
//        localViewLayout= (WilddogVideoViewLayout) findViewById(R.id.local_video_layout);
//
//        localView = (WilddogVideoView) findViewById(R.id.local_video_view);
//
//        remoteViewLayout = (WilddogVideoViewLayout) findViewById(R.id.remote_video_layout);
//
//        remoteView= (WilddogVideoView) findViewById(R.id.remote_video_view);
//
//        conver_call = (Button) findViewById(R.id.conver_call);
//
//        conver_call.setOnClickListener(this);
//
//    }
//
//
//    //初始化视频展示控件
//    private void initVideoRender() {
//        //获取EglBase对象
//
//        //初始化视频展示控件位置，大小
//        localViewLayout.setPosition(LOCAL_X_CONNECTED, LOCAL_Y_CONNECTED, LOCAL_WIDTH_CONNECTED, LOCAL_HEIGHT_CONNECTED);
//        localView.setZOrderMediaOverlay(true);
//        localView.setMirror(true);
//
//        remoteViewLayout.setPosition(REMOTE_X, REMOTE_Y, REMOTE_WIDTH, REMOTE_HEIGHT);
//
//    }
//
//
//    private void createAndShowLocalStream() {
//
////        LocalStreamOptions.Builder builder = new LocalStreamOptions.Builder();
////        LocalStreamOptions options = builder.dimension(LocalStreamOptions.Dimension.DIMENSION_480P).build();
////        //创建本地视频流，通过video对象获取本地视频流
////        localStream = WilddogUtil.getInstance().video.createLocalStream(options);
////        //开启音频/视频，设置为 false 则关闭声音或者视频画面
////        localStream.enableAudio(true);
////         localStream.enableVideo(true);
////        //为视频流绑定播放控件
////        localStream.attach(localView);
//    }
//
//
//
//    private Conversation.Listener conversationListener = new Conversation.Listener() {
//        @Override
//        public void onCallResponse(CallStatus callStatus) {
//            switch (callStatus) {
//                case ACCEPTED:
//                    Toast.makeText(ConverationActivity.this, "接受了你的邀请", Toast.LENGTH_SHORT).show();
//                    isInConversation = true;
//                    break;
//                case REJECTED:
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(ConverationActivity.this, "对方拒绝你的邀请", Toast.LENGTH_SHORT).show();
//                            isInConversation = false;
////                            btnInvite.setText("用户列表");
//                        }
//                    });
//                    break;
//                case BUSY:
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(ConverationActivity.this, "对方正在通话中,稍后再呼叫", Toast.LENGTH_SHORT).show();
//                            isInConversation = false;
////                            btnInvite.setText("用户列表");
//                        }
//                    });
//                    break;
//                case TIMEOUT:
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(ConverationActivity.this, "呼叫对方超时,请稍后再呼叫", Toast.LENGTH_SHORT).show();
//                            isInConversation = false;
////                            btnInvite.setText("用户列表");
//                        }
//                    });
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        @Override
//        public void onStreamReceived(RemoteStream remoteStream) {
//            remoteStream.attach(remoteView);
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
////                    btnInvite.setText("用户已加入");
//                }
//            });
//        }
//
//        @Override
//        public void onClosed() {
////            Log.e(TAG, "onClosed");
//            if (alertDialog != null && alertDialog.isShowing()) {
//                alertDialog.dismiss();
//            }
//            isInConversation = false;
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    closeConversation();
//                    Toast.makeText(ConverationActivity.this, "对方挂断", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//
//        @Override
//        public void onError(WilddogVideoError wilddogVideoError) {
//            if (wilddogVideoError != null) {
//                Toast.makeText(ConverationActivity.this, "通话中出错,请查看日志", Toast.LENGTH_SHORT).show();
//                Log.e("error", wilddogVideoError.getMessage());
////                btnInvite.setText("用户列表");
//                isInConversation = false;
//            }
//        }
//    };
//
//
//
//    private void closeConversation() {
//        if (mConversation != null) {
//            mConversation.close();
//            mConversation = null;
//            //挂断时会释放本地流，如需继续显示本地流，则挂断后要重新获取一次本地流
//            createAndShowLocalStream();
//        }
////        btnInvite.setText("用户列表");
//    }
//
//
//    DecimalFormat decimalFormat = new DecimalFormat("0.00");
//
//    public String convertToMB(long value) {
//        float result = Float.parseFloat(String.valueOf(value)) / (1024 * 1024);
//        return decimalFormat.format(result);
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //需要离开会话时调用此方法，并做资源释放和其他自定义操作
//        if (localView != null) {
//            localView.release();
//            localView = null;
//        }
//        if (remoteView != null) {
//            remoteView.release();
//            remoteView = null;
//        }
//        if (mConversation != null) {
//            mConversation.close();
//        }
//        if (localStream != null) {
//            if (!localStream.isClosed()) {
//                localStream.close();
//            }
//        }
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.conver_call:
////                String uid =WilddogUtil.getInstance().getLoginUserInfo();
////
////                mConversation= WilddogUtil.getInstance().video.call(uid,localStream,"helloworld");
////
////                mConversation.accept(localStream);
//                break;
//
//
//
//
//        }
//    }
//}
